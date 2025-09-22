/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asusx
 */
import java.time.LocalDate;

public class Product {
    public int cakeId;
    public String name;
    public String description;
    public float price;
    public String category;
    public LocalDate expiaryDate;
    public int qauntity;
    public int userID;
    
    //Constructor
    public Product() {
    }
    public Product(int cakeId, String name, String description, float price, String category,  int qauntity, LocalDate expiaryDate, int userID) {
        this.cakeId = cakeId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.qauntity = qauntity;
        this.expiaryDate = expiaryDate;
        this.userID  = userID;
    }
    public Product(int cakeId, String name, float price, int quantity) {
        this.cakeId = cakeId;
        this.name = name;
        this.price = price;
        this.qauntity = quantity;
    }
    
    public int getCakeId() { return cakeId;}
    public void setCakeId(int cakeId) { this.cakeId = cakeId;}
    
    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    
    public String getDescription() { return description; }
    public void setDescription(String description) {this.description = description;}

    public float getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}

    public String getCategory() {return category;}
    public void setCategory(String category) { this.category = category;}

    public LocalDate getExpiaryDate() {return expiaryDate;}
    public void setExpiaryDate(LocalDate expiaryDate) {this.expiaryDate = expiaryDate;}

    public int getQauntity() {return qauntity;}
    public void setQauntity(int qauntity) {this.qauntity = qauntity;}

    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    
}
