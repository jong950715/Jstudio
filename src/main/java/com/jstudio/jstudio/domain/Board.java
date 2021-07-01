package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "parent_board")
    private Board parentBoard;

    @OneToMany(mappedBy = "parentBoard")
    List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    List<Post> posts = new ArrayList<>();
    // +) Posts parentBoard
}
