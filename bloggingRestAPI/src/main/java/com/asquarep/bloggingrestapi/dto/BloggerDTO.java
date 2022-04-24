package com.asquarep.bloggingrestapi.dto;

import com.asquarep.bloggingrestapi.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BloggerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
