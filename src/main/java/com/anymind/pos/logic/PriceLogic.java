package com.anymind.pos.logic;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceLogic {

	private final PointCalculationProperties pointCalculationProperties;

	public Integer calculatePoints(BigDecimal price,  BigDecimal priceModified) {
		return price.multiply(priceModified).intValue();
	}

	public BigDecimal calculatedFinalPrice(BigDecimal price, BigDecimal priceModifier) {
		return price.multiply(priceModifier);
	}

}
