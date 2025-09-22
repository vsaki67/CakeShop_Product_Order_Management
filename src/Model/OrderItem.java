/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asusx
 */
public  class OrderItem {
    public int orderItem;
    public int quantity;
    public float price;
    public int orderID;
    public int idCake;
    

    //CONSTRUCTOR
    public OrderItem() {
    }

    public OrderItem(int orderItem, int quantity, float price, int orderID, int idCake) {
        this.orderItem = orderItem;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
        this.idCake = idCake;
    }
    
    //GETTER AND SETTER
    public int getOrderItem() { return orderItem;}
    public void setOrderItem(int orderItem) { this.orderItem = orderItem;}
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getOrderID() { return orderID;    }
    public void setOrderID(int orderID) { this.orderID = orderID;}

    public int getIdCake() { return idCake; }
    public void setIdCake(int idCake) {this.idCake = idCake; }
 
}
