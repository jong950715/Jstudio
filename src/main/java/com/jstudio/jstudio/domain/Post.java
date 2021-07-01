package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_user_id")
    private BoardUser boardUser;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;
    private String content;
    private String picture;
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
