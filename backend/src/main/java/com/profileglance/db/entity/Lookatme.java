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
public class Lookatme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookatme_id")
    private Long lookatmeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//    @Column(name = "user_id")
//    private Long userId;

//    @Column(name = "category_id")
//    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "lookatme")
    private List<LookatmeLike> lookatmeLikes = new ArrayList<>();

    private String title;

    private String content;

    private String video;

    private String thumbnail;

    private Long view;

    @Column(name = "video_like")
    private Long videoLike;

    @Column(name = "created_at")
    private String createdAt;

}
