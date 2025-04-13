package com.devstack.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @Column(name="order_id", unique=true, nullable=false, length = 80)
    private String orderId;
    @Column(name="order_date", nullable=false, columnDefinition = "DATETIME")
    private Date orderDate;
    @Column(name="total_amount", nullable=false, precision=10, scale=2)
    private double totalAmount;
    @Column(name="user_id", nullable=false, length=80)
    private String userId;
    @Column(name="remark", length=750)
    private String remark;
    //===================
    @OneToMany(mappedBy = "customerOrder")
    private Set<OrderDetail> products = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

}
