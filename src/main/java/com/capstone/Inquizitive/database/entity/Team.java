package com.capstone.Inquizitive.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="team_name")
    private String teamName;

    @Column(name="total_score")
    private Integer totalScore;

    @Column(name="team_pic")
    private String teamPic;

    @Column(name="team_desc", columnDefinition = "TEXT")
    private String teamDesc;

    @Column(name="join_code")
    private String joinCode;

    /* To Team Member */
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers;
}
