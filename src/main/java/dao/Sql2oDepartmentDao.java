package dao;

import model.Department;
import model.user;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {


    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (name, about, website, email) VALUES (:name, :about, :website, :email)";
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
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class));
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public Department findById(int departmentid) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", departmentid)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public void addDepartmentToUser(Department department, user user){
        String sql = "INSERT INTO departments_users (departmentid, Userid) VALUES (:departmentid, :Userid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentid", department.getId())
                    .addParameter("Userid", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<user> getAllUsersByDepartment(int departmentid){
        ArrayList<user> users = new ArrayList<>();

        String joinQuery = "SELECT userid FROM departments_users WHERE departmentid = :departmentid";

        try (Connection con = sql2o.open()) {
            List<Integer> allusersIds = con.createQuery(joinQuery)
                    .addParameter("departmentid", departmentid)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allusersIds){
                String userQuery = "SELECT * FROM users WHERE id = :userid";
                users.add(
                        con.createQuery(userQuery)
                                .addParameter("userid", userId)
                                .executeAndFetchFirst(user.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return users;
    }


    @Override
    public void update(int id, String name, String about, String website, String email){
        String sql = "UPDATE departments SET (id,name, about, website, email)=(:id, :name, :about, :website, :email) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("about", about)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .executeUpdate();
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String deleteJoin = "DELETE from departments_users WHERE departmentid = :departmentid";
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
