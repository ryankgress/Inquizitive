package com.capstone.Inquizitive.database.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="team_members")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id", insertable=false, updatable=false)
    private Integer userId;

    @Column(name="team_id", insertable=false, updatable=false)
    private Integer teamId;

    /* To User */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = true /*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* To Team */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = true /*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
