
package Model;


public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String designation;

    //Constructor
    public User() {
    }

    public User(int userID, String firstName, String lastName, String userName, String password, String designation) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.designation = designation;
    }
    // Constructor with only userName and password
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getUserID() { return userID;}
    public void setUserID(int userID) { this.userID = userID;}
        
    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName;}
        
    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}
        
    public String getUserName() { return userName;}
    public void setUserName(String userName) { this.userName = userName;}
        
    public String getPassword() { return password;    }
    public void setPassword(String password) { this.password = password;}
        
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

}
