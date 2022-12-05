package com.anymind.pos.integrationtest;

import com.anymind.pos.PosApiApplication;
import com.anymind.pos.controllers.request.Request;
import com.anymind.pos.controllers.response.Response;
import com.anymind.pos.controllers.response.SalesResponse;
import com.anymind.pos.enums.PaymentMethod;
import com.anymind.pos.service.SalesPriceService;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PosApiApplication.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SalesPriceServiceTest {

	@Autowired
	private SalesPriceService salesPriceService;

	@Test
	void testCalculateSalesPrice() {
		Response response = salesPriceService.calculate(Request.builder()
			.price(BigDecimal.valueOf(105))
			.priceModifier(BigDecimal.valueOf(0.95))
			.paymentMethod(PaymentMethod.MASTERCARD)
			.dateTime(ZonedDateTime.now())
			.build());

		Assertions.assertEquals(5, response.getPoints());
		Assertions.assertEquals(BigDecimal.valueOf(99.75), response.getFinalPrice());
	}

	@Test
	void testGetSales() {
		Response response = salesPriceService.calculate(Request.builder()
			.price(BigDecimal.valueOf(105))
			.priceModifier(BigDecimal.valueOf(0.95))
			.paymentMethod(PaymentMethod.MASTERCARD)
			.dateTime(ZonedDateTime.now())
			.build());

		Assertions.assertEquals(5, response.getPoints());
		Assertions.assertEquals(BigDecimal.valueOf(99.75), response.getFinalPrice());

		Response response2 = salesPriceService.calculate(Request.builder()
			.price(BigDecimal.valueOf(1000))
			.priceModifier(BigDecimal.valueOf(0.95))
			.paymentMethod(PaymentMethod.MASTERCARD)
			.dateTime(ZonedDateTime.now())
			.build());

		Assertions.assertEquals(50, response2.getPoints());
		Assertions.assertEquals(0, BigDecimal.valueOf(950.00).compareTo(response2.getFinalPrice()));

		SalesResponse salesResponse = salesPriceService.getAll(ZonedDateTime.now().minusHours(1), ZonedDateTime.now().plusHours(1));

		Assertions.assertFalse(salesResponse.getSales().isEmpty());
		Assertions.assertEquals(1, salesResponse.getSales().size());
		Assertions.assertEquals(BigDecimal.valueOf(1049.75), salesResponse.getSales().get(0).getSales());
		Assertions.assertEquals(55, salesResponse.getSales().get(0).getPoints());
	}
}
