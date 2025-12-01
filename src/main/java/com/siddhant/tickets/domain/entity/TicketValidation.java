package com.siddhant.tickets.domain.entity;

import com.siddhant.tickets.domain.enums.TicketValidationMethod;
import com.siddhant.tickets.domain.enums.TicketValidationStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticketValidation")
public class TicketValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false,updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TicketValidationStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "validationMethod",nullable = false)
    private TicketValidationMethod validationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketValidation that = (TicketValidation) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, updatedAt);
    }
}
