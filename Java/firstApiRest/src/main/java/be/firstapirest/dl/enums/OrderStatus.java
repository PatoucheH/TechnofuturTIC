package be.firstapirest.dl.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Pendding"),
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private final String value;
    OrderStatus(String value) {
        this.value = value;
    }

}
