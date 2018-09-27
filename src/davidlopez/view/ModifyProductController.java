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
import davidlopez.model.Inventory;
import static davidlopez.view.MainController.productToModifyIndex;
import static davidlopez.model.Inventory.getCompleteProductInventory;
import static davidlopez.model.Inventory.getCompletePartInventory;
import static davidlopez.model.Product.getassociatedParts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import davidlopez.model.Product;
import davidlopez.model.Part;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author David
 */
public class ModifyProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private DavidLopez DavidLopez;
    private Stage dialogStage;
    private boolean okClicked = false;
    int modifyProductIndex = productToModifyIndex();
    private ObservableList<Part> currentProductParts = FXCollections.observableArrayList();
    private String productError = new String();
     
     //modifyProduct fields
    @FXML
    private TextField modifyProductIDField;
    @FXML 
    private TextField modifyProductName;
    @FXML 
    private TextField modifyProductInvField;
    @FXML
    private TextField modifyProductPriceField;
    @FXML
    private TextField modifyProductMaxField;
    @FXML
    private TextField modifyProductMinField;
    @FXML
    private TextField modifyProductSearchField; 
    @FXML
    private TextField modifyProductAssociatedSearchField;
    
    //tables
    @FXML
    private TableView<Part> modifyProductsAddTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductIDColumn;
    @FXML
    private TableColumn<Part, String> modifyProductNameColumn;
    @FXML
    private TableColumn<Part, Integer> modifyProductInvLevelColumn;
    @FXML
    private TableColumn<Part, Double> modifyProductPriceColumn;
    @FXML
    private TableView<Part> modifyProductsDeleteTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductCurrentIDColumn;
    @FXML
    private TableColumn<Part, String> modifyProductCurrentNameColumn;
    @FXML
    private TableColumn<Part, Integer> modifyProductCurrentInvColumn;
    @FXML
    private TableColumn<Part, Double> modifyProductCurrentPriceColumn;
     
     
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
     
     public boolean isOkClicked() {

        return okClicked;
    }
     
     
    @FXML
     public void setMainApp(DavidLopez DavidLopez) {
        this.DavidLopez = DavidLopez;

    }
     
    @FXML
    private void modifyProductSearchButtonHandler(ActionEvent event) throws Exception {
    String findPart = modifyProductSearchField.getText();
         int partIndex = -1;
        if (Inventory.searchPart(findPart) == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("The part entered cannot be found.");
            alert.showAndWait();
        } else {
            partIndex = Inventory.searchPart(findPart);
            Part tempPart = Inventory.getCompletePartInventory().get(partIndex);
            ObservableList<Part> tempProdList = FXCollections.observableArrayList();
            tempProdList.add(tempPart);
            modifyProductsAddTable.setItems(tempProdList);
        }
    }
    
     @FXML
    private void modifyProductAssociatedSearch(ActionEvent event) throws Exception {
    String findPart = modifyProductAssociatedSearchField.getText();
         int partIndex = -1;
        if (Inventory.searchPart(findPart) == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("The part entered cannot be found.");
            alert.showAndWait();
        } else {
            partIndex = Inventory.searchPart(findPart);
            Part tempPart =Inventory.getCompletePartInventory().get(partIndex);
            ObservableList<Part> tempProdList = FXCollections.observableArrayList();
            tempProdList.add(tempPart);
            modifyProductsDeleteTable.setItems(tempProdList);
        }
    }
    
    @FXML
    private void modifyProductClearSearchButtonHandler(ActionEvent event) throws Exception {
        modifyProductsAddTable.setItems(getCompletePartInventory());
        modifyProductSearchField.setText("");
        
    }
    
     @FXML
    private void modifyProductAssociatedClear(ActionEvent event) throws Exception {
        modifyProductsDeleteTable.setItems(getCompletePartInventory());
        modifyProductAssociatedSearchField.setText("");
        
    }
    @FXML
    private void modifyProductAddButtonHandler(ActionEvent event) throws Exception {
       Part part = modifyProductsAddTable.getSelectionModel().getSelectedItem();
        currentProductParts.add(part);
        modifyProductsAddTable.setItems(getCompletePartInventory());
        
   }
    @FXML
    private void modifyProductDeleteButtonHandler(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to delete this associated Part?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
            Part part = modifyProductsDeleteTable.getSelectionModel().getSelectedItem();
       currentProductParts.remove(part);

        } else {
            System.out.println("Part deletion cancelled.");
        }
        
   }
    @FXML
    private void modifyProductSaveButtonHandler(ActionEvent event) throws Exception {
       
             int finalProductID = Integer.parseInt(modifyProductIDField.getText());
             String finalProductName = modifyProductName.getText();
             int finalProductInv = Integer.parseInt(modifyProductInvField.getText());
             Double finalProductPrice = Double.parseDouble(modifyProductPriceField.getText());
             int finalProductMax = Integer.parseInt(modifyProductMaxField.getText());
             int finalProductMin = Integer.parseInt(modifyProductMinField.getText());
             
             try {
            productError = Product.productValidation(finalProductName, finalProductMin, finalProductMax, finalProductInv, finalProductPrice, currentProductParts, productError);
            if (productError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Modifying Part");
                alert.setHeaderText("Error");
                alert.setContentText(productError);
                alert.showAndWait();
                productError = "";
            } else {
             Product finalProduct = new Product();

             finalProduct.setProductID(finalProductID);
             finalProduct.setProductName(finalProductName);
             finalProduct.setProductPrice(finalProductPrice);
             finalProduct.setProductInStock(finalProductInv);
             finalProduct.setProductMin(finalProductMin);
             finalProduct.setProductMax(finalProductMax);
             finalProduct.addAssociatedPart(currentProductParts);
            
            Inventory.updateProduct(modifyProductIndex, finalProduct);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Success");
            alert.setContentText("Product modified and saved successfully.");
            alert.showAndWait(); 
            dialogStage.close();
            }      
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error modifying Product");
            alert.setHeaderText("Error");
            alert.setContentText("Form contains blank fields.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void modifyProductCancelButtonHandler(ActionEvent event) throws Exception {
       
        dialogStage.close();
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product product = getCompleteProductInventory().get(modifyProductIndex);
        modifyProductIDField.setText(Integer.toString(product.getProductID()));
        modifyProductName.setText(product.getProductName());
        modifyProductInvField.setText(Integer.toString(product.getProductInStock()));
        modifyProductPriceField.setText(Double.toString(product.getProductPrice()));
        modifyProductMaxField.setText(Integer.toString(product.getProductMax()));
        modifyProductMinField.setText(Integer.toString(product.getProductMin()));
        currentProductParts = product.getProductParts();
        modifyProductIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        modifyProductNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        modifyProductInvLevelColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        modifyProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        modifyProductCurrentIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        modifyProductCurrentNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        modifyProductCurrentInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        modifyProductCurrentPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
                
       modifyProductsAddTable.setItems(getCompletePartInventory());
       modifyProductsDeleteTable.setItems(currentProductParts);
      
    }   
    
}
