package com.devstack.quickcart.order_service_api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponseDto {
    private String detailId;
    private String productId;
    private int qty;
    private double unitPrice;
    private double discount;
}
