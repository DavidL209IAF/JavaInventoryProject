/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.view;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.lang.*;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import davidlopez.DavidLopez;
import davidlopez.view.AddPartController;
import davidlopez.view.*;
import java.io.IOException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import davidlopez.model.Part;
import davidlopez.model.Product;
import davidlopez.model.Inventory;
import davidlopez.model.Inhouse;
import davidlopez.model.Outsourced;
import static davidlopez.model.Inventory.getCompletePartInventory;
import static davidlopez.model.Inventory.getCompleteProductInventory;
import static davidlopez.model.Product.productParts;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author David
 */
public class MainController implements Initializable {
    
    //variables 
    @FXML
    private DavidLopez DavidLopez;
    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;
    
    //Table and columns rename them and match on fxml file
    
    @FXML
    private TableView<Part> mainPartsTable;
    @FXML
    private TableColumn<Part, Integer> mainPartIDColumn;
    @FXML
    private TableColumn<Part, String> mainPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> mainPartInvColumn;
    @FXML
    private TableColumn<Part, Double> mainPartPriceColumn;
    
    
    @FXML
    private TableView<Product> mainProductsTable;
    @FXML
    private TableColumn<Product, Integer>mainProductIDColumn;
    @FXML
    private TableColumn<Product, String>mainProductNameColumn;
    @FXML
    private TableColumn<Product, Integer>mainProductInvColumn;
    @FXML
    private TableColumn<Product, Double> mainProductPriceColumn;
    @FXML
    private TextField mainPartsSearchField;
    @FXML
    private TextField mainProductsSearchField;
 
    //default constructor
     public MainController() {
       
         //Sample data part 1
          Inhouse testPart1 = new Inhouse();
          testPart1.setPartID(1);
          testPart1.setName("Test");
          testPart1.setPrice(2.00);
          testPart1.setInStock(5);
          testPart1.setMin(1);
          testPart1.setMax(2);
          testPart1.setMachineID(123);
          Inventory.addPart(testPart1);
          
          
           //Sample data part 2
          Inhouse testPart2 = new Inhouse();
          testPart2.setPartID(2);
          testPart2.setName("Test1");
          testPart2.setPrice(1.00);
          testPart2.setInStock(7);
          testPart2.setMin(1);
          testPart2.setMax(2);
          testPart2.setMachineID(124);
          Inventory.addPart(testPart2);
          
          //sample data part 3
          Outsourced testPart3 = new Outsourced();
          testPart3.setPartID(3);
          testPart3.setName("Test2");
          testPart3.setPrice(5.00);
          testPart3.setInStock(3);
          testPart3.setMin(1);
          testPart3.setMax(2);
          testPart3.setCompanyName("Test");
          Inventory.addPart(testPart3);
          }
         
         
    

    //handles the addproduct click, by calling the add productview, which swutches the controlelr and creates pop
     //up window for the AddPart.fxml
    @FXML
     private void AddProductHandler() {
    
   
     boolean okClicked = DavidLopez.addProductView();
    }

    @FXML
    private void ModifyProductHandler() {
     
     modifyProduct = mainProductsTable.getSelectionModel().getSelectedItem();
     modifyProductIndex = getCompleteProductInventory().indexOf(modifyProduct);
     boolean okClicked = DavidLopez.modifyProductView();
     
    }
     
     @FXML
    private void AddPartHandler() {
     
     boolean okClicked = DavidLopez.addPartView();
     
    } 
     
     @FXML
    private void ModifyPartHandler() {
     
    modifyPart = mainPartsTable.getSelectionModel().getSelectedItem();
    modifyPartIndex = getCompletePartInventory().indexOf(modifyPart);
     boolean okClicked = DavidLopez.modifyPartView();
    } 
     
     //handles the exit button by closing the program
    @FXML
    private void exitButtonHandler(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to quit?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            System.out.println("Exit cancelled.");
        }
    }
    
   @FXML
    private void ProductClearSearchButtonHandler(ActionEvent event) throws Exception {
        mainProductsTable.setItems(getCompleteProductInventory());
        mainProductsSearchField.setText("");
        
    }
    
    @FXML
    private void PartClearSearchButtonHandler(ActionEvent event) throws Exception {
        mainPartsTable.setItems(getCompletePartInventory());
        mainPartsSearchField.setText("");
        
    }
   
      @FXML
    private void deletePartHandler(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to delete this Part?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
            Part part = mainPartsTable.getSelectionModel().getSelectedItem();
       Inventory.deletePart(part);

        } else {
            System.out.println("Part deletion cancelled.");
        }
        
   }
   
    
       @FXML
    private void partsSearchButtonHandler(ActionEvent event) throws Exception {
       
      
        String findPart = mainPartsSearchField.getText();
        int partIndex = -1;
        if (Inventory.searchPart(findPart) == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("The part searched for cannot be found.");
            alert.showAndWait();
        } else {
            partIndex = Inventory.searchPart(findPart);
            Part tempPart = Inventory.getCompletePartInventory().get(partIndex);
            ObservableList<Part> tempPartList = FXCollections.observableArrayList();
            tempPartList.add(tempPart);
            mainPartsTable.setItems(tempPartList);
        }
        
        
   }
    
    
     @FXML
    private void deleteProductHandler(ActionEvent event) throws Exception {
        Product product = mainProductsTable.getSelectionModel().getSelectedItem();
       
        if (productParts(product) == 1) {
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Error");
            alert.setContentText("Product contains parts and cannot be deleted.");
            alert.showAndWait(); 
        } else if(productParts(product) == -1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to delete this Product?");
        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.get() == ButtonType.OK) {
         
         Inventory.removeProduct(product);
         } else {
            System.out.println("Product deletion cancelled.");
        }
        }
   }
    
 
     @FXML
    private void productsSearchButtonHandler(ActionEvent event) throws Exception {
       
        String findProduct = mainProductsSearchField.getText();
        int productIndex = -1;
        if (Inventory.searchProduct(findProduct) == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("The product searched for cannot be found.");
            alert.showAndWait();
        } else {
            productIndex = Inventory.searchProduct(findProduct);
            Product tempProd = Inventory.getCompleteProductInventory().get(productIndex);
            ObservableList<Product> tempProdList = FXCollections.observableArrayList();
            tempProdList.add(tempProd);
            mainProductsTable.setItems(tempProdList);
        }
          
   }
    
    public static int partToModifyIndex() {
        return modifyPartIndex;
    }

    public static int productToModifyIndex() {
        return modifyProductIndex;
    }
    
    public static boolean isValueInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mainPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        mainPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        mainPartInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        mainPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        mainProductIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        mainProductNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        mainProductInvColumn.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        mainProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        
       mainPartsTable.setItems(getCompletePartInventory());
       mainProductsTable.setItems(getCompleteProductInventory());
        
        
    }    
    @FXML
     public void setMainApp(DavidLopez DavidLopez) {
        this.DavidLopez = DavidLopez;

    }
}
