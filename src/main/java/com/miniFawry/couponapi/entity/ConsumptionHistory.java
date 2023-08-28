package com.miniFawry.couponapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Schema(name = "Consumption History Schema")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionHistory implements Serializable {


    public ConsumptionHistory(String orderCode, double priceBefore, double priceAfter, Coupon usedCoupon) {
        this.orderCode = orderCode;
        this.priceBefore = priceBefore;
        this.priceAfter = priceAfter;
        this.usedCoupon = usedCoupon;
        this.consumedAt = new Date();
    }

    @Id
    @SequenceGenerator(
            name = "consumptionHistory_id_seq",
            sequenceName = "consumptionHistory_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumptionHistory_id_seq")
    Long id;

    @Column(nullable = false)
    String orderCode;
    @Column(nullable = false)
    double priceBefore;
    @Column(nullable = false)
    double priceAfter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usedCoupon_id", referencedColumnName = "id")
    Coupon usedCoupon;

    Date consumedAt;
}