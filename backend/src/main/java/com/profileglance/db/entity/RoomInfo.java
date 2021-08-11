package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_info_id")
    private Long roomInfoId;

//    @Column(name = "room_id")
//    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Room room;

    @Column(name = "company_id")
    private String companyId;

//    @Column(name = "user_id")
//    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
