package dao;

import model.News;

import java.util.List;

public interface NewsDao {

//    Create
    void add(News news);

//    Read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int departmentId);
    List<News> getAllNewsByDepartmentSortedNewestToOldest(int departmentId);

//    Delete
    void deleteById(int id);
    void clearAll();
}
