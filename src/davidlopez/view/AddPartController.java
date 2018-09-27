/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.view;

import davidlopez.DavidLopez;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import davidlopez.model.Part;
import davidlopez.model.Inventory;
import davidlopez.model.Inhouse;
import static davidlopez.model.Inventory.getCompletePartInventory;
import davidlopez.model.Outsourced;
import davidlopez.model.Product;
import java.util.Optional;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author David
 */


public class AddPartController implements Initializable {
@FXML
    private DavidLopez DavidLopez;
    private Stage dialogStage;
    private boolean okClicked = false;
    
    @FXML
    private RadioButton addPartInHouseRadio;
    private RadioButton addPartOutsourcedRadio;
    
    @FXML
    private Label lbl;
    @FXML
    private TextField addPartMachineID;
    @FXML
    private TextField addPartCompanyName;
    @FXML 
    private TextField addPartIDField;
    @FXML
    private TextField addPartNameField;
    @FXML
    private TextField addPartInvField;
    @FXML
    private TextField addPartPriceField;
    @FXML
    private TextField addPartMaxField;
    @FXML
    private TextField addPartMinField;
    private Part part;
    private String partError = new String();
    private boolean isInhouse;
  
   
  
  
   
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
     public boolean isOkClicked() {

        return okClicked;
    }
     @FXML
     
     private void addPartSaveButtonHandler(ActionEvent event) throws Exception {
         //make if statement for radio on inhouse or outourced by makinh outsourced default constructor like inHouse
             int finalPartID = Integer.parseInt(addPartIDField.getText());
             String finalName = addPartNameField.getText();
             int finalInv = Integer.parseInt(addPartInvField.getText());
             Double finalPrice = Double.parseDouble(addPartPriceField.getText());
             int finalMax = Integer.parseInt(addPartMaxField.getText());
             int finalMin = Integer.parseInt(addPartMinField.getText());
             int finalMachineID = 0;
             String finalCompanyName = "";
             
        
          
        try {
            partError = Part.partValidation(finalName, finalMin, finalMax, finalInv, finalPrice, partError);
            if (partError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part");
                alert.setHeaderText("Error");
                alert.setContentText(partError);
                alert.showAndWait();
                partError = "";
            } else {
                if (isInhouse == true) {
                    Inhouse Part = new Inhouse();
                    
                    finalMachineID = Integer.parseInt(addPartMachineID.getText());
                    Part.setPartID(finalPartID);
                    Part.setName(finalName);
                    Part.setPrice(finalPrice);
                    Part.setInStock(finalInv);
                    Part.setMin(finalMin);
                    Part.setMax(finalMax);
                    Part.setMachineID(finalMachineID);
                    Inventory.addPart(Part);
                } else {
                    Outsourced Part = new Outsourced();
                    finalCompanyName = addPartCompanyName.getText();
                    Part.setPartID(finalPartID);
                    Part.setName(finalName);
                    Part.setPrice(finalPrice);
                    Part.setInStock(finalInv);
                    Part.setMin(finalMin);
                    Part.setMax(finalMax);
                    Part.setCompanyName(finalCompanyName);
                    Inventory.addPart(Part);
                }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Success");
            alert.setContentText("Part added successfully.");
            alert.showAndWait();
            dialogStage.close();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part");
            alert.setHeaderText("Error");
            alert.setContentText("Form contains blank fields.");
            alert.showAndWait();
        }
    }

  
     @FXML
     private void radioButtonSelection(ActionEvent event)  {
         String Label = "";
         if (addPartInHouseRadio.isSelected()) {
             Label = "Machine ID";
             addPartMachineID.setVisible(true);
             addPartCompanyName.setVisible(false);
             
         }
         else  {
             Label = "Company Name";
             addPartCompanyName.setVisible(true);
             addPartMachineID.setVisible(false);
         
         }
         lbl.setText(Label);
     }
     
     @FXML
     private void addPartCancelButtonHandler(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to cancel adding a part?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
         dialogStage.close();
         } else {
            System.out.println("Action Cancelled.");
        }
     }
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
     @FXML
     public void setMainApp(DavidLopez DavidLopez) {
        this.DavidLopez = DavidLopez;

    }
};