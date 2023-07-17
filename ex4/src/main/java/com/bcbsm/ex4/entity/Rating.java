package com.bcbsm.ex4.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratings_id_seq")
    @SequenceGenerator(name = "ratings_id_seq", sequenceName = "ratings_id_seq", allocationSize = 1)
    @Column(name = "rating_id")
    private Long id;
    @Column(name = "rating_value")
    private int rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date")
    private Date date;
    @Column(name = "username")
    private String user;
}
