package com.devstack.quickcart.order_service_api.repo;


import com.devstack.quickcart.order_service_api.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {
    @Query(nativeQuery = true, value = "SELECT COUNT(detail_id) FROM order_detail")
    public long searchCount();

    @Query(nativeQuery = true, value = "SELECT * FROM order_detail")
    public Page<OrderDetail> searchAll(Pageable pageable);
}
