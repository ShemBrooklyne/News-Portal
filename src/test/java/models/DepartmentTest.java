package models;

import model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Department testDepartment = setupDepartment();
        assertEquals("DailyNation", testDepartment.getName());
    }



    @Test
    public void getWebsiteReturnsCorrectWebsite() throws Exception {
        Department testDepartment = setupAltDepartment();
        assertEquals("www.DailyNation.com", testDepartment.getWebsite());
    }

    @Test
    public void getEmailReturnsCorrectEmail() throws Exception {
        Department testDepartment = setupAltDepartment();
        assertEquals("DailyNation@nation.com", testDepartment.getEmail());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Department testDepartment = setupDepartment();
        testDepartment.setName("DailyNation");
        assertNotEquals("Human Resources",testDepartment.getName());
    }


    @Test
    public void setWebsiteSetsCorrectWebsite() throws Exception {
        Department testDepartment = setupDepartment();
        testDepartment.setWebsite("www.DailyNation.com");
        assertNotEquals("DailyNation@nation.com", testDepartment.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectEmail() throws Exception {
        Department testDepartment = setupDepartment();
        testDepartment.setEmail("DailyNation@nation.com");
        assertNotEquals("www.DailyNation.com", testDepartment.getEmail());
    }

    public Department setupDepartment (){
        return new Department("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
    }

    public Department setupAltDepartment (){
        return new Department("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
    }
}