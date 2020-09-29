package dao;

import model.Department;
import model.user;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2OUserDaoTest {
    private Connection conn;
    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao DepartmentDao;

    private static  Sql2o sql2o;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        DepartmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearAll();
        conn.close();
    }


    @Test
    public void addingFoodSetsId() throws Exception {
        user testUser = setupNewuser();
        int originalUserId = testUser.getId();
        userDao.add(testUser);
        assertNotEquals(originalUserId, testUser.getId());
    }

    @Test
    public void addedusersAreReturnedFromGetAll() throws Exception {
        user testuser = setupNewuser();
        userDao.add(testuser);
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void nousersReturnsEmptyList() throws Exception {
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectuser() throws Exception {
        user user = setupNewuser();
        userDao.add(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        user testUser = setupNewuser();
        user otherUser = setupNewuser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void addUserToDepartmentAddsTypeCorrectly() throws Exception {

        Department testDepartment = setupDepartment();
        Department altDepartment = setupAltDepartment();

        DepartmentDao.add(testDepartment);
        DepartmentDao.add(altDepartment);

        user testUser = setupNewuser();

        userDao.add(testUser);

        userDao.addUserToDepartment(testUser, testDepartment);
        userDao.addUserToDepartment(testUser, altDepartment);

        assertEquals(2, userDao.getAlldepartmentsForAuser(testUser.getId()).size());
    }

    @Test
    public void deleteingDepartmentAlsoUpdatesJoinTable() throws Exception {
        user testUser = new user("JohnVick", "Head-Chief", "Assisting senior supervisor");
        userDao.add(testUser);

        Department testDepartment = setupDepartment();
        DepartmentDao.add(testDepartment);

        Department altDepartment = setupAltDepartment();
        DepartmentDao.add(altDepartment);

        DepartmentDao.addDepartmentToUser(testDepartment, testUser);
        DepartmentDao.addDepartmentToUser(altDepartment, testUser);

        DepartmentDao.deleteById(testDepartment.getId());
        assertEquals(0, DepartmentDao.getAllUsersByDepartment(testDepartment.getId()).size());
    }

    // helpers

    public user setupNewuser(){
        return new user("JohnVick", "Head-Chief", "Assisting senior supervisor");
    }

    public Department setupDepartment (){
        Department department = new Department("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        DepartmentDao.add(department);
        return department;
    }

    public Department setupAltDepartment (){
        Department department = new Department("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        DepartmentDao.add(department);
        return department;
    }
}