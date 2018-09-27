/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.view;

import davidlopez.DavidLopez;
import davidlopez.model.Inhouse;
import davidlopez.model.Part;
import davidlopez.model.Product;
import davidlopez.model.Inventory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static davidlopez.model.Inventory.getCompletePartInventory;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author David
 */
public class AddProductController implements Initializable {
    private DavidLopez DavidLopez;
    private ObservableList<Part> currentProductParts = FXCollections.observableArrayList();
    private Stage dialogStage;
    private boolean okClicked = false;
    private String productError = new String();

    //addProduct fields
    @FXML
    private TextField addProductIDField;
    @FXML 
    private TextField addProductProductName;
    @FXML 
    private TextField addProductInvField;
    @FXML
    private TextField addProductPriceField;
    @FXML
    private TextField addProductMaxField;
    @FXML
    private TextField addProductMinField;
    @FXML
    private TextField addProductSearchField;
    @FXML 
    private TextField addProductAssociatedPartField;
    
    //addProduct table views
    @FXML
    private TableView<Part> addProductsAddTable;
    @FXML
    private TableColumn<Part, Integer> addProductPartIDColumn;
    @FXML
    private TableColumn<Part, String> addProductPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> addProductInvLevelColumn;
    @FXML
    private TableColumn<Part, Double> addProductPriceColumn;
    @FXML
    private TableView<Part> addProductsDeleteTable;
    @FXML
    private TableColumn<Part, Integer> addProductCurrentPartIDColumn;
    @FXML
    private TableColumn<Part, String>addProductCurrentPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> addProductCurrentInvColumn;
    @FXML
    private TableColumn<Part, Double> addProductCurrentPriceColumn;

    
    
     
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
     public boolean isOkClicked() {

        return okClicked;
    }
      @FXML
    private void addProductSearchButtonHandler(ActionEvent event) throws Exception {
        String findPart = addProductSearchField.getText();
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
            addProductsAddTable.setItems(tempProdList);
        }
       
   }
    @FXML
     private void addProductAssociatedPartSearchButtonHandler(ActionEvent event) throws Exception {
        String findPart = addProductAssociatedPartField.getText();
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
            addProductsDeleteTable.setItems(tempProdList);
        }
       
   }
    @FXML
    private void addProductClearSearchButtonHandler(ActionEvent event) throws Exception {
        addProductsAddTable.setItems(getCompletePartInventory());
        addProductSearchField.setText("");
        
    }
    
    @FXML
    private void addProductAssociatedPartClear(ActionEvent event) throws Exception {
        addProductsDeleteTable.setItems(getCompletePartInventory());
        addProductAssociatedPartField.setText("");
    }
    
     @FXML
    private void addProductAddButtonHandler(ActionEvent event) throws Exception {
       
        Part part = addProductsAddTable.getSelectionModel().getSelectedItem();
        currentProductParts.add(part);
        addProductsAddTable.setItems(getCompletePartInventory());
                }
     @FXML
    private void addProductDeleteButtonHandler(ActionEvent event) throws Exception {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to delete this associated Part?");
        Optional<ButtonType> choice = alert.showAndWait();
        
        

        if (choice.get() == ButtonType.OK) {
            Part part = addProductsDeleteTable.getSelectionModel().getSelectedItem();
       currentProductParts.remove(part);

        } else {
            System.out.println("Part deletion cancelled.");
        }
       

   }
     @FXML
    private void addProductSaveButtonHandler(ActionEvent event) throws Exception {
       
             int finalProductID = Integer.parseInt(addProductIDField.getText());
             String finalProductName = addProductProductName.getText();
             int finalProductInv = Integer.parseInt(addProductInvField.getText());
             Double finalProductPrice = Double.parseDouble(addProductPriceField.getText());
             int finalProductMax = Integer.parseInt(addProductMaxField.getText());
             int finalProductMin = Integer.parseInt(addProductMinField.getText());
             
             try {
            productError = Product.productValidation(finalProductName, finalProductMin, finalProductMax, finalProductInv, finalProductPrice, currentProductParts, productError);
            if (productError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part");
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
             Inventory.addProduct(finalProduct);
        
            addProductsAddTable.setItems(getCompletePartInventory()); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Success");
            alert.setContentText("Product added successfully.");
            alert.showAndWait();
            dialogStage.close();
            }
            }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error adding Product");
            alert.setHeaderText("Error");
            alert.setContentText("Form contains blank fields.");
            alert.showAndWait();
        
            }
             }
    
            

     @FXML
    private void addProductCancelButtonHandler(){
       
        dialogStage.close();
   }
     @FXML
     public void setMainApp(DavidLopez DavidLopez) {
        this.DavidLopez = DavidLopez;

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addProductPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addProductInvLevelColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        addProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());

        addProductCurrentPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductCurrentPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addProductCurrentInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        addProductCurrentPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        
       addProductsAddTable.setItems(getCompletePartInventory());
       addProductsDeleteTable.setItems(currentProductParts);
       
          
    }    
    
}
