/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asusx
 */

public  class Order {
      
    //VARIABLES
    public int orderID;
    public String status;
    public float amount;
    public int userID;
    public String paymentMethod;
    public String payStatus;
    
    //CONSTRUCTOR
    public Order(int orderID, String status, float amount, int userID, String paymentMethod, String payStatus) { this.orderID = orderID;    
        this.status = status;
        this.amount = amount;
        this.userID = userID;
        this.paymentMethod = paymentMethod;
        this.payStatus = payStatus;
}

    //GETTER AND SETTER
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID;}

    public String getStatus() {return status;}
    public void setStatus(String status) { this.status = status;}

    public float getAmount() { return amount;}
    public void setAmount(float amount) {   this.amount = amount;}

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        
    public String getPayStatus() { return payStatus;}
    public void setPayStatus(String payStatus) { this.payStatus = payStatus; }
     
}
