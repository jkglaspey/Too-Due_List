/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to create the basic object to store data about specific events.

package baseline;

public class Item {

    // store description
    private String description;

    // store due-date
    private String dueDate;

    // store status of completion
    private String status;

    // store item ID
    private final int id;

    // create counter for item ID
    private static int index = 0;

    // create new item object
    // precondition: description is of appropriate length
    // precondition: due date is of YYYY-MM-DD
    // precondition: status is either "Complete" or "Incomplete"
    public Item(String description, String dueDate, String status) {
        // set the values
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        id = index;

        // increment counter
        index++;
    }

    // create method to get the description
    public String getDescription() {
        return description;
    }

    // create method to set the description to a new String
    // precondition: the description is a valid length
    public void setDescription(String description) {
        this.description = description;
    }

    // create method to get the due date
    public String getDueDate() {
        return dueDate;
    }

    // create method to set the due date to a new date
    // precondition: the date is formatted as YYYY-MM-DD
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // create method to get the completion status
    public String getStatus() {
        return status;
    }

    // create method to set the completion status
    // precondition: string is either "Complete" or "Incomplete"
    public void setStatus(String s) { status = s; }

    // create method to get the id
    public int getId() { return id; }

    // create method to reset the index
    // note: only called if the list is reset
    public static void resetIndex() {
        index = 0;
    }
}