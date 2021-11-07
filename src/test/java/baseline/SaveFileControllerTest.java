/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileControllerTest {

    // initialize a test object
    SaveFileController test;
    @BeforeEach
    void initController() {
        test = new SaveFileController("Test");
    }

    // assert that the outputStream method creates a successful new file and stream
    @Test
    void test_createOutputStream() {
        Formatter result = test.createOutputStream("./data/test","testOutputStream");

        // assert the file was created
        File testFile = new File("./data/test/testOutputStream.txt");
        assertTrue(testFile.exists());

        // assert the stream is not null
        assertNotNull(result);

        // close stream
        result.close();

        // delete the file for test repeatability
        testFile.delete();
    }

    // assert that save works appropriately
    @Test
    void test_save() {
        // create path and name strings
        String path = "./data/test";
        String name = "testSave";

        // populate a list with a test item
        List<Item> values = new ArrayList<>();
        values.add(new Item("Item","N/A","Incomplete"));

        // call the method
        test.forceSaveList(values,path,name);

        // create Scanner to read file
        Scanner readTestFile = null;
        File testFile = null;
        try {
            testFile = new File("./data/test/testSave.txt");
            readTestFile = new Scanner(testFile);
        }
        catch (FileNotFoundException e) {
            fail();
        }

        // test that the file contains the correct size
        int numOfItems = readTestFile.nextInt();
        assertEquals(1,numOfItems);

        // skip to next line
        readTestFile.nextLine();

        // assert the item was stored correctly
        assertEquals("Item",readTestFile.nextLine());
        assertEquals("N/A",readTestFile.nextLine());
        assertEquals("Incomplete",readTestFile.nextLine());

        // close stream
        readTestFile.close();

        // delete the file for test repeatability
        testFile.delete();
    }
}