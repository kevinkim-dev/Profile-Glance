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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @OneToMany(mappedBy = "room")
    private List<RoomInfo> roomInfos = new ArrayList<>();

    @OneToOne(mappedBy = "room")
    private Interview interview;

//    @Column(name = "company_id")
//    private String companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "room_category")
    private Long roomCategory;

    @Column(name = "room_url")
    private String roomUrl;

    @Column(name = "is_active")
    private boolean isActive;
}
