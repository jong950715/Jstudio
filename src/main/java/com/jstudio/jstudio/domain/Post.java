package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_user_id", nullable = false)
    private BoardUser boardUser;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(columnDefinition="text")
    private String picture;
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToMany
    @JoinTable(name = "good_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "board_user_id"))
    List<BoardUser> goodUsers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "bad_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "board_user_id"))
    List<BoardUser> badUsers = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    List<Reply> replies = new ArrayList<>();

}
