/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Joshua Glaspey
 */

package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileControllerTest {

    // assert that save has no runtime errors
    @Test
    void save() {
        // Assert that no exception is thrown when:
        // Saving selected lists to a file at a valid file path on the local user's pc.

        // Assert that an exception is thrown when:
        // No lists are selected.

        // Assert that an exception is thrown when:
        // Saving selected lists to a file at an invalid file path in the pc.

        // Assert that an exception is thrown when:
        // Using illegal characters in a .txt file declaration, so the File object was never created successfully.
    }
}