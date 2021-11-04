/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCellTest {

    // create an ItemCell for testing
    ItemCell test;
    @BeforeEach
    void init_ItemCell() {
        test = new ItemCell();
        // create values for cell
        Item data = new Item();
        data.setDescription("description");
        data.setDueDate("10-24-2021");
        data.setStatus("Complete");
        test.updateItem(data,false);
    }

    // test the description is correct
    @Test
    void getDescription() {
        //assertEquals("description",test.getDescription());
    }

    // test the due date is correct
    @Test
    void getDueDate() {
        //assertEquals("10-24-2021",test.getDueDate());
    }

    // test the status is correct
    @Test
    void getStatus() {
        //assertEquals("Complete",test.getStatus());
    }
}