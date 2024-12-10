package com.example.taskandpresent2.user;

import com.example.taskandpresent2.Create;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(groups = {Create.class})
    private String name;
    @Email
    @NotBlank(groups = {Create.class})
    private String email;
    @JsonIgnore
    private List<Long> eventIds;
}
