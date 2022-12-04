package com.anymind.pos.controllers.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    
    @JsonAlias("final_price")
    private BigDecimal finalPrice;

    private Integer points;
}
