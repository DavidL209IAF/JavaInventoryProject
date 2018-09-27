/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.view;

import davidlopez.DavidLopez;
import static davidlopez.model.Inventory.getCompletePartInventory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import static davidlopez.view.MainController.partToModifyIndex;
import davidlopez.model.Part;
import davidlopez.model.Inhouse;
import davidlopez.model.Inventory;
import davidlopez.model.Outsourced;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static davidlopez.model.Inventory.getCompletePartInventory;
import java.util.Optional;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author David
 */





public class ModifyPartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private DavidLopez DavidLopez;
    private Stage dialogStage;
    private boolean okClicked = false;
    int modifyPartIndex = partToModifyIndex();
    
    
      @FXML
    private RadioButton modifyPartInHouseRadio;
      @FXML
    private RadioButton modifyPartOutsourcedRadio;
    
    @FXML
    private Label lbl;
   
    @FXML
    private TextField modifyPartInHouse;
    @FXML
    private TextField modifyPartOutsourced;
    
    @FXML
    private TextField modifyPartIDField;
    @FXML
    private TextField modifyPartNameField;
    @FXML
    private TextField modifyPartInvField;
    @FXML
    private TextField modifyPartPriceField;
    @FXML
    private TextField modifyPartMinField;
    @FXML
    private TextField modifyPartMaxField;
    private String partError = new String();
    private boolean isInhouse;
    
  
    
     
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
     
      public boolean isOkClicked() {

        return okClicked;
    }
      
      @FXML
     private void radioButtonSelection(ActionEvent event)  {
         String Label = "";
         if (modifyPartInHouseRadio.isSelected()) {
             Label = "Machine ID";
             modifyPartInHouse.setVisible(true);
             modifyPartOutsourced.setVisible(false);
             
         }
         else  {
             Label = "Company Name";
             modifyPartInHouse.setVisible(false);
             modifyPartOutsourced.setVisible(true);
         }
         lbl.setText(Label);
     }
    @FXML
     
     private void modifyPartSaveButtonHandler(ActionEvent event) throws Exception {
             int finalPartID = Integer.parseInt(modifyPartIDField.getText());
             String finalName = modifyPartNameField.getText();
             int finalInv = Integer.parseInt(modifyPartInvField.getText());
             Double finalPrice = Double.parseDouble(modifyPartPriceField.getText());
             int finalMax = Integer.parseInt(modifyPartMaxField.getText());
             int finalMin = Integer.parseInt(modifyPartMinField.getText());
             
             
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
             
        
          if (modifyPartInHouseRadio.isSelected()) {
          Inhouse part = new Inhouse();
          part.setPartID(finalPartID);
          part.setName(finalName);
          part.setPrice(finalPrice);
          part.setInStock(finalInv);
          part.setMin(finalMin);
          part.setMax(finalMax);
          int finalMachineID = Integer.parseInt(modifyPartInHouse.getText());
          part.setMachineID(finalMachineID);
          
          Inventory.updatePart(modifyPartIndex, part);
          }
          else if (modifyPartOutsourcedRadio.isSelected()) {
           
          Outsourced part = new Outsourced();
          part.setPartID(finalPartID);
          part.setName(finalName);
          part.setPrice(finalPrice);
          part.setInStock(finalInv);
          part.setMin(finalMin);
          part.setMax(finalMax);
          String finalCompanyName = modifyPartOutsourced.getText();
          part.setCompanyName(finalCompanyName);
          
          Inventory.updatePart(modifyPartIndex, part);
          }  
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Success");
            alert.setContentText("Psrt modified and saved successfully.");
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
     private void modifyPartCancelButtonHandler(ActionEvent event) throws Exception {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to cancel modifying this product?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
         dialogStage.close();
         } else {
            System.out.println("Action Cancelled.");
        }
     }
    @FXML
     public void setMainApp(DavidLopez DavidLopez) {
        this.DavidLopez = DavidLopez;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Part part = getCompletePartInventory().get(modifyPartIndex);
        modifyPartIDField.setText(Integer.toString(part.getPartID()));
        modifyPartNameField.setText(part.getName());
        modifyPartInvField.setText(Integer.toString(part.getInStock()));
        modifyPartPriceField.setText(Double.toString(part.getPrice()));
        modifyPartMinField.setText(Integer.toString(part.getMin()));
        modifyPartMaxField.setText(Integer.toString(part.getMax()));
        
         if (part instanceof Inhouse) {
            modifyPartInHouse.setText(Integer.toString(((Inhouse) getCompletePartInventory().get(modifyPartIndex)).getMachineID()));
            lbl.setText("Machine ID");
            modifyPartInHouseRadio.setSelected(true);
            modifyPartInHouse.setVisible(true);
            modifyPartOutsourced.setVisible(false);

        } else {
            modifyPartOutsourced.setText(((Outsourced) getCompletePartInventory().get(modifyPartIndex)).getCompanyName());
            lbl.setText("Company Name");
            modifyPartOutsourcedRadio.setSelected(true);
            modifyPartInHouse.setVisible(false);
            modifyPartOutsourced.setVisible(true);
        }
      
    }    
    
}

