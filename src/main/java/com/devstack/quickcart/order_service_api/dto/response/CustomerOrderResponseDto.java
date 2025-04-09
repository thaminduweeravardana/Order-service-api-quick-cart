package com.devstack.quickcart.order_service_api.dto.response;

import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderResponseDto {
    private String orderId;
    private Date orderDate;
    private double totalAmount;
    private String userId;
    private String remark;
    private String status;
    private List<OrderDetailResponseDto> orderDetails;
}
