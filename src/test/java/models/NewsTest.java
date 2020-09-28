package models;

import model.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() {
        News testNews = setupNews();
        assertEquals("Great News", testNews.getContent());
    }

    @Test
    public void setContent() {
        News testNews = setupNews();
        testNews.setContent("Warzone in Russia :(");
        assertNotEquals("Great News", testNews.getContent());
    }


    @Test
    public void setAuthor() {
        News testNews = setupNews();
        testNews.setAuthor("Mike");
        assertNotEquals("Kim", testNews.getAuthor());
    }


    @Test
    public void getdepartmentid() {
        News testNews = setupNews();
        assertEquals(1, testNews.getdepartmentid());
    }

    @Test
    public void setdepartmentid() {
        News testNews = setupNews();
        testNews.setdepartmentid(10);
        assertNotEquals(1, testNews.getdepartmentid());
    }

    @Test
    public void setId() {
        News testNews = setupNews();
        testNews.setId(5);
        assertEquals(5, testNews.getId());
    }

    // helper
    public News setupNews (){
        return new News("Kid Gets Whacked", "Great News", "Joel", 1);
    }

}