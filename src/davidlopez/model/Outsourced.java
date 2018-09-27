/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.model;
;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import davidlopez.model.Part;
/*
 * @author David
 */
public class Outsourced extends Part {
    
private final StringProperty companyName;


public Outsourced () {
    companyName = new SimpleStringProperty();
}

public void setCompanyName(String companyName) {
    this.companyName.set(companyName);
}

public String getCompanyName()
   {
       return this.companyName.get();
   }

}
