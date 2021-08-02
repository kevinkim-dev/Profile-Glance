package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<Lookatme> lookatmes = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private RoomInfo roomInfo;

    @OneToMany(mappedBy = "user")
    private List<Interview> interviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<LookatmeLike> lookatmeLikes = new ArrayList<>();

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_password")
    private String userPassword;

    private String major1;

    private String major2;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(columnDefinition = "boolean default false", name = "is_admin")
    private boolean isAdmin;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "company_like")
    private Long companyLike;

    private String birth;

    @Column
    private String portfolio1;

    @Column
    private String portfolio2;

}