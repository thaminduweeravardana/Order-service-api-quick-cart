package com.devstack.quickcart.order_service_api.repo;

import com.devstack.quickcart.order_service_api.dto.response.OrderStatusResponseDto;
import com.devstack.quickcart.order_service_api.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM order_status WHERE status=?1")
    public Optional<OrderStatus>findByStatus(String status);

    @Query(nativeQuery = true, value = "SELECT COUNT(status_id) FROM order_status WHERE status LIKE %?1%")
    long searchCount(String searchText);

    @Query(nativeQuery = true, value = "SELECT * FROM order_status WHERE status LIKE %?1%")
    Page<OrderStatus> searchAll(String status, Pageable pageable);
}
