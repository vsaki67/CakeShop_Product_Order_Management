package Controller;

import Model.Order;
import Model.Order_DAO;
import Model.OrderItem;
import Model.OrderItem_DAO;
import Model.PlaceOrder;
import Model.PlaceOrder_DAO;
import Model.Customer;
import Model.Customer_DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderController {
    private final Connection conn;
    
    private final Order_DAO orderDAO;
    private final Customer_DAO customerDAO;
    private final OrderItem_DAO orderItemDAO;
    private final PlaceOrder_DAO placeOrderDAO;

    public OrderController() {
        this.conn = DataBase_Connection.dbConnection(); 
        this.orderDAO = new Order_DAO();  // No connection passed
        this.customerDAO = new Customer_DAO();
        this.orderItemDAO = new OrderItem_DAO();
        this.placeOrderDAO = new PlaceOrder_DAO();
    }
//ORDER TABLE
        // Method to create an order
    public boolean createOrder(int orderID, String status, float amount, int userID, String paymentMethod, String payStatus) throws SQLException {
        Order order = new Order(orderID, status, amount, userID,  paymentMethod,  payStatus);
        orderDAO.addOrder(order);
        return true;
    }
        // Method to update an order
    public void updateOrder(int orderID, String status, float amount, int userID, String paymentMethod, String payStatus) throws SQLException {
        orderDAO.updateOrder(orderID, status, amount, userID, paymentMethod,  payStatus);
    }
    
        // Method to get an order by ID
    public Order getOrderById(int orderID) throws SQLException {
       return orderDAO.getOrderById(orderID);
    }

    // Create a new order with items
    public void createOrder(int orderID, int customerID, List<OrderItem> items, LocalDate orderDate, float totalAmount,  String paymentMethod, String payStatus) throws SQLException {
        // Check if customer exists
        if (customerDAO.getCustomerById(customerID) == null) {
            throw new SQLException("Customer not found!");
        }

        // Create the order in the orders table
        Order order = new Order(orderID, "Pending", totalAmount, customerID, paymentMethod, payStatus);
        orderDAO.addOrder(order);

        // Add items to OrderItem table
        for (OrderItem item : items) {
            item.setOrderID(orderID);  // Set the order ID for each item
            orderItemDAO.addOrderItem(item);
        }

        // Insert into PlaceOrder table
        PlaceOrder placeOrder = new PlaceOrder(orderDate, customerID, orderID);
        placeOrderDAO.addPlaceOrder(placeOrder);
    }

    // Update an existing order with items
    public void updateOrder(int orderID, List<OrderItem> items, float totalAmount, String status, int userID, String paymentMethod, String payStatus) throws SQLException {
        orderDAO.updateOrder(orderID, status, totalAmount, userID, paymentMethod, payStatus);  // Correct signature

        // Remove existing items for the order
        orderItemDAO.deleteOrderItemsByOrderID(orderID);

        // Add updated items
        for (OrderItem item : items) {
            item.setOrderID(orderID);
            orderItemDAO.addOrderItem(item);
        }
    }
    public boolean deleteOrder(int orderID) {
        try {
            conn.setAutoCommit(false);

            orderItemDAO.deleteOrderItemsByOrderID(orderID);
            placeOrderDAO.deletePlaceOrder(orderID);
            orderDAO.deleteOrder(orderID);

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, "Rollback failed: " + rollbackEx.getMessage());
            }
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, "Error deleting order: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException closeEx) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, "Error resetting auto-commit: " + closeEx.getMessage());
            }
        }
    }
    // Delete an order along with items
   // public void deleteOrderWithItems(int orderID) throws SQLException {
        // Remove order items first
     //   orderItemDAO.deleteOrderItemsByOrderID(orderID);

        // Remove from PlaceOrder table
       // placeOrderDAO.deletePlaceOrder(orderID);

        // Remove from Order table
 //       orderDAO.deleteOrder(orderID);
   // }
    
//ORDER ITEM TABLE
    public boolean addOrderItem(int orderItem, int quantity, float price, int orderID, int idCake){
        OrderItem orderitem = new OrderItem (orderItem, quantity, price, orderID, idCake);
        try {
            orderItemDAO.addOrderItem(orderitem);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // Retrieve order items by order ID
    public List<OrderItem> getOrderItemsByOrderID(int orderID) throws SQLException {
        return orderItemDAO.getOrderItemsByOrderID(orderID);
    }

    
    // Retrieve customer by order ID
    public Customer getCustomerByOrderID(int orderID) throws SQLException {
        PlaceOrder placeOrder = placeOrderDAO.getPlaceOrderByOrderId(orderID);
        return customerDAO.getCustomerById(placeOrder.getCustomerID());
    }
//CUSTOMER 
    public boolean addCustomer (int customerID, String cusName,int cNum){
        Customer customer = new Customer(customerID, cusName, cNum);
        customerDAO.addCustomer(customer);
        return true;
    }
    // Retrieve customer by Customer ID
    public Customer getCustomerById(int customerID) throws SQLException {
        return customerDAO.getCustomerById(customerID);
    }
    
    
//PLACE ORDER
    public boolean addPlaceOrder(LocalDate orderDate, int orderID, int cID) throws SQLException{
        PlaceOrder placeOrder = new PlaceOrder(orderDate, orderID, cID);
        placeOrderDAO.addPlaceOrder(placeOrder);
        return true;
    }
    
    
    // Method to retrieve PlaceOrder by order ID
    public PlaceOrder getPlaceOrderByOrderId(int orderID) throws SQLException {
        return placeOrderDAO.getPlaceOrderByOrderId(orderID);
    }
  
}



