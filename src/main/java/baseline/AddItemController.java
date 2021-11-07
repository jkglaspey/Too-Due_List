package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemController {

    // button which triggers the new item to be added to the table
    @FXML
    private Button button;

    // button for marking item as complete
    @FXML
    private RadioButton completeButton;

    // calendar item that lets user select a due date
    @FXML
    private DatePicker datePicker;

    // button for marking item as incomplete
    @FXML
    private RadioButton incompleteButton;

    // group for the completion status radio buttons
    @FXML
    private ToggleGroup statusButton;

    // text pane to enter a description
    @FXML
    private TextField textPane;

    // string which holds the due date
    private String dueDate = "N/A";

    // item which will be returned
    static Item newItem;

    // stage object
    static Stage stage = new Stage();

    // method that opens the default window and returns an item after the process is completed
    public static Item display() {
        try{
            // reset item
            newItem = null;

            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(AddItemController.class.getResource("addItem.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            stage.setScene(new Scene(root1));

            // show window, but wait for add button to be clicked
            stage.showAndWait();
            return newItem;
        }
        catch(IOException e) {
            System.err.print("Error loading addItem GUI.");
        }
        // GUI fails to appear
        return null;
    }

    // change the due date to last selected OR null
    @FXML
    void setDueDate(ActionEvent event) {
        try {
            // get the due date
            dueDate = datePicker.getValue().toString();
            if(dueDate.isEmpty()) dueDate = "N/A";
        }
        // catch null pointer
        catch (NullPointerException e) {
            dueDate = "N/A";
        }
    }

    // the user clicks the add item button
    // note: this method ensures there's no errors with the input
    @FXML
    void saveItem(ActionEvent event) {
        // get text from textPane
        String description = textPane.getText();

        // verify the text is less than 256 and greater than 1 character
        if(description.length() > 256 || description.length() < 1) {
            // prompt user that input is invalid
            promptInvalidInput();

            // break the function
            return;
        }

        // get the completion status from the radio button
        String status;
        if(completeButton.isSelected()) status = "Complete";
        else status = "Incomplete";

        // create a new item
        newItem = new Item(description,dueDate,status);

        // close the window
        stage.close();
    }

    // the user entered invalid input
    void promptInvalidInput() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invalidInput.fxml"));
            Parent root2 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        }
        catch(IOException e) {
            System.err.print("Error loading invalidInput GUI.");
        }
    }
}

