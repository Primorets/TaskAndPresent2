package com.example.taskandpresent2.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortUserDto {
    private Long id;
    private String name;
    private String email;
}
