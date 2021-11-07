/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to prompt the user that there is no file found at a specific location.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FileNotFoundController {

    // button which closes popup
    @FXML
    private Button button;

    // method for closing popup
    @FXML
    void closePopup(ActionEvent event) {
        Stage stage3 = (Stage) button.getScene().getWindow();
        stage3.close();
    }
}
