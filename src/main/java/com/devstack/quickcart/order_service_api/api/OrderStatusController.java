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

    @PutMapping("/business/update-status/{id}")
    public ResponseEntity<StandardResponseDto> updateStatus(@RequestBody OrderStatusRequestDto request,@PathVariable String id){
        orderStatusService.updateStatus(request, id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"Order status has been updated",null
                ),HttpStatus.OK
        );
    }

    @DeleteMapping("/business/delete-by-id/{id}")
    public ResponseEntity<StandardResponseDto> deleteBYId(@PathVariable String id){
        orderStatusService.deleteById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204,"Order status has been deleted",null
                ),HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/visitors/search-all")
    public ResponseEntity<StandardResponseDto> searchAll(@RequestParam String searchText, @RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,"Order status list",orderStatusService.searchAll(searchText,page,size)
                ),HttpStatus.OK
        );
    }
}
