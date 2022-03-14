package com.example.scheduler.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<>();

    @Builder
    public Team(Long id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
