package com.devstack.quickcart.order_service_api.service.impl;

import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderDetailResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderDetailPaginateDto;
import com.devstack.quickcart.order_service_api.entity.CustomerOrder;
import com.devstack.quickcart.order_service_api.entity.OrderDetail;
import com.devstack.quickcart.order_service_api.exception.EntryNotFoundException;
import com.devstack.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.devstack.quickcart.order_service_api.repo.OrderDetailRepo;
import com.devstack.quickcart.order_service_api.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailRepo orderDetailRepo;
    private final CustomerOrderRepo customerOrderRepo;

    @Override
    public void createOrderDetail(OrderDetailRequestDto requestDto) {
        CustomerOrder customerOrder = customerOrderRepo.findByOrderId(requestDto.getProductId())
                .orElseThrow(() -> new EntryNotFoundException("Order Not Found. so you can't place an order details please contact admin"));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString());
        orderDetail.setProductId(requestDto.getProductId());
        orderDetail.setCustomerOrder(customerOrder);
        orderDetail.setQty(requestDto.getQty());
        orderDetail.setUnitPrice(requestDto.getUnitPrice());
        orderDetail.setDiscount(requestDto.getDiscount());

        orderDetailRepo.save(orderDetail);
    }

    @Override
    public void updateOrderDetail(OrderDetailRequestDto requestDto, String detailId) {
        OrderDetail orderDetail = orderDetailRepo.findById(detailId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order details not found with %s", detailId)));
        orderDetail.setQty(requestDto.getQty());
        orderDetail.setUnitPrice(requestDto.getUnitPrice());
        orderDetail.setDiscount(requestDto.getDiscount());

        orderDetailRepo.save(orderDetail);
    }

    @Override
    public OrderDetailResponseDto findOrderDetailById(String detailId) {
        OrderDetail orderDetail = orderDetailRepo
                .findById(detailId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order details not found %s",detailId)));
        return toOrderDetailResponseDto(orderDetail);
    }

    @Override
    public void deleteById(String detailId) {
        OrderDetail orderDetail = orderDetailRepo
                .findById(detailId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order details not found %s",detailId)));
        orderDetailRepo.delete(orderDetail);
    }

    @Override
    public OrderDetailPaginateDto searchAll(int page, int size) {
        return OrderDetailPaginateDto.builder()
                .count(
                        orderDetailRepo.searchCount()
                )
                .dataList(
                        orderDetailRepo.searchAll(PageRequest.of(page,size))
                                .stream().map(this::toOrderDetailResponseDto).collect(Collectors.toList())
                )
                .build();
    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail){
        if (orderDetail == null) {
            return null;
        }
        return OrderDetailResponseDto.builder()
                .detailId(orderDetail.getDetailId())
                .qty(orderDetail.getQty())
                .discount(orderDetail.getDiscount())
                .productId(orderDetail.getProductId())
                .unitPrice(orderDetail.getUnitPrice())
                .build();
    }
}
