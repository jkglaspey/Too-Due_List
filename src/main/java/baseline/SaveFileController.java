/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to manage the embedded GUI program which can save To-Do Lists to a file.

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

public class SaveFileController {

    @FXML
    private Button button;

    @FXML
    private TextField textPane;

    // trigger save method via text field
    @FXML
    void saveLists(ActionEvent event) {
        save(textPane.getText());
    }

    // attempt to save the currently selected lists to a file
    private void save(String path) {
        // try:
        // create new File using text from textPane
        // create .txt file at File object
        // create new Formatter to this File
        // catch exceptions with user permissions and pre-existing file
        // if exception is thrown, open error creating file GUI
        // write size of inventory to file FIRST
        // for each item
        // write description to new line
        // write due date to new line
            // if there is no due date, write N/A
        // write completion status to new line
        // close Formatter

        // for testing GUI
        failToSave();
    }

    // prompt the user that the file path cannot be used
    private void failToSave() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("errorCreatingFile.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading ErrorCreatingFile GUI.");
        }
    }
}
