package com.example.taskandpresent2.user.model;

import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.event.model.EventDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(groups = {Create.class})
    private String name;
    @Email
    @NotBlank(groups = {Create.class})
    private String email;
    private List<EventDto> eventDtoList;

}
