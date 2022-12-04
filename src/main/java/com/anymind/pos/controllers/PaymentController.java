package com.anymind.pos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anymind.pos.controllers.request.Request;
import com.anymind.pos.controllers.response.Response;
import com.anymind.pos.service.PaymentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    
    @PostMapping("/calculatePrice")
    public Response calculateRates(@RequestBody @Valid Request request) {

        return paymentService.calculate(request);
    }
    
    @GetMapping("/message")
    public String getMessage() {
        return "Hello";
    }
}
