package com.devstack.quickcart.order_service_api.service.impl;


import com.devstack.quickcart.order_service_api.dto.request.OrderStatusRequestDto;
import com.devstack.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderDetailResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import com.devstack.quickcart.order_service_api.dto.response.paginate.OrderStatusPaginateDto;
import com.devstack.quickcart.order_service_api.entity.CustomerOrder;
import com.devstack.quickcart.order_service_api.entity.OrderDetail;
import com.devstack.quickcart.order_service_api.entity.OrderStatus;
import com.devstack.quickcart.order_service_api.exception.EntryNotFoundException;
import com.devstack.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.devstack.quickcart.order_service_api.repo.OrderStatusRepo;
import com.devstack.quickcart.order_service_api.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepo orderStatusRepo;
    private final CustomerOrderRepo customerOrderRepo;

    @Override
    public void createStatus(OrderStatusRequestDto requestDto) {
        CustomerOrder customerOrder = customerOrderRepo.findByOrderId(requestDto.getOrderId())
                .orElseThrow(() -> new EntryNotFoundException("Order Not Found. so you can't place an order details please contact admin"));

        OrderStatus orderStatus = new OrderStatus();
         orderStatus.setStatusId(UUID.randomUUID().toString());
         orderStatus.setStatus(requestDto.getStatus());
         orderStatus.setCustomerOrders(
                requestDto.getCustomerOrder().stream()
                        .map(e->createCustomerOrder(customerOrder)).collect(Collectors.toSet())
        );
        orderStatusRepo.save(orderStatus);
    }

    @Override
    public void updateStatus(OrderStatusRequestDto requestDto, String statusId) {
        OrderStatus orderStatus = orderStatusRepo.findById(statusId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order status not found with %s", statusId)));
        orderStatus.setStatus(requestDto.getStatus());
        orderStatusRepo.save(orderStatus);
    }

    @Override
    public OrderStatusResponseDto findStatusById(String statusId) {
        OrderStatus orderStatus = orderStatusRepo
                .findById(statusId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order status not found %s",statusId)));
        return toOrderStatusResponseDto(orderStatus);
    }

    @Override
    public void deleteById(String statusId) {
        OrderStatus orderStatus = orderStatusRepo.findById(statusId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order status not found %s", statusId)));
        orderStatusRepo.delete(orderStatus);
    }

    @Override
    public OrderStatusPaginateDto searchAll(String searchText, int page, int size) {
        return OrderStatusPaginateDto.builder()
                .count(
                        orderStatusRepo.searchCount(searchText)
                )
                .dataList(
                        orderStatusRepo.searchAll(searchText, PageRequest.of(page,size)).stream()
                                .map(this::toOrderStatusResponseDto).toList()
                )
                .build();
    }

    private CustomerOrder createCustomerOrder(CustomerOrder customerOrder){
        if(customerOrder == null){
            return null;
        }

        return CustomerOrder.builder()
                .orderId(UUID.randomUUID().toString())
                .orderDate(customerOrder.getOrderDate())
                .orderStatus(customerOrder.getOrderStatus())
                .products(customerOrder.getProducts())
                .remark(customerOrder.getRemark())
                .totalAmount(customerOrder.getTotalAmount())
                .userId(customerOrder.getUserId())
                .build();
    }

    private OrderStatusResponseDto toOrderStatusResponseDto(OrderStatus orderStatus){
       if(orderStatus == null){
           return null;
       }
       return  OrderStatusResponseDto.builder()
               .statusId(orderStatus.getStatusId())
               .status(orderStatus.getStatus())
               .customerOrders(orderStatus.getCustomerOrders().stream().map(this ::orderToResponseDto).toList())
               .build();
    }

    private CustomerOrderResponseDto orderToResponseDto(CustomerOrder customerOrder){
        if(customerOrder == null){
            return null;
        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .userId(customerOrder.getUserId())
                .totalAmount(customerOrder.getTotalAmount())
                .status(customerOrder.getOrderStatus().getStatus())
                .orderDetails(customerOrder.getProducts().stream().map(this::toOrderDetailResponseDto).toList())
                .remark(customerOrder.getRemark())
                .build();
    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        return OrderDetailResponseDto.builder()
                .productId(orderDetail.getProductId())
                .detailId(orderDetail.getDetailId())
                .discount(orderDetail.getDiscount())
                .qty(orderDetail.getQty())
                .unitPrice(orderDetail.getUnitPrice())
                .build();
    }

}

