package models;

import model.user;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class userTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        user testUser = setupuser();
        assertEquals("Alex O. Simba", testUser.getName());
    }

    @Test
    public void setName() {
        user testUser = setupuser();
        testUser.setName("Alex O. Simba");
        assertNotEquals("Alex O. Simb", testUser.getName());
    }

    @Test
    public void setId() {
        user testUser = setupuser();
        testUser.setId(5);
        assertEquals(5, testUser.getId());
    }

    // helper
    public user setupuser(){
        return new user("Alex O. Simba", "Editor", "Director");
    }
}