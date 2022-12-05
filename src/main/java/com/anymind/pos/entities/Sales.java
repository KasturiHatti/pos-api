package com.anymind.pos.entities;

import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.anymind.pos.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "SALES")
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    @Id
	@Column(name = "ID")
    private String id;
	@Column(name = "ORIGINAL_PRICE")
    private BigDecimal originalPrice;
	@Column(name = "FINAL_PRICE")
	private BigDecimal finalPrice;
	@Column(name = "POINTS")
	private Integer points;
	@Column(name = "PAYMENT_METHOD")
	private PaymentMethod paymentMethod;
	@Column(name = "PRICE_MODIFIER")
	private BigDecimal priceModifier;
	@Column(name = "TSZ_CREATED")
	private ZonedDateTime createdDateTime;
}
