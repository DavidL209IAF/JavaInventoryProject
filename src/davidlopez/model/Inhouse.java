/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import davidlopez.model.Part;
/**
 *
 * @author David
 */
public class Inhouse extends Part {
    
private final SimpleIntegerProperty machineID;
    
public Inhouse() {
   machineID = new SimpleIntegerProperty();  
}

public void setMachineID(Integer machineID) {
    this.machineID.set(machineID);
}

public Integer getMachineID()
   {
       return this.machineID.get();
   }

    
    
    
    
    
    
    
    
}
