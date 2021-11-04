/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to manage the embedded GUI program which can load in To-Do Lists from a file.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadFileController {

    @FXML
    private Button button;

    @FXML
    private TextField textPane;

    // call load method from entered file path
    @FXML
    void loadLists(ActionEvent event) {
        load(textPane.getText());
    }

    // attempt to load in saved lists to project
    // note: this class utilizes static methods because it does not require an object to be created to function.
    private static void load(String path) {
        // assign text from textPane to new String
        // try to create a Scanner at location
        // if not, open file not found GUI
        // if found:
        // delete all current items in inventory
        // use number printed on first line (numItems) to loop through each item
        // for numOfItems:
        // add a new item
        // set description to next line
        // set due date to next line
            // if due date is N/A, delete date
        // set completion status to next line
        // close Scanner

        // for testing the GUI
        failToLoad();
    }

    // prompt user that the file cannot be found
    private static void failToLoad() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader2 = new FXMLLoader(LoadFileController.class.getResource("fileNotFound.fxml"));
            Parent root2 = (Parent)fxmlLoader2.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading LoadFile GUI.");
        }
    }
}
