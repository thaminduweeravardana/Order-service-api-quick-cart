package com.devstack.quickcart.order_service_api.service;

import com.devstack.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.CustomerOrderPaginateDto;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public CustomerOrderResponseDto findOrderById(String orderId);
    public void deleteById(String orderId);
    public CustomerOrderPaginateDto searchAll(String searchText, int page, int size);
}
