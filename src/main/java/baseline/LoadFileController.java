/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to manage the embedded GUI program which can import a to-do list from a file.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LoadFileController {

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label filePathPane;

    @FXML
    private Button importButton;

    // stage that can be closed from within class
    private final Stage stage;

    // list of items loaded from a saved file
    private List<Item> loadedItems;

    // initialize the stage
    public LoadFileController() {
        stage = new Stage();
    }

    // used for testing (no stage declaration)
    // note: String acts as signifier that this is a test constructor
    public LoadFileController(String s) { stage = null; }

    // call method to open a file chooser
    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            filePathPane.setText(file.getPath());
        }
    }

    // call method that creates GUI window
    public List<Item> loadItems() {
        try{
            // load new fxml loader, but keep stage as one thread
            FXMLLoader fxmlLoader = new FXMLLoader(LoadFileController.class.getResource("loadFile.fxml"));
            fxmlLoader.setController(this);

            // load stage
            Parent root1 = fxmlLoader.load();
            assert stage != null;
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }
        // could not load GUI
        catch(IOException e) {
            System.err.print("Error loading loadList GUI.");
            return Collections.emptyList();
        }
        // return items from file
        return loadedItems;
    }

    // call load method from entered file path
    @FXML
    void loadFile(ActionEvent event) {
        // get values into list from specified file
        load(filePathPane.getText());

        // close the window
        assert stage != null;
        stage.close();
    }

    // attempt to load in saved lists to project
    private void load(String path) {
        // try to create a Scanner at location
        Scanner stream;
        try {
            stream = new Scanner(new File(path));
        }
        // file was not found
        catch (FileNotFoundException e) {
            // open file not found GUI
            failToLoad();
            return;
        }

        // use number printed on first line to loop through each item
        int count = Integer.parseInt(stream.nextLine());

        // initialize the list of items (we passed the null check)
        loadedItems = new ArrayList<>();

        // populate the item list
        for(int i = 0; i < count; i++) {
            loadedItems.add(new Item(stream.nextLine(),stream.nextLine(),stream.nextLine()));
        }

        // close Scanner
        stream.close();
    }

    // call the load method with a preset String (for testing purposes)
    void forceLoad(String s) {
        load(s);
    }

    // get the current list of items
    List<Item> getLoadedItems() {
        return loadedItems;
    }

    // prompt user that the file cannot be found
    private void failToLoad() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader2 = new FXMLLoader(LoadFileController.class.getResource("fileNotFound.fxml"));
            Parent root2 = fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.show();
        }
        catch(IOException e) {
            System.err.print("Error loading file.");
        }
    }
}
