/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to create the basic object to store data about specific events.

package baseline;

public class Item {

    // create instance variables to store data

    // store description
    private String description;

    // store due-date
    private String dueDate;

    // store status of completion
    private String status;

    // initialize values
    // Precondition: description is at least 3 characters (checked by controller)
    public Item() {
        // set complete status to "Incomplete"
        // rest of the values will be added using set methods by the user through the GUI
    }

    // create method to get the description
    public String getDescription() {
        // return the description
        return null;
    }

    // create method to set the description to a new String
    // precondition: the description is at least 3 characters (checked by controller)
    public void setDescription(String description) {
        // check if the name is unique through ToDoList method isUnique
        // set this.description to the new description
    }

    // create method to get the due date
    public String getDueDate() {
        // return the date
        return null;
    }

    // create method to set the due date to a new date
    public void setDueDate(String dueDate) {
        // set this.dueDate to the new date
    }

    // create method to get the completion status
    public String getStatus() {
        // return the status
        return null;
    }

    // create method to set the completion status
    public void setStatus(String s) {
        // set this.status to either "Complete" or "Incomplete" depending on input
        // if s is neither, prompt user that it is an invalid input
    }
}