/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidlopez.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import davidlopez.model.Part;
import davidlopez.model.Product;

/**
 *
 * @author David
 */
public class Inventory {
  
   private static ObservableList<Product> products = FXCollections.observableArrayList(); 
   private static ObservableList<Part> allParts = FXCollections.observableArrayList(); 
    
    
   public static void addProduct(Product finalProduct) {
        products.add(finalProduct);
   }
    
   public static void removeProduct(Product finalProduct) {
       products.remove(finalProduct);
   }
    
   public static int searchProduct(String searchItem) {
       boolean searchItemFound = false;
       int index = 0;

        if (checkIfInteger(searchItem)) {
            for (int i = 0; i < products.size(); i++) {
                if (Integer.parseInt(searchItem) == products.get(i).getProductID()) {
                    index = i;
                    searchItemFound = true;
                }
            }
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (searchItem.equals(products.get(i).getProductName())) {
                    index = i;
                    searchItemFound = true;
                }
            }
        }
        if (searchItemFound = true) {
            return index;
        } else {
            System.out.println("Product could not be found.");
            return -1;
        }
   }
   
    public static void updateProduct(Integer index, Product finalProduct) {
       products.set(index, finalProduct);
   }
    
    public static void addPart(Part finalPart) {
       allParts.add(finalPart);    
   }
    
    public static void deletePart(Part finalPart) {
       allParts.remove(finalPart);
   }
    
    public static int searchPart(String searchItem) {
    boolean searchItemFound = false;
       int index = 0;

        if (checkIfInteger(searchItem)) {
            for (int i = 0; i < allParts.size(); i++) {
                if (Integer.parseInt(searchItem) == allParts.get(i).getPartID()) {
                    index = i;
                    searchItemFound = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                if (searchItem.equals(allParts.get(i).getName())) {
                    index = i;
                    searchItemFound = true;
                }
            }
        }
        if (searchItemFound = true) {
            return index;
        } else {
            System.out.println("Part could not be found.");
            return -1;
        }
   }
    
    public static void updatePart(Integer index, Part part) {
       allParts.set(index, part);
   } 
   
    public static boolean checkIfInteger(String searchItem) {
        try {
            Integer.parseInt(searchItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
   public static ObservableList<Part> getCompletePartInventory() {
        return allParts;
    }
   
   public static ObservableList<Product> getCompleteProductInventory() {
        return products;
    }

}
