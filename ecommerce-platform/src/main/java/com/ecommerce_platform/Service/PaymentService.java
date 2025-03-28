package com.ecommerce_platform.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce_platform.Entity.Order;
import com.ecommerce_platform.Enum.OrderStatus;
import com.ecommerce_platform.Repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;



@Service
public class PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private final OrderRepository orderRepo;

    public PaymentService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String createPaymentIntent(Long orderId) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(order.getTotalPrice().multiply(BigDecimal.valueOf(100)).longValue()) // Convert to cents
            .setCurrency("usd")
            .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getClientSecret(); // Return client secret for front-end processing
    }

    public void confirmPayment(Long orderId) {
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.PAID);
        orderRepo.save(order);
    }
}
