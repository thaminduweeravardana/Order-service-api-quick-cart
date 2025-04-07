package com.devstack.quickcart.order_service_api.repo;

import com.devstack.quickcart.order_service_api.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {
}
