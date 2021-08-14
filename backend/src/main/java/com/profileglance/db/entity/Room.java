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

public class Room {

    @Id
    @Column(name = "session_id")
    private String sessionId;

    @OneToMany(mappedBy = "room")
    private List<RoomInfo> roomInfos = new ArrayList<>();

    @OneToOne(mappedBy = "room")
    private Interview interview;

    @OneToOne(mappedBy = "room")
    private Recruit recruit;

//    @Column(name = "company_id")
//    private String companyId;

    @Column(name = "room_category")
    private String roomCategory;

    @Column(name = "host")
    private String host;

    @Column(name = "create_at")
    private String createAt;

}
