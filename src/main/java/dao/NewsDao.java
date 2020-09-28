package dao;

import model.News;

import java.util.List;

public interface NewsDao {

    //create
    void add(News news);

    //read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int departmentId);
    List<News> getAllNewsByDepartmentSortedNewestToOldest(int departmentId);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
