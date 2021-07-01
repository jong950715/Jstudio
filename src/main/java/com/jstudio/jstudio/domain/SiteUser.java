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

    private String name;
    private String email;
    private String picture;
    private String nickName;
    private String loginId;
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
