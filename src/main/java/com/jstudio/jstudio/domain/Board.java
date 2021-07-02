package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition="text")
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
