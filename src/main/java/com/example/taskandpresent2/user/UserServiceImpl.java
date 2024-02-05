package com.example.taskandpresent2.user;

import com.example.taskandpresent2.exception.DuplicateEmailException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.exception.ValidationException;
import com.example.taskandpresent2.pageable.Pagination;
import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.toUserDto(userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован.")));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(toList());
    }

    @Override
    public List<UserDto> getALlFriends(Long userId) {
        try {
            UserDto user = getUserById(userId);
            List<Long> friendsIdList = new ArrayList<>(user.getFriendsIds());
            List<UserDto> userList = new ArrayList<>();
            for(int i=0;i<friendsIdList.size();i++){
                userList.add(getUserById(friendsIdList.get(i)));
            }
            return userList;
        } catch (UserNotFoundException userNotFoundException) {
            throw new UserNotFoundException("Ошибка в списке друзей.");
        }
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto user, Long id) {
        user.setId(id);
        User user1 = userRepository.findById(user.getId()).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован."));
        if (user.getEmail() == null) {
            user.setEmail(user1.getEmail());
        }
        if (user.getName() == null) {
            user.setName(user1.getName());
        }
        if (userRepository.findById(id).stream()
                .filter(user2 -> !Objects.equals(user2.getId(), user.getId()))
                .anyMatch(user2 -> user2.getEmail()
                        .equals(user.getEmail()))) {
            throw new DuplicateEmailException("Email уже зарегестрирован");

        }
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(user)));
    }

    @Override
    public void addFriend(Long userId, Long friendId) {

        UserDto user = getUserById(userId);
        UserDto friend = getUserById(friendId);
        if (!user.getFriendsIds().contains(userId)) {
            if (user.getFriendshipStatuses().containsKey(friendId)) {
                if (friend.getFriendshipStatuses().containsKey(userId)) {
                    user.getFriendsIds().add(friendId);
                    friend.getFriendshipStatuses().put(userId, true);
                    getUserById(friendId).getFriendsIds().add(userId);
                } else {
                    user.getFriendshipStatuses().put(friendId, false);
                }
                userRepository.save(UserMapper.toUser(user));
            }
        } else {
            throw new UserNotFoundException("Пользователь с ID: " + friendId
                    + "уже в друзьях у пользователя с ID: " + userId);
        }
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto user) {
        validateUser(user);
        try {
            return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(user)));
        } catch (DuplicateEmailException duplicateEmailException) {
            throw new DuplicateEmailException("Email уже зарегестрирован");
        }
    }

    @Override
    public List<UserDto> searchUserByName(String text, int from, int size) {
        return userRepository.searchUserByName(text, Pagination.makePageRequest(from, size)).stream()
                .filter(user -> user.getName().contains(text))
                .map(UserMapper::toUserDto)
                .collect(toList());
    }


    public boolean checkId(Long userId, Long friendId) {
        if (userId < 0 || friendId < 0) {
            throw new UserNotFoundException("ID не существует.");
        }
        getUserById(userId);
        getUserById(friendId);
        return true;
    }

    private void validateUser(UserDto user) {
        if (!user.getEmail().contains("@") || user.getEmail() == null) {
            throw new ValidationException("Введён не правильный email");
        }
        if (user.getName().isEmpty() || user.getName().contains(" ")) {
            throw new ValidationException("Введено пустое имя");
        }
    }
}
