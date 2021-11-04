package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

    // the user clicks the add item button
    @FXML
    void saveItem(ActionEvent event) {
        // get text from textPane
        // verify the text is less than 256 characters
        // if not, open invalidInput gui and force user to fix it
        // otherwise,
        // get the due date from the calendar
        // get the completion status from the radio button
        // create a new item using the grabbed data
        // add item to inventory List in SceneController
        // add each item to specific table category in SceneController
        // a.k.a.
            // description to viewDescription
            // due date to viewDueDate
            // completion status to viewStatus
        // close the gui
    }
}

