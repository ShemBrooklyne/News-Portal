package dao;

import model.Department;
import model.user;

import java.util.List;

public interface DepartmentDao {


    //create
    void add(Department department);
    void addDepartmentToUser(Department department, user user);

    //read
    List<Department> getAll();
    List<user> getAllUsersByDepartment(int departmentid);


    Department findById(int id);
    // List<user> getAllusersForADepartment(int departmentid);

    //update
    void update(int id, String name, String about, String website, String email);

    //delete
    void deleteById(int id);
    void clearAll();
}
