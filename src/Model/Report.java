/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

public class Report {
    //private int reportId;
    private List<Product> products;
    private double totalsale;

    public Report(List<Product> products, double totalsale) {
        this.products = products;
        this.totalsale = totalsale;
    }

    public List<Product> getProducts() { return products;}
    public void setProducts(List<Product> products) { this.products = products;}

    public double getTotalsale() { return totalsale;}
    public void setTotalsale(double totalsale) { this.totalsale = totalsale; }

}
