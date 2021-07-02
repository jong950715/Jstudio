package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "board_user_id", nullable = false)
    private BoardUser boardUser;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "parent_reply")
    private Reply parent;

    @OneToMany(mappedBy = "parent")
    private List<Reply> children = new ArrayList<>();

    private int depth;
    private int position;

    //not on DB
    @Transient
    private int numDescendant;
}
