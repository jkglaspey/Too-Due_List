/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadFileControllerTest {

    // create object before testing
    LoadFileController test;
    @BeforeEach
    void initController() {
        test = new LoadFileController("Test");
    }

    // assert that load has no runtime errors
    @Test
    void load() {
        // test that we populate the array of values from a txt file (256Items.txt)
        test.forceLoad("./data/test/256Items.txt");

        // test that the first value stored correctly
        // note: the file reading is repetitive for each item, so verifying one stored verifies they all stored
        assertEquals("Item",test.getLoadedItems().get(0).getDescription());
        assertEquals("N/A",test.getLoadedItems().get(0).getDueDate());
        assertEquals("Incomplete",test.getLoadedItems().get(0).getStatus());

        // test that the list stored the proper number
        assertEquals(256,test.getLoadedItems().size());
    }
}