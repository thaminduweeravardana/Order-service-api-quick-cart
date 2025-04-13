package com.devstack.quickcart.order_service_api.service;

import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderDetailResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderDetailPaginateDto;

public interface OrderDetailsService {
    public void createOrderDetail(OrderDetailRequestDto requestDto);
    public void updateOrderDetail(OrderDetailRequestDto requestDto,String detailId);
    public OrderDetailResponseDto findOrderDetailById(String detailId);
    public void deleteById(String detailId);
    public OrderDetailPaginateDto searchAll(int page,int size);
}
