package com.devstack.quickcart.order_service_api.api;

import com.devstack.quickcart.order_service_api.dto.request.OrderStatusRequestDto;
import com.devstack.quickcart.order_service_api.service.OrderStatusService;
import com.devstack.quickcart.order_service_api.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order-status")
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @PostMapping("/business")
    public ResponseEntity<StandardResponseDto> create(@RequestBody OrderStatusRequestDto request){
        orderStatusService.createStatus(request);
         return new ResponseEntity<>(
                new StandardResponseDto(
                        201,"Status has been created",null
                ), HttpStatus.CREATED
         );
    }

    @GetMapping("/visitors/find-by-id/{id}")
    public ResponseEntity<StandardResponseDto> findById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"Order status details",orderStatusService.findStatusById(id)
                ),HttpStatus.OK
        );
    }
}
