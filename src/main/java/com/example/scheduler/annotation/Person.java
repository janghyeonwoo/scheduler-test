package com.example.scheduler.annotation;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;
    private String gender;

    public Person(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
