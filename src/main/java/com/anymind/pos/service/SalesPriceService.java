package com.anymind.pos.service;

import com.anymind.pos.controllers.response.Sale;
import com.anymind.pos.controllers.response.SalesResponse;
import com.anymind.pos.logic.PriceLogic;
import java.math.BigDecimal;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.anymind.pos.controllers.request.Request;
import com.anymind.pos.controllers.response.Response;
import com.anymind.pos.entities.Sales;
import com.anymind.pos.repositories.SalesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesPriceService {

    private final SalesRepository salesRepository;

	private final PriceLogic priceLogic;

    public Response calculate(Request request) {
        BigDecimal calculatedFinalPrice = priceLogic.calculatedFinalPrice(request.getPrice(), request.getPriceModifier());
        int points = priceLogic.calculatePoints(request.getPrice(), request.getPriceModifier());
        
        salesRepository.save(buildSalesEntity(request, calculatedFinalPrice, points)
        );

        return Response.builder()
                .finalPrice(calculatedFinalPrice)
                .points(points)
                .build();
    }

	public SalesResponse getAll(ZonedDateTime start, ZonedDateTime till) {
		List<Sales> salesList = salesRepository.findAllByCreatedDateTimeBetween(start, till);

		List<Sale> sales = salesList.stream().collect(Collectors.groupingBy(salse -> salse.getCreatedDateTime().toInstant().truncatedTo(ChronoUnit.HOURS)))
			.entrySet().stream().map(instantListEntry -> Sale.builder()
				.dateTime(instantListEntry.getKey())
				.sales(instantListEntry.getValue().stream().map(Sales::getFinalPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
				.points(instantListEntry.getValue().stream().map(Sales::getPoints).reduce(0, Integer::sum))
				.build())
			.sorted(Comparator.comparing(Sale::getDateTime))
			.collect(Collectors.toList());

		return SalesResponse.builder().sales(sales).build();
	}

	private static Sales buildSalesEntity(Request request, BigDecimal calculatedFinalPrice, int points) {
		return Sales.builder()
			.id((UUID.randomUUID().toString()))
			.finalPrice(calculatedFinalPrice)
			.points(points)
			.paymentMethod(request.getPaymentMethod())
			.priceModifier(request.getPriceModifier())
			.createdDateTime(request.getDateTime())
			.build();
	}

}
