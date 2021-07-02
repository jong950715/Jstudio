package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardUser {

    @Id @GeneratedValue
    @Column(name = "board_user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "site_user_id", nullable = false)
    SiteUser siteUser;

    @OneToMany(mappedBy = "boardUser")
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "boardUser")
    List<Reply> replies = new ArrayList<>();

    @ManyToMany(mappedBy = "goodUsers")
    List<Post> goodPosts = new ArrayList<>();

    @ManyToMany(mappedBy = "badUsers")
    List<Post> badPosts = new ArrayList<>();
    //goodPosts
    //badPosts
}
