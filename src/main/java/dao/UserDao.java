package dao;

import model.Department;
import model.User;

import java.util.List;

public interface UserDao {

//    Create
    void add(User user);
    void addUserToDepartment(User user, Department department);

//    Read
    List<User> getAll();
    User findUserById(int id);
    List<Department> getAllDepartmentsForAUser(int id);

    void deleteById(int id);

    void clearAll();
}
