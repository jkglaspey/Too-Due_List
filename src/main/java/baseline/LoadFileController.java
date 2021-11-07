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
    private static Stage stage;

    // list of items loaded from a saved file
    private static List<Item> loadedItems = null;

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
    public static List<Item> loadItems() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(LoadFileController.class.getResource("loadFile.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }
        // could not load GUI
        catch(IOException e) {
            System.err.print("Error loading loadList GUI.");
            return null;
        }
        // return items from file
        return loadedItems;
    }

    // call load method from entered file path
    @FXML
    void loadFile(ActionEvent event) {
        load(filePathPane.getText());
    }

    // replace invalid characters in file path
    private static String fixPath(String path) {
        // replace "\" with "\\"
        System.out.print(path);
        return path.replace("\\","\\\\");
    }

    // attempt to load in saved lists to project
    // note: this class utilizes static methods because it does not require an object to be created to function.
    private static void load(String path) {
        // try to create a Scanner at location
        Scanner stream;
        try {
            // use fixPath method to make path a valid name
            String pathName = fixPath(path);
            stream = new Scanner(new File(pathName));
        }
        // file was not found
        catch (FileNotFoundException e) {
            // open file not found GUI
            failToLoad();
            return;
        }

        // if stream is still null, terminate
        if(stream == null) return;

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

        // close the window
        stage.close();
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
            System.err.print("Error loading file.");
        }
    }
}
