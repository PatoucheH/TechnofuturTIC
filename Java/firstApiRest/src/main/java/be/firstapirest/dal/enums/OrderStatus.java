package be.firstapirest.dal.enums;

public enum OrderStatus {
    PENDING("Pendding"),
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private final String value;
    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
