
package Model;

import java.time.LocalDate;

public class PlaceOrder {
    public LocalDate orderDate;
    public int orderID;
    public int customerID;

    //CONSTRUCTOR
    public PlaceOrder() {
    }

    public PlaceOrder(LocalDate orderDate, int orderID, int customerID) {
        this.orderDate = orderDate;
        this.orderID = orderID;
        this.customerID = customerID;
    }

    //GETTER AND SETTER
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
        
    public int getOrderID() { return orderID;}
    public void getOrderID(int userID) { this.orderID = userID;}
    
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
        
}

