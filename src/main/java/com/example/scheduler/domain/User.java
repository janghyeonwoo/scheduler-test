package com.example.scheduler.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    public static User createUser(String name, Integer age, Team team) {
        return new User().builder()
                .name(name)
                .age(age)
                .team(team)
                .build();
    }
}
