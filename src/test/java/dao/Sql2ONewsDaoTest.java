package dao;

import model.Department;
import model.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2ONewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao NewsDao;
    private Sql2oDepartmentDao DepartmentDao;

    private static  Sql2o sql2o;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        NewsDao = new Sql2oNewsDao(sql2o);
        DepartmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        NewsDao.clearAll();
        conn.close();
    }

    @Test
    public void timeStampIsReturnedCorrectly() throws Exception {
        Department testDepartment = setupDepartment();
        DepartmentDao.add(testDepartment);
        News testNews = new News("Kid Gets Whacked", "Kimani", "Joel", testDepartment.getId());
        NewsDao.add(testNews);

        long creationTime = testNews.getCreatedat();
        long savedTime = NewsDao.getAll().get(0).getCreatedat();
        String formattedCreationTime = testNews.getFormattedCreatedAt();
        String formattedSavedTime = NewsDao.getAll().get(0).getFormattedCreatedAt();
        assertEquals(formattedCreationTime,formattedSavedTime);
        assertEquals(creationTime, savedTime);
    }


    @Test
    public void addingnewsetsId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, NewsDao.getAll().size());
    }

    @Test
    public void getAllNewsByDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment(); //add in some extra data to see if it interferes
        News news1 = setupNewsForDepartment(testDepartment);
        News news2 = setupNewsForDepartment(testDepartment);
        News newsForOtherDepartment = setupNewsForDepartment(otherDepartment);
        assertEquals(2, NewsDao.getAllNewsByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        assertEquals(2, NewsDao.getAll().size());
        NewsDao.deleteById(testNews.getId());
        assertEquals(1, NewsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        NewsDao.clearAll();
        assertEquals(0, NewsDao.getAll().size());
    }

    //helpers

    public News setupNews() {
        News news = new News("Kid Gets Whacked", "Kimani", "Joel", 555);
        NewsDao.add(news);
        return news;
    }

    public News setupNewsForDepartment(Department department) {
        News news = new News("Kid Gets Whacked", "Kimani", "Joel", department.getId());
        NewsDao.add(news);
        return news;
    }

    public Department setupDepartment() {
        Department department = new Department("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        DepartmentDao.add(department);
        return department;
    }

//    @Test
//    public void newsAreReturnedInCorrectOrder() throws Exception {
//        Department testDepartment = setupDepartment();
//        DepartmentDao.add(testDepartment);
//        News testNews = new News("Captain Kirk", 3, "foodcoma!", testDepartment.getId());
//        NewsDao.add(testNews);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException ex){
//            ex.printStackTrace();
//        }
//
//        News testSecondNews = new News("Mr Spock", 1, "passable", testDepartment.getId());
//        NewsDao.add(testSecondNews);
//
//        assertEquals("passable", NewsDao.getAllNewsByDepartmentSortedNewestToOldest(testDepartment.getId()).get(0).getContent());
//    }

}