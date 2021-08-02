package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "lookatme_like")
public class LookatmeLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookatme_like_id")
    private Long lookatmeLikeId;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lookatme_id")
    private Lookatme lookatme;

//    @Column(name = "lookatme_id")
//    private Long lookatmeId;

}
