package com.jstudio.jstudio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // role name must start with ROLE_

    @ManyToMany(mappedBy = "roles")
    private List<SiteUser> siteUsers = new ArrayList<>();



}
