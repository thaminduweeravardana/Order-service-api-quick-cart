package com.devstack.quickcart.order_service_api.dto.response.paginate;

import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusPaginateDto {
    private long count;
    private List<OrderStatusResponseDto> dataList;
}
