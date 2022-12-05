package com.anymind.pos.validator;

import com.anymind.pos.exception.InvalidInputException;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

	public void validateDates(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
		if (startDateTime.isAfter(endDateTime)) {
			throw new InvalidInputException("startDateTime : " + startDateTime + " must be before endDateTime :" + endDateTime);
		}
	}
}
