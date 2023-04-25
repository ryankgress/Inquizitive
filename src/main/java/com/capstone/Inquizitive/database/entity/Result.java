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
@Table(name="results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="team_id", insertable=false, updatable=false)
    private Integer teamId;

    @Column(name="trivia_id", insertable=false, updatable=false)
    private Integer triviaId;

    @Column(name="points_awarded")
    private Integer pointsAwarded;

    @Column(name="placement")
    private String placement;

    /* To Trivia Detail */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = true /*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "trivia_id", nullable = false)
    private TriviaDetail triviaDetail;

    /* To Team */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = true /*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
