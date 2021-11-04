/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

    // create item before running each test
    Item test;
    @BeforeEach
    void init_Item() {
        test = new Item();
    }

    // test the methods that modify the description
    // note: since we need to use a mutator method to set a value anyways, we can combine the set and get methods
    @Test
    void modifyDescription() {
        // testing 2 String to verify it isn't hard-coded

        // test.setDescription("Test");
        // assertEquals("Test",test.getDescription());

        // test.setDescription("This is not hard-coded!");
        // assertEquals("This is not hard-coded!",test.getDescription());
    }

    // test the methods that modify the due date
    // note: since we need to use a mutator method to set a value anyways, we can combine the set and get methods
    // note: values coming into this method are of date format, but it won't break the Item object if the String is not
    //  one
    @Test
    void modifyDueDate() {
        // testing 2 String to verify it isn't hard-coded

        // test.setDescription("10-25-1900");
        // assertEquals("10-25-1900",test.getDueDate());

        // test.setDescription("10-24-2021");
        // assertEquals("10-24-2021",test.getDueDate());
    }

    // test the method that changes the status of the Item
    // note: the string can only be one of two values
    @Test
    void modifyStatus() {
        // case 1: "Complete"
        //test.setStatus("Complete");
        //assertEquals("Complete",test.getStatus());

        // case 2: "Incomplete"
        // test.setStatus("Incomplete");
        // assertEquals("Incomplete",test.getStatus());
    }

    // test the method that sets the status to either one of two strings
    // note: the strings will only be one of two values, but it will not break the Item if it is not formatted
    //  correctly
    @Test
    void getStatus() {
        // assert the constructor sets the initial value to false
        // assertEquals("Incomplete",test.getStatus());
    }
}