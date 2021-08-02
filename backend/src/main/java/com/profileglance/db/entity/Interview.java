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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "interview_date")
    private String interviewDate;

    @Column(name = "interview_time")
    private String interviewTime;
}
