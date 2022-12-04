package com.anymind.pos.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.anymind.pos.controllers.request.Request;
import com.anymind.pos.controllers.response.Response;
import com.anymind.pos.entities.Payment;
import com.anymind.pos.repositories.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Response calculate(Request request) {
        BigDecimal calculatedFinalPrice = request.getPrice().multiply(request.getPriceModifier());
        int points = request.getPrice().multiply(BigDecimal.valueOf(0.05)).intValue();
        
        paymentRepository.save(Payment.builder()
                .finalPrice(calculatedFinalPrice)
                .points(points)
                .paymentMethod(request.getPaymentMethod())
                .priceModifier(request.getPriceModifier())
                .createdDateTime(request.getDateTime())
                .build()
        );

        return Response.builder()
                .finalPrice(calculatedFinalPrice)
                .points(points)
                .build();
    }
}
