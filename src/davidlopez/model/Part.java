/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
/**
 *
 * @author David
 */
public abstract class Part {
    
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty InStock;
    private final IntegerProperty min;
    private final IntegerProperty max;



public Part() {
    partID = new SimpleIntegerProperty();
    name = new SimpleStringProperty();
    price = new SimpleDoubleProperty();
    InStock = new SimpleIntegerProperty();
    min = new SimpleIntegerProperty();
    max = new SimpleIntegerProperty();
}


public void setPartID(Integer partID)
{
    this.partID.set(partID);
}

public void setName(String name) 
{
    this.name.set(name);
}
public void setPrice(Double price) {
    this.price.set(price);
}

public void setInStock(Integer InStock) {
    this.InStock.set(InStock);
}

public void setMin(Integer min) {
    this.min.set(min);
}

public void setMax(Integer max) {
    this.max.set(max);
}



 public Integer getPartID()
   {
       return this.partID.get();
   }

public String getName()
   {
       return this.name.get();
   }

public Double getPrice()
   {
       return this.price.get();
   }

public Integer getInStock()
   {
       return this.InStock.get();
   }

public Integer getMin()
   {
       return this.min.get();
   }

public Integer getMax()
   {
       return this.max.get();
   }



   public IntegerProperty partIDProperty() {
        return partID;
    }

    public StringProperty partNameProperty() {
        return name;
    }

    public DoubleProperty partPriceProperty() {
        return price;
    }

    public IntegerProperty partInvProperty() {
        return InStock;
    }
    
    public static String partValidation(String partName, int partMin, int partMax, int partInv, double partPrice, String partError) {
        int nameLength = partName.length();
       
        if (nameLength == 0) {
            partError = "Name field is blank.";
        }
        if (partInv < 1) {
            partError = "Inventory must be greater than 0.";
        }
        if (partPrice < 1) {
            partError = "Part price must be greater than $0.00";
        }
        if (partMin > partMax) {
            partError = "Inventory Min must be less than the Max.";
        }
        if (partInv < partMin || partInv > partMax) {
            partError = "Part inventory must be between Min and Max values.";
        }
        return partError;
    }













}

