package com.siddhant.tickets.domain.entity;

import com.siddhant.tickets.domain.enums.EventStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @ManyToMany(mappedBy = "staffingEvents")
    private List<User> staff = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>();

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(eventName, event.eventName) && Objects.equals(eventStartTime, event.eventStartTime) && Objects.equals(eventEndTime, event.eventEndTime) && Objects.equals(eventVenue, event.eventVenue) && Objects.equals(saleStartDate, event.saleStartDate) && Objects.equals(saleEndDate, event.saleEndDate) && status == event.status && Objects.equals(createdAt, event.createdAt) && Objects.equals(updatedAt, event.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, eventStartTime, eventEndTime, eventVenue, saleStartDate, saleEndDate, status, createdAt, updatedAt);
    }
}
