package com.educandoweb.course.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private OrderStatus(int code) {
    }

    public int getCode(){
        return code;
    }

    public static OrderStatus valueof(int code){
        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.getCode() == code)
                return orderStatus;
        }
        throw new IllegalStateException("INVALID ORDER STATUS");
    }
}
