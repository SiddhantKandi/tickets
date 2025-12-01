package com.siddhant.tickets.domain.entity;

import com.siddhant.tickets.domain.enums.QRCodeStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "qrCode")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private QRCodeStatusEnum status;

    @Column(name = "value",nullable = false)
    private String value;

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
        QRCode qrCode = (QRCode) o;
        return Objects.equals(id, qrCode.id) && status == qrCode.status && Objects.equals(value, qrCode.value) && Objects.equals(createdAt, qrCode.createdAt) && Objects.equals(updatedAt, qrCode.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, value, createdAt, updatedAt);
    }
}
