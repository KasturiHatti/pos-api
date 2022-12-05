package com.anymind.pos.logic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "pos")
@Data
@Component
public class PointCalculationProperties {

	private PointMap pointMap;

	@Data
	public static class PointMap {
		private Map<String, BigDecimal> map = new HashMap<>();
	}
}
