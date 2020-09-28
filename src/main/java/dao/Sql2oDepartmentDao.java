package dao;

import model.Department;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (name, about, website) VALUES (:name, :about, :website)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentToUser(Department department, User user) {
        String sql = "INSERT INTO departments_users (departmentid, userid) VALUES (:departmentid, :userid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentid", department.getId())
                    .addParameter("userid", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class));
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<User> getAllUsersByDepartment(int departmentId) {

        ArrayList<User>  users = new ArrayList<>();
        String joinQuery = "SELECT userid FROM departments_users WHERE departmentid = :departmentid";

        try (Connection con = sql2o.open()) {
            List<Integer> allUsersIds = con.createQuery(joinQuery)
                    .addParameter("departmentid", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allUsersIds) {
                String usersQuery = "SELECT * FROM users WHERE id = :userid";
                users.add(
                        con.createQuery(usersQuery)
                        .addParameter("userid", userId)
                        .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return users;
    }

    @Override
    public Department findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurants WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public void update(int id, String name, String about, String website) {
        String sql = "UPDATE departments SET (id, name, about, website)=(:id, :name, :about, :website) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("about", about)
                    .addParameter("website", website)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departments_users WHERE departmentsid = :departmentsid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentid", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
