/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.Test;

class LoadFileControllerTest {

    // assert that load has no runtime errors
    @Test
    void load() {
        // Assert that there's no exception thrown when:
        // Running load() from an existing file path with valid To-Do List examples.

        // Assert that an exception is thrown when:
        // Running load() from an existing file path with incorrectly formatted To-Do Lists.

        // Assert that an exception is thrown when:
        // Running load() from a non-existent file path.
    }

    // assert that running load when there are too many lists will throw an error
    @Test
    void load_TooManyLists() {
        // Create a test file with at least 257 To-Do's.
        // Assert that the list populates with up to 256 lists.
        // Assert that the GUI "MoreThan256ObjectsController" was added to the scene.
    }
}