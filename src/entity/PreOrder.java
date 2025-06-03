package entity;

import util.PreOrderUtils;

public class PreOrder{
    private String orderId;
    private String customerName;
    private String productName;
    private String model;
    private int quantity;
    private String orderTime;
    private String deliveryMethod;
    private String status;

    public PreOrder(String customerName, String productName, String model,int quantity, String orderTime, String deliveryMethod, String status) {
        this.orderId = PreOrderUtils.generateOrderId();
        this.customerName = customerName;
        this.productName = productName;
        this.model = model;
        this.quantity = quantity;
        this.orderTime = orderTime;
        this.deliveryMethod = deliveryMethod;
        this.status = status;
    }

    // Getter dan setter...

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}