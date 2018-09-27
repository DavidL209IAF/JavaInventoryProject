/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.model;

import static davidlopez.model.Inventory.checkIfInteger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.DoubleProperty;



/**
 *
 * @author David
 */
public class Product {
    
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    protected final IntegerProperty productID;
    protected final StringProperty name;
    protected final DoubleProperty price;
    protected final IntegerProperty InStock;
    protected final IntegerProperty min;
    protected final IntegerProperty max;
    
    public Product() {
        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        InStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    
    public void setProductID(Integer productID) {
        this.productID.set(productID);
    }
    
    public void setProductName(String name) {
        this.name.set(name);
    }
    
    public void setProductPrice(Double price) {
        this.price.set(price);
    }
    
    public void setProductInStock(Integer InStock) {
        this.InStock.set(InStock);
    }
    public void setProductMin(Integer min) {
        this.min.set(min);
    }
    
    public void setProductMax(Integer max) {
        this.max.set(max);
    }
    
    public Integer getProductID(){
        return this.productID.get();
    }    
    
  
    public String getProductName() {
        return this.name.get();
    }
   
    public Double getProductPrice() {
        return this.price.get();
    }
    
    public Integer getProductInStock() {
        return this.InStock.get();
    }
    
    public Integer getProductMin() {
        return this.min.get();
    }
    
    public Integer getProductMax() {
        return this.max.get();
    }
    
    public ObservableList getProductParts() {
        return associatedParts;
    }
    public IntegerProperty productIDProperty() {
        return productID;
    }

    public StringProperty productNameProperty() {
        return name;
    }

    public DoubleProperty productPriceProperty() {
        return price;
    }

    public IntegerProperty productInvProperty() {
        return InStock;
    }

    public void addAssociatedPart(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
        
    }
    
    
    private void removeAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
    
    public static ObservableList<Part> getassociatedParts() {
        return associatedParts;
    }
    
    public Double getAssociatedPartsPrice() {
       
          return this.price.get();
    }
      
     public static int productParts(Product product) {
         if (associatedParts.isEmpty()) {
             return -1;
         } else {
             return 1;
         }
     }
    
    public static String productValidation(String productName, int productMin, int productMax, int productInv, double productPrice, ObservableList<Part> associatedParts, String productError) {
        int nameLength = productName.length();
        double partsPriceTotal = 0.00;
        for (int i = 0; i < associatedParts.size(); i++) {
            partsPriceTotal = partsPriceTotal + associatedParts.get(i).getPrice();
        }
        if (nameLength == 0) {
            productError = "Name field is blank.";
        }
        if (productInv < 1) {
            productError = "Inventory must be greater than 0.";
        }
        if (productPrice < 1) {
            productError = "Part price must be greater than $0.00";
        }
        if (productMin > productMax) {
            productError = "Inventory Min must be less than the Max.";
        }
        if (productInv < productMin || productInv > productMax) {
            productError = "Part inventory must be between Min and Max values.";
        }
        if (associatedParts.isEmpty()) {
            productError = "Product must contain at least one part.";
        }
        if (partsPriceTotal > productPrice) {
            productError = "Product price must be greater than the total cost of associated parts.";
        }
        return productError;
    }
    
  
    
}
