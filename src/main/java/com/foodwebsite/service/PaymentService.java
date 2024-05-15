package com.foodwebsite.service;

import com.foodwebsite.model.Order;
import com.foodwebsite.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentResponse createPaymentLink(Order order) throws StripeException;
}
