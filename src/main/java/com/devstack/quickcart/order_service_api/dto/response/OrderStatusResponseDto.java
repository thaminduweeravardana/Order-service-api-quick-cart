package com.devstack.quickcart.order_service_api.dto.response;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusResponseDto {
    private String statusId;
    private String status;
    private List<CustomerOrderResponseDto> customerOrders;
}
