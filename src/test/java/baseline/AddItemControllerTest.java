/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static baseline.AddItemController.isValidInput;
import static org.junit.jupiter.api.Assertions.*;

class AddItemControllerTest {

    // create a new controller object
    AddItemController test;
    @BeforeEach
    void initController() {
        test = new AddItemController("Test constructor");
    }

    // test the method that gets / sets the due date
    @Test
    void test_setDueDate() {
        // test valid date
        test.setDueDate("2020-10-10");
        assertEquals("2020-10-10",test.getDueDate());

        // test empty date
        test.setDueDate("");
        assertEquals("N/A",test.getDueDate());
    }

    // test the method that determines if a string is valid input
    @Test
    void test_isValidInput() {
        // test valid string
        assertTrue(isValidInput("Valid Input"));

        // test invalid strings
        assertFalse(isValidInput(""));
        assertFalse(isValidInput("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    // everything else is just grabbing values from controllers
}