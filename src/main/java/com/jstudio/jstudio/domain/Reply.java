package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reply {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "board_user_id")
    private BoardUser boardUser;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "parent_reply")
    private Reply parent;

    @OneToMany(mappedBy = "parent")
    private List<Reply> children = new ArrayList<>();

    private int depth;
    private int order;

    //not on DB
    private int numDescendant;
}
