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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;

public class SaveFileController {

    // Stores the file name
    @FXML
    private TextField fileNamePane;

    // Triggers the File Chooser
    @FXML
    private Button findFileButton;

    // Displays the file path
    @FXML
    private Label pathLabel;

    // list to hold inventory items
    private static List<Item> savedList;

    // stage which can be accessed from anywhere in the method
    static Stage stage;

    // Saves the list
    @FXML
    private Button saveButton;

    @FXML
    void findFilePath(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if(file != null) {
            pathLabel.setText(file.getPath());
        }
    }

    // method called to open window and bring inventory
    public static void sendItems(List<Item> inventory) {
        // save inventory to list
        savedList = inventory;

        // show the GUI
        SaveFileController.loadWindow();
    }

    // show the save file window
    static void loadWindow() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(SaveFileController.class.getResource("saveFile.fxml"));
            Parent root2 = fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading saveList GUI.");
        }
    }

    // trigger save method via text field
    @FXML
    void saveLists(ActionEvent event) {
        // verify the file path is set
        if(pathLabel.getText().length() < 1) {
            // call invalid path
            invalidPath();
        }

        // verify the text is valid
        else if(fileNamePane.getText().length() < 1 || fileNamePane.getText().length() > 256) {
            // call invalid input
            invalidInput();
        }

        // otherwise, begin saving process
        else save(pathLabel.getText(),fileNamePane.getText());
    }

    // create file / stream
    private Formatter createOutputStream(String path, String name) {
        // catch exceptions
        try {
            // create new file
            // note: we can use hard-coded path-delimiters since we are locating a path on the local computer
            File savedFile = new File(path + "\\" + name + ".txt");
            if(!savedFile.createNewFile()) {
                // file could not be created, open fail GUI
                failToSave();
                return null;
            }

            // create / return output stream
            return new Formatter(new FileOutputStream(savedFile));
        }
        // exception is thrown, open fail GUI
        catch (IOException e) {
            failToSave();
            return null;
        }
    }

    // attempt to save the currently selected lists to a file
    private void save(String path, String name) {
        // create file and formatter
        Formatter stream = createOutputStream(path,name);

        // if stream is null, leave
        if(stream == null) return;

        // write size of inventory to file FIRST
        stream.format("%d%n",savedList.size());

        // write each item to a new line
        for(Item i : savedList) {
            stream.format("%s%n%s%n%s%n",i.getDescription(),i.getDueDate(),i.getStatus());
        }

        // close Formatter
        stream.close();

        // close window
        stage.close();
    }

    // prompt the user that the file path cannot be used
    private void failToSave() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("errorCreatingFile.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
        }
        catch(IOException e) {
            System.err.print("Error loading ErrorCreatingFile GUI.");
        }
    }

    // prompt the user that the file name is invalid
    private void invalidInput() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invalidInput.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
        }
        catch(IOException e) {
            System.err.print("Error loading invalidInput GUI.");
        }
    }

    // prompt the user that the file path is unspecified
    private void invalidPath() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noPath.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root));
            stage2.show();
        }
        catch(IOException e) {
            System.err.print("Error loading noPath GUI.");
        }
    }
}
