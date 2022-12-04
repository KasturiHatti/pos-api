package com.anymind.pos.controllers.request;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.anymind.pos.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private BigDecimal price;
    
    @JsonAlias("price_modifier")
    private BigDecimal priceModifier;

    @JsonAlias("payment_method")
    private PaymentMethod paymentMethod;

    @JsonAlias("datetime")
    private ZonedDateTime dateTime;
}
