package com.devstack.quickcart.order_service_api.service.impl;

import com.devstack.quickcart.order_service_api.dto.request.OrderStatusRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderStatusPaginateDto;
import com.devstack.quickcart.order_service_api.entity.CustomerOrder;
import com.devstack.quickcart.order_service_api.entity.OrderStatus;
import com.devstack.quickcart.order_service_api.exception.EntryNotFoundException;
import com.devstack.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.devstack.quickcart.order_service_api.repo.OrderStatusRepo;
import com.devstack.quickcart.order_service_api.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepo orderStatusRepo;
    private final CustomerOrderRepo customerOrderRepo;

    @Override
    public void createStatus(OrderStatusRequestDto requestDto) {
        Set<CustomerOrder> customerOrderSet = customerOrderRepo.findByOrderId(requestDto.getOrderId())
                .stream().collect(Collectors.toSet());

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatusId(UUID.randomUUID().toString());
        orderStatus.setStatus(requestDto.getStatus());
        orderStatus.setCustomerOrders(customerOrderSet);
        orderStatusRepo.save(orderStatus);
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
