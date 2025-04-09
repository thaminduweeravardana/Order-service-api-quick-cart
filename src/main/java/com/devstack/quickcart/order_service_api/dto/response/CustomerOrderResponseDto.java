package com.devstack.quickcart.order_service_api.dto.response;

import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderResponseDto {
    private String orderId;
    private Date orderDate;
    private double totalAmount;
    private String userId;
    private String remark;
    private String status;
    private ArrayList<OrderDetailResponseDto> orderDetails;
}
