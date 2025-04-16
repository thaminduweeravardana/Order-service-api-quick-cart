package com.devstack.quickcart.order_service_api.service;

import com.devstack.quickcart.order_service_api.dto.request.OrderStatusRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderStatusPaginateDto;

public interface OrderStatusService {
    public void createStatus(OrderStatusRequestDto requestDto);
    public void updateStatus(OrderStatusRequestDto requestDto, String statusId);
    public OrderStatusResponseDto findStatusById(String statusId);
    public void deleteById(String statusId);
    public OrderStatusPaginateDto searchAll(String searchText, int page, int size);
}
