package com.devstack.quickcart.order_service_api.service.impl;

import com.devstack.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.quickcart.order_service_api.entity.CustomerOrder;
import com.devstack.quickcart.order_service_api.entity.OrderDetail;
import com.devstack.quickcart.order_service_api.entity.OrderStatus;
import com.devstack.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.devstack.quickcart.order_service_api.repo.OrderStatusRepo;
import com.devstack.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;

    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {
        OrderStatus orderStatus = orderStatusRepo.findByStatus("PENDING").orElseThrow(() -> new RuntimeException("Order Status Not Found. so you can't place an order please contact admin"));

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setRemark("");
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());
        customerOrder.setOrderStatus(orderStatus);
        customerOrder.setProducts(
                requestDto.getOrderDetails().stream().map(e -> createOrderDetail(e, customerOrder)).collect(Collectors.toSet())
        );
        customerOrderRepo.save(customerOrder);

    }
    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if (requestDto==null){
            return null;
        }
        return OrderDetail.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(requestDto.getUnitPrice())
                .discount(requestDto.getDiscount())
                .qty(requestDto.getQty())
                .customerOrder(order)
                .build();
    }
}
