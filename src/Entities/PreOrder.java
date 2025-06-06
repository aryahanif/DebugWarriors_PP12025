package Entities;

public class PreOrder {
    private String customerName;
    private String contactNumber;

    // Constructor
    public PreOrder(String name, String contact) {
        this.customerName = name;
        this.contactNumber = contact;
    }

    // Getters
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }


    // Setters
    public void setCustomerName(String name) { this.customerName = name; }
    public void setContactNumber(String contact) { this.contactNumber = contact; }

    @Override
    public String toString() {
        return "Preorder{" + "customerName='" + customerName + '\'' + ", contactNumber='" + contactNumber + '\'' + '}';
    }
}
