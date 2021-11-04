/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneControllerTest {

    //  ----- GUI tests ----- //

    @Test
    void addItemToList() {
        // Trigger GUI "Add Item" button
        // Assert no exception was thrown
        // Assert the item list now is 1 larger
    }

    @Test
    void addItemToListButThereIsTooManyItems() {
        // Trigger GUI "Add Item" button
        // Assert that "MoreThan100ObjectsController" scene was opened
    }

    @Test
    void changeTitle() {
        // Trigger the title change with the text field
        // Assert no exception was thrown
        // Assert the new title is set with getTitle() method
    }

    @Test
    void changeTitleButSizeTooSmall() {
        // Trigger the title change with the text field
        // Assert that "invalidInput" scene was created
    }

    @Test
    void deleteItem() {
        // Trigger delete items menu button with 1 item selected
        // Assert no exception was thrown
        // Assert the list of items is now 1 smaller
    }

    @Test
    void deleteAllItems() {
        // Trigger delete items menu button with all items selected
        // Assert no exception was thrown
        // Assert the list of items is now size 0
    }

    @Test
    void loadLists() {
        // Trigger load lists menu button
        // Assert no exception was thrown
        // Logic of this method is handled in different test case
    }

    @Test
    void saveLists() {
        // Trigger save lists menu button
        // Assert no exception was thrown
        // Logic of this method is handled in different test case
    }

    @Test
    void setDate() {
        // Trigger new date selection on the DatePicker
        // Assert no exception was thrown
        // Assert that the String passed to the ItemCell is a matching String
    }

    @Test
    void removeDate() {
        // Trigger an input with no text in the DatePicker object
        // Assert no exception was thrown
        // Assert that the ItemCell does not have a date value set
    }

    @Test
    void setDescription() {
        // Trigger new description from text pane
        // Get text pane
        // Assert no exception was thrown
        // Assert that the String passed to the ItemCell is a matching String
    }

    @Test
    void setDescriptionButSizeTooSmall() {
        // Trigger new description from text pane
        // Get text pane
        // Assert that "invalidInput" scene was opened
    }

    @Test
    void setDescriptionButSizeTooLarge() {
        // Trigger new description from text pane
        // Get text pane
        // Assert that "invalidInput" scene was opened
    }

    @Test
    void clickCompleteButton() {
        // Trigger "Complete" button
        // Assert no exception was thrown
        // Assert that the String passed to the ItemCell is "Complete"
    }

    @Test
    void clickIncompleteButton() {
        // Trigger "Incomplete" button
        // Assert no exception was thrown
        // Assert that the String passed to the ItemCell is "Incomplete"
    }

    @Test
    void viewAllItems() {
        // Trigger "view all items" menu button
        // Assert no exception was thrown
        // Loop through listOfItems
        // Assert every value in listViewOfItems matches every value in inventory list
    }

    @Test
    void viewCompleteItems() {
        // Trigger "view complete items" menu button
        // Assert no exception was thrown
        // Loop through listViewOfItems
        // Assert every value in listViewOfItems is set to complete
    }

    @Test
    void viewIncompleteItems() {
        // Trigger "view incomplete items" menu button
        // Assert no exception was thrown
        // Loop through listViewOfItems
        // Assert every value in listViewOfItems is set to incomplete
    }

    @Test
    void viewUserGuide() {
        // Trigger the User Guide menu button
        // Assert no exception was thrown
        // Logic of this method is in another method
    }
}