package com.estsoft.blogjpaproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}