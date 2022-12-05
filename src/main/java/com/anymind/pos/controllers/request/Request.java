package com.anymind.pos.controllers.request;

import com.anymind.pos.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.anymind.pos.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
public class Request {

	@NotNull(message = "price can not be null")
	@Positive(message = "price must be > 0")
	private BigDecimal price;
    
    @NotNull(message = "Price modifier can not be null")
	@Positive(message = "Price  modifier must be > 0")
	@JsonAlias("price_modifier")
    private BigDecimal priceModifier;

    @JsonAlias("payment_method")
	@NotNull(message = "please specify valid payment method")
    private PaymentMethod paymentMethod;

    @NotNull(message = "date time should not be null")
	@DateTimeFormat(pattern = Constants.DATE_FORMAT)
	@JsonAlias("datetime")
    private ZonedDateTime dateTime;
}
