package com.siddhant.tickets.domain.entity;

import com.siddhant.tickets.domain.enums.TicketStatusEnum;
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
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {

    @Id
    @Column(name="id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="ticketStatus",nullable = false)
    private TicketStatusEnum ticketStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketTypeId")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaserId")
    private User purchaser;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketValidation> validations = new ArrayList<>();

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<QRCode> codes = new ArrayList<>();

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && ticketStatus == ticket.ticketStatus && Objects.equals(createdAt, ticket.createdAt) && Objects.equals(updatedAt, ticket.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketStatus, createdAt, updatedAt);
    }
}
