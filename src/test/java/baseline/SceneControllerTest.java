package baseline;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SceneControllerTest extends SceneController {

    // initialize a test for inventory and listOfItems
    @BeforeEach
    void initLists() {
        // create items to populate lists
        Item a = new Item("Item1","2021-10-10","Complete");
        Item b = new Item("Item2","2021-1-1","Incomplete");
        Item c = new Item("Item3","N/A","Incomplete");

        // populate lists
        getInventory().add(a);
        getInventory().add(b);
        getInventory().add(c);
        setListOfItemsToAll();
    }

    // reset item index after each method
    @AfterEach
    void reset() {
        // clear lists
        getListOfItems().clear();
        getInventory().clear();

        // reset item ID's
        Item.resetIndex();
    }

    // verify the method returns listOfItems
    @Test
    void test_getListOfItems() {
        assertNotNull(getListOfItems());
    }

    // verify the method return the current status view
    @Test
    void test_getView() {
        // status by default is set to one, so verify that the view is one
        assertEquals(1,getView());
    }

    // verify the listOfItems is changed to all items from inventory
    @Test
    void test_setListOfItemsToAll() {
        // call the method
        setListOfItemsToAll();

        // verify the number is 1
        assertEquals(1,getView());

        // verify that every value in the list matches inventory
        for(int i = 0; i < getInventory().size(); i++) {
            assertEquals(getListOfItems().get(i).getStatus(),getInventory().get(i).getStatus());
        }
    }

    // verify the listOfItems is changed to only complete items from inventory
    @Test
    void test_setListOfItemsToComplete() {
        // call the method
        setListOfItemsToComplete();

        // verify the number is 2
        assertEquals(2,getView());

        // verify that every value in the list is "Complete"
        for(int i = 0; i < getListOfItems().size(); i++) {
            assertEquals("Complete",getListOfItems().get(i).getStatus());
        }
    }

    // verify the listOfItems is changed to only incomplete items from inventory
    @Test
    void test_setListOfItemsToIncomplete() {
        // call the method
        setListOfItemsToIncomplete();

        // verify the number is 3
        assertEquals(3,getView());

        // verify that every value in the list is "Complete"
        for(int i = 0; i < getListOfItems().size(); i++) {
            assertEquals("Incomplete",getListOfItems().get(i).getStatus());
        }
    }

    // test method that finds item index
    @Test
    void test_findIndexOfID() {
        // ensure that I2 is item3
        int x = findIndexOfID(2);
        assertEquals(2,x);

        // ensure that an out-of-bounds ID returns 0
        x = findIndexOfID(3);
        assertEquals(-1,x);
    }

    // test the method that adds an item to list IF the completion status matches for ALL items
    @Test
    void test_addItemToListIfStatusMatches() {
        // try to add a complete item
        addItemToListIfStatusMatches(new Item("Complete Item","N/A","Complete"));

        // verify the list size is now 4 (three from BeforeEach)
        assertEquals(4,getListOfItems().size());

        // try to add an incomplete item
        addItemToListIfStatusMatches(new Item("Incomplete Item","N/A","Incomplete"));

        // verify the list size increased to 5
        assertEquals(5,getListOfItems().size());
    }

    // test the method that adds an item to list IF the completion status matches for Complete items
    @Test
    void test_addItemToListIfStatusMatchesForComplete() {
        // set values to complete
        setListOfItemsToComplete();

        // try to add a complete item
        addItemToListIfStatusMatches(new Item("Complete Item","N/A","Complete"));

        // verify the list size is now 2 (one from BeforeEach)
        assertEquals(2,getListOfItems().size());

        // try to add an incomplete item
        addItemToListIfStatusMatches(new Item("Incomplete Item","N/A","Incomplete"));

        // verify the list size is still 2
        assertEquals(2,getListOfItems().size());
    }

    // test the method that adds an item to list IF the completion status matches for incomplete
    @Test
    void test_addItemToListIfStatusMatchesForIncomplete() {
        // set values to complete
        setListOfItemsToIncomplete();

        // try to add a complete item
        addItemToListIfStatusMatches(new Item("Complete Item","N/A","Complete"));

        // verify the list size is 2 (2 from BeforeEach)
        assertEquals(2,getListOfItems().size());

        // try to add an incomplete item
        addItemToListIfStatusMatches(new Item("Incomplete Item","N/A","Incomplete"));

        // verify the list size increased to 3
        assertEquals(3,getListOfItems().size());
    }

    // test that date value gets changed
    @Test
    void test_modifyDates() {
        // grab date from object 1
        String oldDate = getInventory().get(0).getDueDate();

        // change date
        modifyDates(0, 0, "2000-12-25");

        // verify both dates changed
        assertNotEquals(oldDate,getInventory().get(0).getDueDate());
        assertNotEquals(oldDate,getListOfItems().get(0).getDueDate());
    }

    // test that description value gets changed
    @Test
    void test_modifyDescriptions() {
        // grab description from object 1
        String oldDescription = getInventory().get(0).getDescription();

        // change date
        modifyDescriptions(0, 0, "Item was changed");

        // verify both dates changed
        assertNotEquals(oldDescription,getInventory().get(0).getDescription());
        assertNotEquals(oldDescription,getListOfItems().get(0).getDescription());
    }

    // test that the method is true if the input is not between 1 and 256 characters
    @Test
    void test_isInputInvalid() {
        // create valid input
        String valid = "This is a valid string";

        // assert method finds string valid
        assertFalse(isInputInvalid(valid));

        // create invalid strings (sizes 0 and 257)
        String tooShort = "";
        String tooLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // assert method finds both strings invalid
        assertTrue(isInputInvalid(tooShort));
        assertTrue(isInputInvalid(tooLong));
    }

    // test that status value gets changed
    @Test
    void test_modifyStatus() {
        // grab date from object 1
        String oldStatus = getInventory().get(0).getStatus();

        // change date
        modifyStatus(0, 0, "Incomplete");

        // verify both dates changed
        assertNotEquals(oldStatus,getInventory().get(0).getStatus());
        assertNotEquals(oldStatus,getListOfItems().get(0).getStatus());
    }

    // test that all items are cleared from both lists, and item indexes are reset
    @Test
    void test_clearItems() {
        // call method
        clearItems();

        // assert both lists are empty
        assertTrue(getInventory().isEmpty());
        assertTrue(getListOfItems().isEmpty());

        // assert that the view is reset to 1
        assertEquals(1,getView());
    }

    // test that inventory is changed to an imported list
    @Test
    void test_insertLoadedList() {
        // create list to "load" in
        List<Item> loadedList = new ArrayList<>();
        loadedList.add(new Item("Item 4","1900-01-01","Incomplete"));

        // "load" new list into inventory
        insertLoadedList(loadedList);

        // verify new list is new inventory
        assertEquals(1,getInventory().size());
        assertEquals("Item 4",getInventory().get(0).getDescription());
        assertEquals("1900-01-01",getInventory().get(0).getDueDate());
        assertEquals("Incomplete",getInventory().get(0).getStatus());
    }
}