/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import davidlopez.view.MainController;
import davidlopez.view.AddPartController;
import davidlopez.view.AddProductController;
import davidlopez.view.ModifyPartController;
import davidlopez.view.ModifyProductController;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.stage.Modality;
/**
 *
 * @author David
 */
public class DavidLopez extends Application {

   //variable delarations
    
    private Stage mainStage;
    private BorderPane rootLayout;
    
    //default constructor
    public DavidLopez() {
    };
    
   //sets main stage (mainview of program
    @Override
      public void start(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.setTitle("Inventory");
        //sets default rootlayout and also loads the mainView over the rootlayout
        initRootLayout();
        showMain();
       
    }
      
      //
      public void initRootLayout() {
        try {
             //Loads rootlayout from fxml file RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DavidLopez.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Shows the scene that has the root layout.
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
            
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
      public Boolean addProductView() {
      try {
        // Load the fxml file for addProductView 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DavidLopez.class.getResource("view/AddProduct.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

// Give the controller access to the main app.
        AddProductController controller = loader.getController();
        controller.setMainApp(this);
        // Creates the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Product");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       AddProductController controller1= loader.getController();
        controller1.setDialogStage(dialogStage);
        controller1.setMainApp(this);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();;

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
      }
      
    public Boolean modifyProductView() {
      try {
        // Load the fxml file for addProductView 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DavidLopez.class.getResource("view/ModifyProduct.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

// Give the controller access to the main app.
        ModifyProductController controller = loader.getController();
        controller.setMainApp(this);
        // Creates the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modify Product");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       ModifyProductController controller1= loader.getController();
        controller1.setDialogStage(dialogStage);
        controller1.setMainApp(this);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();;

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
      }
    
    
    
    
    public Boolean addPartView() {
      try {
        // Load the fxml file for addProductView 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DavidLopez.class.getResource("view/AddPart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

// Give the controller access to the main app.
        AddPartController controller = loader.getController();
        controller.setMainApp(this);
        // Creates the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Part");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       AddPartController controller1= loader.getController();
        controller1.setDialogStage(dialogStage);
        controller1.setMainApp(this);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();;

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
      }

    
    public Boolean modifyPartView() {
      try {
        // Load the fxml file for addProductView 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DavidLopez.class.getResource("view/ModifyPart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

// Give the controller access to the main app.
        ModifyPartController controller = loader.getController();
        controller.setMainApp(this);
        // Creates the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modify Part");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(mainStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       ModifyPartController controller1= loader.getController();
        controller1.setDialogStage(dialogStage);
        controller1.setMainApp(this);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();;

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
      }
   public void showMain() {
       try {
         
            // Load Main.fxml
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(DavidLopez.class.getResource("view/Main.fxml"));
         AnchorPane mainOverview = (AnchorPane) loader.load();
         rootLayout.setCenter(mainOverview);
            
        MainController controller = loader.getController();
        controller.setMainApp(this);
       
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
 
     //* @param args the command line arguments
     
    public static void main(String[] args) {
        launch(args);
    }
    
}
