package com.FlyNova.Pricing_Service.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FareRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String ruleName;
    private Long airlineId;
    @OneToOne
    private Fare fare;

    private Boolean isRefundable;
    private Double changeFee;
    private Double cancellationFee;
    private Integer refundDeadLineDays;
    private Integer ChangeDeadlineHours;
    private Boolean isChangeable;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant UpdatedAt;
}
