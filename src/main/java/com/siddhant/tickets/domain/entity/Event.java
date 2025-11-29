package com.siddhant.tickets.domain.entity;

import com.siddhant.tickets.domain.enums.EventStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;

    @Column(name = "eventName",nullable = false)
    private String eventName;

    @Column(name = "eventStartTime",nullable = false)
    private LocalDateTime eventStartTime;

    @Column(name = "eventEndTime",nullable = false)
    private LocalDateTime eventEndTime;

    @Column(name = "eventVenue",nullable = false)
    private String eventVenue;

    @Column(name="saleStartDate")
    private LocalDateTime saleStartDate;

    @Column(name="saleEndDate")
    private LocalDateTime saleEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private EventStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizerId")
    private User organizer;

    @ManyToMany(mappedBy = "attendingEvents")
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "satffingEvents")
    private List<User> staff = new ArrayList<>();

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;
}
