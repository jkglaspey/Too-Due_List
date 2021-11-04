/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to format a cell that can be used to preview data about an item on the main GUI program.

package baseline;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class ItemCell extends ListCell<Item> {

    // declare custom values
    private final HBox hbox = new HBox(8.0);
    private final Label description = new Label();
    private final Label dueDate = new Label();
    private final Label status = new Label();

    // initialize and configure each element
    public ItemCell() {
        // note: the pseudocode for this segment was drawn directly from the textbook Ch 13.6

        // align data to center

        // ensure description will fit in bounds
        // left align description
        // add description to list

        // ensure due date will fit in bounds
        // left align date
        // add due date to list

        // ensure status will fit in bounds
        // left align date
        // add status to list

        // set size to match the length of the string
    }

    // configure each custom cell
    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item,empty); // required

        // test if item is empty
        if (empty || item == null) {
            setText(null);
        }
        // otherwise, show data
        else {
            // set the description
            // set the due date
            // set the status
            // if status is "complete" --> change color to green
            // if status is "incomplete" --> change color to red
        }
    }

    // create method to return the description
    public String getDescription() {
        return description.getText();
    }

    // create method to return the due date
    public String getDueDate() {
        return dueDate.getText();
    }

    // create method to return the status
    public String getStatus() {
        return status.getText();
    }
}
