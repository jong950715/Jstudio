게시판

# Entity(엔티티) 객체 구현하기



----------------------------

### 엔티티 자바코드로 구현하기

지난번 설계했던대로 자바코드를 구현할 차례입니다.<br>
그 중 몇가지만 살펴보겠습니다.

---------------------------

### ManyToMany 구현하기


User와 Role을 예시로 ManyToMany를 구현하는 두가지 방법을 소개해보겠습니다.<br>

### 1. 중간 테이블(user_role)이 객체로 구현되지 않는 방법

```java
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class SiteUser {

    @Id @GeneratedValue
    @Column(name = "site_user_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "site_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    
}

```

```java
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // role name must start with ROLE_

    @ManyToMany(mappedBy = "roles")
    private List<SiteUser> siteUsers = new ArrayList<>();

}
```
> ManyToMany 어노테이션을 통해서 중간에 있는 테이블(user_role)은 DB상에서만 존재하고, JPA가 알아서 맵핑해주는 방식입니다.<br>
>
> + 장점 : 편리함
> + 단점 : 중간테이블 활용이 불가 기능 구현이 불가능
>
> 만약에 role에 만료기한을 설정하고 싶다면 어떻게 될까요? 이런 기능은 중간 테이블을 활용해야 하고 다음과 같은 방법으로 구현한다면 가능합니다.


### 2. 중간 테이블(user_role)이 객체로 구현되는 방법
```java

@Entity
public class UserRole {

    @Id @GeneratedValue
    Long id;

    private LocalDateTime expireTime;

    @ManyToOne
    @JoinColumn(name = "site_user_id")
    SiteUser user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}

```


```java
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class SiteUser {

    @Id @GeneratedValue
    @Column(name = "site_user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    void accessRoles(){
        List<Role> roles = userRoles.stream()
                .map(userRole -> userRole.getRole())
                .collect(Collectors.toList());
    }

}
```


```java
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // role name must start with ROLE_

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles = new ArrayList<>();

}
```
> 가운데 테이블이 ManyToMany를 OneToMany, ManyToOne으로 풀어낸 방법입니다.
> + 장점 : 가운데 테이블을 활용할 수 있음
> 
> UserRole에 expireTime을 추가함으로써 추가적인 기능을 수행할 수 있습니다.
> 
> + 단점 : 코드량이 늘고 roles에 접근하기 번거로운 등 불편함
> 
> SiteUser의 accessRoles를 보듯이 roles에 접근하기 번거롭고 복잡해집니다.

----------------------------------------

### 같은방법으로 follow 구현하기

```java
public class SiteUser {
    @Id @GeneratedValue
    @Column(name = "site_user_id")
    private Long id;

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
```

조금 복잡하지만 천천히 생각해보면 간단합니다.<br>
followers는 나를 팔로우 하는 사람이 저장되는 것이 목적입니다.
즉 followers는 팔로우 하는 사람,
나는 팔로우 당하는 사람 입니다.
```java
    @ManyToMany
    @JoinTable(name = "중간 테이블 이름",
            joinColumns = @JoinColumn(name = "나를 의미하는 칼럼"),
            inverseJoinColumns = @JoinColumn(name = "목표 데이터를 의미하는 칼럼"))
    private List<SiteUser> follower = new ArrayList<>();
```

> + 중간 테이블 명 : follow
> + 나를 의미하는 칼럼 : followee(팔로우 당하는 사람)
> + 목표 데이터를 의미하는 칼럼 : follower(팔로우 하는 사람)

---------------------------

### J.studio에서는

추가적인 기능을 구현할 계획이 없으므로, <br>
나머지도 1번 방법을 이용하여 빠르고 간편하게 구현해보겠습니다.

-----------------------------


참고 : ../src/main/java/com.jstudio.jstudio.domain

Rmx