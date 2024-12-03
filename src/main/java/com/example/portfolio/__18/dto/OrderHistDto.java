package com.example.portfolio.__18.dto;

import com.example.portfolio.__18.constant.OrderStatus;
import com.example.portfolio.__18.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {
    
    private Long orderId;
    
    private String orderDate;
    
    private OrderStatus orderStatus;
    //주문 상품 리스트
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();
    //orderItemDto 객체를 주문 상품 리스트에 추가하는 메소드
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
    public OrderHistDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        this.orderStatus = order.getOrderStatus();
    }
}
