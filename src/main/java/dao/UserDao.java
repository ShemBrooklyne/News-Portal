package dao;

import model.Department;
import model.user;

import java.util.List;

public interface UserDao {

    //create
    void add(user user);
    void addUserToDepartment(user user, Department department);


    //read
    List<user> getAll();
    user findUserById(int id);
    List<Department> getAlldepartmentsForAuser(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
