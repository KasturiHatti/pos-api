package com.anymind.pos.controllers;

import com.anymind.pos.controllers.request.Request;
import com.anymind.pos.controllers.response.Response;
import com.anymind.pos.controllers.response.SalesResponse;
import com.anymind.pos.service.SalesPriceService;
import com.anymind.pos.validator.RequestValidator;
import jakarta.validation.Valid;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {

	private final SalesPriceService salesPriceService;

	private final RequestValidator requestValidator;

	@PostMapping("/calculateSalesPrice")
	public Response calculateRates(@RequestBody @Valid Request request) {
		return salesPriceService.calculate(request);
	}

	@GetMapping("/sales")
	public SalesResponse getMessage(@RequestParam ZonedDateTime startDateTime, @RequestParam ZonedDateTime endDateTime) {
		requestValidator.validateDates(startDateTime, endDateTime);
		return salesPriceService.getAll(startDateTime, endDateTime);
	}
}
