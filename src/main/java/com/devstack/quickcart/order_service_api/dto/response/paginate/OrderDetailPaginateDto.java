package com.devstack.quickcart.order_service_api.dto.response.paginate;


import com.devstack.quickcart.order_service_api.dto.response.OrderDetailResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailPaginateDto {
    private long count;
    private List<OrderDetailResponseDto> dataList;
}
