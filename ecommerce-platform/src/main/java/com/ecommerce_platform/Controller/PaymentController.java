package com.ecommerce_platform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_platform.Service.PaymentService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestParam Long orderId) {
        try {
            return ResponseEntity.ok(paymentService.createPaymentIntent(orderId));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed");
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestParam Long orderId) {
        paymentService.confirmPayment(orderId);
        return ResponseEntity.ok("Payment confirmed, order marked as PAID.");
    }
}
