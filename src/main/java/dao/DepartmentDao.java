package dao;

import model.Department;
import model.User;

import java.util.List;

public interface DepartmentDao {

    //    Create
    void add(Department department);
    void addDepartmentToUser(Department department, User user);


//    Read
    List<Department> getAll();
    List<User> getAllUsersByDepartment(int departmentId);

    Department findById(int id);



//    update
    void update(int id, String name, String about, String website);

//    Delete
    void deleteById(int id);

    void clearAll();

}



