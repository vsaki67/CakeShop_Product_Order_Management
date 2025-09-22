/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class Customer {
    private int customerID;
    private String name;
    private int contactDetails;
    
    //CONSTRUCTOR
    public Customer() {
    }

    public Customer(int customerID, String name, int contactDetails) {
        this.customerID = customerID;
        this.name = name;
        this.contactDetails = contactDetails;
    }
    
    //GETTER AND SETTER
    public int getCustomerID() { return customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;} 

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getContactDetails() {return contactDetails;}
    public void setContactDetails(int contactDetails) {this.contactDetails = contactDetails;}
    
}
