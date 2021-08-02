package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;

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
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "user_id")
    private Long userId;

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