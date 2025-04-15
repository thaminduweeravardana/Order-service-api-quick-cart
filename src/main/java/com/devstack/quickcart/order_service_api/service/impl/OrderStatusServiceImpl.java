package com.devstack.quickcart.order_service_api.service.impl;

import com.devstack.quickcart.order_service_api.dto.request.OrderStatusRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderStatusPaginateDto;
import com.devstack.quickcart.order_service_api.service.OrderStatusService;

public class OrderStatusServiceImpl implements OrderStatusService {
    @Override
    public void createStatus(OrderStatusRequestDto requestDto) {

    }

    @Override
    public void updateStatus(OrderStatusRequestDto requestDto, String statusId) {

    }

    @Override
    public OrderStatusResponseDto findOrderById(String statusId) {
        return null;
    }

    @Override
    public void deleteById(String statusId) {

    }

    @Override
    public OrderStatusPaginateDto searchAll(int page, int size) {
        return null;
    }
}
