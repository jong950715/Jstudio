package com.jstudio.jstudio.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class SiteUser {

    @Id @GeneratedValue
    @Column(name = "site_user_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(columnDefinition="text")
    private String picture;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "site_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "followee"),
            inverseJoinColumns = @JoinColumn(name = "follower"))
    private List<SiteUser> follower = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "follower"),
            inverseJoinColumns = @JoinColumn(name = "followee"))
    private List<SiteUser> followee = new ArrayList<>();

}
