package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="interview_id")
    private Long interviewId;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "company_id")
//    private String companyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

//    @Column(name = "room_id")
//    private Long roomId;

    @OneToOne
    @JoinColumn(name = "session_id")
    private Room room;

    @Column(name = "interview_date")
    private String interviewDate;

    @Column(name = "interview_time")
    private String interviewTime;
}
