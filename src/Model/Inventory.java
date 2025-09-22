
package Model;


public class Inventory {
    public String Name;
    public int stock;
    public int cakeId;

    public Inventory() {
    }

    public Inventory( int cakeId, String Name, int stock ) {
        this.cakeId = cakeId;
        this.Name = Name;
        this.stock = stock;  
    }
    public int getCakeId() { return cakeId; }
    public void setCakeId(int cakeId) { this.cakeId = cakeId; }
        
    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }
        
    //public void setInventoryId(int InventoryId) { this.InventoryId = InventoryId; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
      
}
