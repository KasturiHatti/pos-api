package com.anymind.pos.controllers.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
	private Instant dateTime;
	private BigDecimal sales;
	private Integer points;
}
