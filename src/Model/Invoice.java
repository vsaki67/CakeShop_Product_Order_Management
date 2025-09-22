/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;


public class Invoice {
    private int invoiceID;
    //private int reportType;
    private LocalDate date;
    private float totalAmount;
    public int orderID;

    public Invoice() {
    }

    public Invoice(int invoiceID,  LocalDate date, float totalAmount, int orderID) {
        this.invoiceID = invoiceID;
        //this.reportType = reportType;
        this.date = date;
        this.totalAmount = totalAmount;
        this.orderID = orderID;
    }

    public int getInvoiceID() { return invoiceID;}
    public void setInvoiceID(int invoiceID) { this.invoiceID = invoiceID;}

    public  LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
        
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }

    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }
      
}
