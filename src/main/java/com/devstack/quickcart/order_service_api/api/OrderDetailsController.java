package com.devstack.quickcart.order_service_api.api;

import com.devstack.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.quickcart.order_service_api.service.OrderDetailsService;
import com.devstack.quickcart.order_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order-detail")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping("/business")
    public ResponseEntity<StandardResponseDto> create(@RequestBody OrderDetailRequestDto request){
        orderDetailsService.createOrderDetail(request);
        return new ResponseEntity<>(
              new StandardResponseDto(
                      201,"Order detail has been created",null
              ), HttpStatus.CREATED
        );
    }

    @GetMapping("/visitors/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"customer order details", orderDetailsService.findOrderDetailById(id)
                ),HttpStatus.OK
        );
    }


    @PutMapping("/business/update-order/{id}")
    public ResponseEntity<StandardResponseDto> updateOrder(@RequestBody OrderDetailRequestDto request, @PathVariable String id) {
        orderDetailsService.updateOrderDetail(request, id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"Order detail has been updated",null
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/business/delete-by-id/{id}")
    public ResponseEntity<StandardResponseDto> deleteById(@PathVariable String id) {
        orderDetailsService.deleteById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204,"Order detail has been deleted",null
                ), HttpStatus.NO_CONTENT
        );
    }


    @GetMapping("/visitors/search-all")
    public ResponseEntity<StandardResponseDto> searchAll(@RequestParam int page,@RequestParam int size) {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"customer order list",orderDetailsService.searchAll(page, size)
                ), HttpStatus.OK
        );
    }
}
