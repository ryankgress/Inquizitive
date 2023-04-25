package com.capstone.Inquizitive.database.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "trivia_details")
public class TriviaDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="trivia_name")
    private String triviaName;

    @Column(name="location_name")
    private String locationName;

    @Column(name="address1")
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zip")
    private String zip;

    @Column(name="date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name="host_id", insertable = false, updatable = false)
    private Integer hostId;

    @Column(name="active")
    private String active;


    /* To Result */
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "triviaDetail", cascade = CascadeType.ALL)
    private List<Result> results;

    /* To User/Host */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = true /*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;
}
