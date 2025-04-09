package com.devstack.quickcart.order_service_api.service;

import com.devstack.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public CustomerOrderResponseDto findOrderById(String orderId);
}
