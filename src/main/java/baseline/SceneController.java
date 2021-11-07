/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to manage the main GUI program.

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneController {

    // Custom image for displaying the title
    @FXML
    private ImageView titleImage;

    // Button which signifies an event is complete
    @FXML
    private RadioButton completeButton;

    // Button which signifies an event is incomplete
    @FXML
    private RadioButton incompleteButton;

    // Variable to represent "Complete"
    private static final String COMPLETE = "Complete";

    // Variable to represent "Incomplete"
    private static final String INCOMPLETE = "Incomplete";

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

    // int which manages the view status
    // note: 1 = all, 2 = complete, 3 = incomplete
    private int view = 1;

    // Button which adds a new item to the list
    @FXML
    private Button newItemButton;

    // Scroll pane which oversees the items
    @FXML
    private ScrollPane itemsScrollPane;

    // Table for storing items
    @FXML
    TableView<Item> tableView;

    // View list for description
    @FXML
    private TableColumn<Item, String> viewDescription;

    // View list for due date
    @FXML
    private TableColumn<Item, String> viewDueDate;

    // View list for status
    @FXML
    private TableColumn<Item, String> viewStatus;

    // List of items (to be viewed)
    private final ObservableList<Item> listOfItems = FXCollections.observableArrayList();

    // List of all items (database)
    private List<Item> inventory = new ArrayList<>();

    // Pane which holds the dimensions for the list of items
    @FXML
    private AnchorPane itemsPane;

    // Group which maintains the status buttons
    @FXML
    private ToggleGroup statusButton;

    // initialize the controller and values
    public void initialize() {

        // assign title image to png in resources
        titleImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("title.png"))));

        // clear default message in TableView
        tableView.setPlaceholder(new Label(""));

        // initialize the description column
        viewDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        viewDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        viewDescription.setOnEditCommit( t -> (
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(t.getNewValue()));

        // initialize the date column
        viewDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        viewDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
        viewDueDate.setOnEditCommit( t -> (
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setDueDate(t.getNewValue()));

        // initialize the status column
        viewStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        viewStatus.setCellFactory(TextFieldTableCell.forTableColumn());
        viewStatus.setOnEditCommit( t -> (
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue()));

        // get the observable list in the table
        setTableView();

        // setup listener for selecting a table item
        tableView.getSelectionModel().selectedItemProperty().addListener((click,oldValue,newValue) -> {
            // all items were deleted
            if(tableView.getSelectionModel().isEmpty()) {
                // set controllers to empty
                descriptionTextField.clear();
                dueDateDatePicker.setValue(null);
                completeButton.setSelected(false);
                incompleteButton.setSelected(false);

                // break out of lambda expression
                return;
            }

            // verify an item was clicked
            int selectedID;
            try {
                selectedID = tableView.getSelectionModel().getSelectedIndex();
            }
            // there's no selected item
            catch (Exception e) {
                return;
            }

            // set description field as selected item text
            descriptionTextField.setText(listOfItems.get(selectedID).getDescription());

            // if date is "N/A", empty date picker
            if(listOfItems.get(selectedID).getDueDate().equals("N/A")) {
                dueDateDatePicker.setValue(null);
            }
            // if date is valid, put date in date picker
            else {
                // set due date as selected item text
                String year = listOfItems.get(selectedID).getDueDate().substring(0,4);
                String month = listOfItems.get(selectedID).getDueDate().substring(5,7);
                String day = listOfItems.get(selectedID).getDueDate().substring(8,10);
                dueDateDatePicker.setValue(LocalDate.of(
                        Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)));
            }

            // set status to matching button as selected item
            if(listOfItems.get(selectedID).getStatus().equals(COMPLETE)) completeButton.setSelected(true);
            else incompleteButton.setSelected(true);
        });
    }

    // set the table view to the listOfItems
    void setTableView() {
        tableView.setItems(getListOfItems());
    }

    // refresh the table view to update changed values
    private void refreshTable() {
        viewDescription.setVisible(false);
        viewDescription.setVisible(true);
    }

    // return the current list of items
    // note: this is primarily used by the TableView
    ObservableList<Item> getListOfItems() {
        return listOfItems;
    }

    // set the observable list of items to the inventory
    void setListOfItemsToAll() {
        // change view status
        view = 1;

        // clear the list
        listOfItems.removeAll();

        // add all items in the database to the list
        listOfItems.addAll(inventory);
    }

    // set the observable list of items to the completed items from the inventory
    void setListOfItemsToComplete() {
        // change view status
        view = 2;

        // clear the list
        listOfItems.removeAll();

        // add complete items in the database to the list
        for(Item i : inventory) {
            if(i.getStatus().equals(COMPLETE)) listOfItems.add(i);
        }
    }

    // set the observable list of items to the incomplete items in the inventory
    void setListOfItemsToIncomplete() {
        // change view status
        view = 3;

        // clear the list
        listOfItems.removeAll();

        // add incomplete items in the database to the list
        for(Item i : inventory) {
            if(i.getStatus().equals(INCOMPLETE)) listOfItems.add(i);
        }
    }

    // create method to find the index of a value in the inventory given the ID
    private int findIndexOfID(int id) {
        // loop through inventory
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getId() == id) return i;
        }

        // no ID found
        return -1;
    }

    // takes an item and adds it to the listOfItems IF the status matches
    void addItemToListIfStatusMatches(Item item) {
        switch(view) {
            case 2: if(item.getStatus().equals(COMPLETE)) listOfItems.add(item);
                break;
            case 3: if(item.getStatus().equals(INCOMPLETE)) listOfItems.add(item);
                break;
            default: listOfItems.add(item);
                break;
        }
    }

    // Adds a new item to the list
    @FXML
    void addItemToList(ActionEvent event) {
        // verify that there is not 100 items in the list
        if(inventory.size() == 100) {
            // since this is the only case this GUI can be called, I did not move it to a separate method
            try{
                // load error GUI, and set a new stage
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreThan100Objects.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch(IOException e) {
                System.err.print("Error loading moreThan100Objects GUI.");
            }
        }
        else {
            // create a new item by calling the addItem GUI
            Item newItem = (new AddItemController()).display();

            // if the item is null, no changes
            if(newItem != null) {
                // add item to list
                inventory.add(newItem);

                // depending on view status, add to observable list
                addItemToListIfStatusMatches(newItem);

                // set the table view to (possibly) include new item
                setTableView();
            }
        }
    }

    // Sets the due date for a specific item (YYYY-MM-DD)
    @FXML
    void setDate(ActionEvent event) {
        // verify there is a selected value (and declare indexes)
        int inventoryIndex;
        int listOfItemsIndex;
        try {
            inventoryIndex = findIndexOfID(listOfItems.get(tableView.getSelectionModel().getSelectedIndex()).getId());
            listOfItemsIndex = tableView.getSelectionModel().getSelectedIndex();
        }
        // there's no selected item
        catch (Exception e) {
            return;
        }

        // get the user date
        String newDueDate;
        try {
            // get the due date
            newDueDate = dueDateDatePicker.getValue().toString();
            if(newDueDate.isEmpty()) newDueDate = "N/A";
        }
        // catch null pointer
        catch (NullPointerException e) {
            newDueDate = "N/A";
        }

        // change date in inventory
        if(inventoryIndex >= 0) inventory.get(inventoryIndex).setDueDate(newDueDate);

        // modify the item in viewable list
        listOfItems.get(listOfItemsIndex).setDueDate(newDueDate);

        // refresh the table
        refreshTable();
    }

    // Changes the description on the item in the list view
    @FXML
    void setDescription(ActionEvent event) {
        // verify there is a selected value (and declare indexes)
        int inventoryIndex;
        int listOfItemsIndex;
        try {
            inventoryIndex = findIndexOfID(listOfItems.get(tableView.getSelectionModel().getSelectedIndex()).getId());
            listOfItemsIndex = tableView.getSelectionModel().getSelectedIndex();
        }
        // there's no selected item
        catch (Exception e) {
            return;
        }

        // verify the length of the string in the text pane is between 1 and 256 characters
        String newDescription = descriptionTextField.getText();
        if(newDescription.length() < 1 || newDescription.length() > 256) {
            promptInvalidInput();
            return;
        }

        // modify the item in inventory
        if(inventoryIndex >= 0) inventory.get(inventoryIndex).setDescription(newDescription);

        // modify the item in viewable list
        listOfItems.get(listOfItemsIndex).setDescription(newDescription);

        // refresh the table
        refreshTable();
    }

    // Sets the status of an item to "Complete"
    @FXML
    void clickCompleteButton(ActionEvent event) {
        // verify there is a selected value (and declare indexes)
        int inventoryIndex;
        int listOfItemsIndex;
        try {
            inventoryIndex = findIndexOfID(listOfItems.get(tableView.getSelectionModel().getSelectedIndex()).getId());
            listOfItemsIndex = tableView.getSelectionModel().getSelectedIndex();
        }
        // there's no selected item
        catch (Exception e) {
            return;
        }

        // modify the item in inventory
        if(inventoryIndex >= 0) inventory.get(inventoryIndex).setStatus(COMPLETE);

        // modify the item in viewable list
        listOfItems.get(listOfItemsIndex).setStatus(COMPLETE);

        // refresh the table
        refreshTable();
    }

    // Sets the status of an item to "Incomplete"
    @FXML
    void clickIncompleteButton(ActionEvent event) {
        // verify there is a selected value (and declare indexes)
        int inventoryIndex;
        int listOfItemsIndex;
        try {
            inventoryIndex = findIndexOfID(listOfItems.get(tableView.getSelectionModel().getSelectedIndex()).getId());
            listOfItemsIndex = tableView.getSelectionModel().getSelectedIndex();
        }
        // there's no selected item
        catch (Exception e) {
            return;
        }

        // modify the item in inventory
        if(inventoryIndex >= 0) inventory.get(inventoryIndex).setStatus(INCOMPLETE);

        // modify the item in viewable list
        listOfItems.get(listOfItemsIndex).setStatus(INCOMPLETE);

        // refresh the table
        refreshTable();
    }

    // Call the method that deletes all items from a list
    @FXML
    void deleteAllItems(ActionEvent event) {
        clearItems();
    }

    // Delete all items from inventory and current view
    void clearItems() {
        // remove all items from inventory
        inventory = new ArrayList<>();

        // empty list of items
        listOfItems.removeAll();

        // set table view to list view
        tableView.getItems().clear();

        // reset the item index counter
        Item.resetIndex();
    }

    // Deletes a specific item from the list
    @FXML
    void deleteItem(ActionEvent event) {
        // verify there is a selected value (and declare indexes)
        int inventoryIndex;
        int listOfItemsIndex;
        try {
            inventoryIndex = findIndexOfID(listOfItems.get(tableView.getSelectionModel().getSelectedIndex()).getId());
            listOfItemsIndex = tableView.getSelectionModel().getSelectedIndex();
        }
        // there's no selected item
        catch (Exception e) {
            return;
        }

        // delete the item in the inventory
        inventory.remove(inventoryIndex);

        // delete item in observable list
        listOfItems.remove(listOfItemsIndex);

        // refresh the table
        refreshTable();
    }

    // Loads a saved list from a specified directory
    @FXML
    void loadList(ActionEvent event) {
        List<Item> loadedItems = (new LoadFileController()).loadItems();

        // verify items were present on file
        if(loadedItems == null || loadedItems.isEmpty()) return;

        // reset list
        clearItems();

        // save new items to inventory
        inventory = loadedItems;

        // set current view to ALL
        setListOfItemsToAll();
    }

    // Saves a list to a specified directory in the local drive
    @FXML
    void saveList(ActionEvent event) {
        SaveFileController.sendItems(inventory);
    }

    // Changes the ListView to show only the complete values
    @FXML
    void viewAllItems(ActionEvent event) {
        // reset table view
        tableView.getItems().clear();

        // set the list of items to view all
        setListOfItemsToAll();

        // set table view
        setTableView();
    }

    // Changes the ListView to show all items
    @FXML
    void viewCompleteItems(ActionEvent event) {
        // reset table view
        tableView.getItems().clear();

        // set the list of items to view all
        setListOfItemsToComplete();

        // set table view
        setTableView();
    }

    // Changes the ListView to show only the incomplete items
    @FXML
    void viewIncompleteItems(ActionEvent event) {
        // reset table view
        tableView.getItems().clear();

        // set the list of items to view all
        setListOfItemsToIncomplete();

        // set table view
        setTableView();
    }

    // the user entered invalid input
    void promptInvalidInput() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invalidInput.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading invalidInput GUI.");
        }
    }
}
