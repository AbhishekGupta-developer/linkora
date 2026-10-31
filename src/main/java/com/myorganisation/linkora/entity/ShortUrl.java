package com.myorganisation.linkora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "short_urls")
@Getter
@Setter
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    private String shortCode;

    @ManyToOne
    private User user;

    @ManyToMany
//    @JoinTable(name = "join_table_of_short_url_and_tags")
    private Set<Tag> tags =  new HashSet<>();
}
