package com.example.taskandpresent2.user.model;

import com.example.taskandpresent2.Create;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(groups = {Create.class})
    private String name;
    @Email
    @NotBlank(groups = {Create.class})
    private String email;
    private Set<Long> friendsIds = new HashSet<>();
    private Map<Long, Boolean> friendshipStatuses;
}
