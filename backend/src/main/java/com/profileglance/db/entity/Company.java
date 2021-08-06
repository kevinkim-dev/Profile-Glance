package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_password")
    private String companyPassword;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_img")
    private String companyImg;

    @OneToMany(mappedBy = "company")
    private List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Interview> interviews = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Recruit> recruits = new ArrayList<>();

}
