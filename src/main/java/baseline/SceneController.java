/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to manage the main GUI program.

package baseline;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class SceneController {

    // Custom image for displaying the title
    @FXML
    private ImageView titleImage;

    // Button which signifies an event is complete
    @FXML
    private RadioButton completeButton;

    // Button which deletes all items from the list
    @FXML
    private MenuItem deleteAllItems;

    // Button which deletes a single item
    @FXML
    private MenuItem deleteItem;

    // Text field which modifies the description of an item
    @FXML
    private TextField descriptionTextField;

    // Calendar tool which selects a due date
    // Note: It is optional in the item, and can be removed by pushing enter on the text field.
    @FXML
    private DatePicker dueDateDatePicker;

    // Button which signifies an event is incomplete
    @FXML
    private RadioButton incompleteButton;

    // Button which loads a list from a specific location
    @FXML
    private MenuItem menuFileLoadList;

    // Button which saves a list to a specific location
    @FXML
    private MenuItem menuFileSaveList;

    // Button which changes the view of the list to all items
    @FXML
    private MenuItem menuViewAll;

    // Button which changes the view of the list to only completed items
    @FXML
    private MenuItem menuViewComplete;

    // Button which changes the view of the list to only incomplete items
    @FXML
    private MenuItem menuViewIncomplete;

    // Button which adds a new item to the list
    @FXML
    private Button newItemButton;

    // Scroll pane which oversees the items
    @FXML
    private ScrollPane itemsScrollPane;

    // Table for storing items
    @FXML
    TableView tableView;

    // View list for description
    @FXML
    private TableColumn<?, ?> viewDescription;

    // View list for due date
    @FXML
    private TableColumn<?, ?> viewDueDate;

    // View list for status
    @FXML
    private TableColumn<?, ?> viewStatus;

    // List of items
    private ObservableList<ItemCell> listOfItems;

    // List of all items (database)
    private List<Item> inventory;

    // Pane which holds the dimensions for the list of items
    @FXML
    private AnchorPane itemsPane;

    // Group which maintains the status buttons
    @FXML
    private ToggleGroup statusButton;

    // Boolean which manages the statusButton group
    private Boolean status;

    // initialize the controller and values
    public void initialize() {

        // assign title image to png in resources
        titleImage.setImage(new Image(getClass().getResourceAsStream("title.png")));

        // clear default message in TableView
        tableView.setPlaceholder(new Label(""));

        // set each group in the table to be visible:
        // viewDescription
        // viewDueDate
        // viewStatus
    }

    // Adds a new item to the list
    @FXML
    void addItemToList(ActionEvent event) {
        // verify that there is not 100 items in the list
        // if there is, open GUI for too many items
        // otherwise
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading addItem GUI.");
        }
    }

    // Sets the status of an item to "Complete"
    @FXML
    void clickCompleteButton(ActionEvent event) {
        // update the status off the item cell to complete
        // change the color to green
    }

    // Sets the status of an item to "Incomplete"
    @FXML
    void clickIncompleteButton(ActionEvent event) {
        // update the status off the item cell to incomplete
        // change the color to red
    }

    // Deletes all the items from the list
    @FXML
    void deleteAllItems(ActionEvent event) {
        // remove all items from inventory
        // set listOfItems to inventory
    }

    // Deletes a specific item from the list
    @FXML
    void deleteItem(ActionEvent event) {
        // get the selected item's index
        // delete the item in the inventory
        // set listOfItems to inventory
    }

    // Loads a saved list from a specific directory
    @FXML
    void loadList(ActionEvent event) {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadFile.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading loadList GUI.");
        }
    }

    // Removes the due date from an item
    @FXML
    void removeDate(KeyEvent event) {
        // remove the date value from item
        // remove date from item cell
    }

    // Saves a list to a specified directory in the local drive
    @FXML
    void saveList(ActionEvent event) {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("saveFile.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading saveList GUI.");
        }
    }

    // Sets the due date for a specific item (YYYY-MM-DD)
    @FXML
    void setDate(ActionEvent event) {
        // change the date of the item
    }

    // Changes the description on the item in the list view
    @FXML
    void setDescription(ActionEvent event) {
        // verify the length of the string in the text pane is between 1 and 256 characters
        // if the string is not valid, open GUI popup for invalid input
        // otherwise, change the description of the item
    }

    // Changes the ListView to show only the complete values
    @FXML
    void viewAllItems(ActionEvent event) {
        // reset listOfItems
        // loop through the inventory
        // if an item has a complete status, add to listOfItems
    }

    // Changes the ListView to show all items
    @FXML
    void viewCompleteItems(ActionEvent event) {
        // set listOfItems to inventory
    }

    // Changes the ListView to show only the incomplete items
    @FXML
    void viewIncompleteItems(ActionEvent event) {
        // reset listOfItems
        // loop through the inventory
        // if an item has an incomplete status, add to listOfItems
    }
}
