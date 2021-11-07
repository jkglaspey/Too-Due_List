package baseline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class AddItemController {

    // button for marking item as complete
    @FXML
    private RadioButton completeButton;

    // calendar item that lets user select a due date
    @FXML
    private DatePicker datePicker;

    // text pane to enter a description
    @FXML
    private TextField textPane;

    // button which adds the new item
    @FXML
    private Button button;

    // string which holds the due date
    private String dueDate = "N/A";

    // item which will be returned
    private Item newItem;

    // stage object
    private final Stage stage;

    // initialize values
    public AddItemController() {
        stage = new Stage();
    }

    // method that opens the default window and returns an item after the process is completed
    public Item display() {
        try{
            // load new fxml loader, but keep stage as one thread
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addItem.fxml"));
            fxmlLoader.setController(this);

            // load stage
            Parent root1 = fxmlLoader.load();
            stage.setScene(new Scene(root1));

            // show window, but wait for add button to be clicked
            stage.showAndWait();

            // return created item
            return newItem;
        }
        catch(IOException e) {
            System.err.print("Error loading addItem GUI.");
        }
        // item creation fails
        return null;
    }

    // change the due date to last selected OR null
    @FXML
    void setDueDate() {
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
    void saveItem() {
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

        // close the stage
        ((Stage) button.getScene().getWindow()).close();
    }

    // the user entered invalid input
    private void promptInvalidInput() {
        try{
            // load new fxml loader, and set a new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invalidInput.fxml"));
            Parent root2 = fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.show();
        }
        catch(IOException e) {
            System.err.print("Error loading invalidInput GUI.");
        }
    }
}

