package com.profileglance.db.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "room_category")
    private Long roomCategory;

    @Column(name = "room_url")
    private String roomUrl;

    @Column(name = "is_active")
    private boolean isActive;
}
