package dao;

import model.Department;
import model.user;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;


    public Sql2oUserDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(user user) {
        String sql = "INSERT INTO users (name, position, roles) VALUES (:name, :position, :roles);"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<user> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(user.class);

        }
    }

    @Override
    public user findUserById(int id){
        String sql = "SELECT * FROM users WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(user.class);
        }
    }

    @Override
    public void addUserToDepartment(user user, Department department){
        String sql = "INSERT INTO departments_users (departmentId, UserId) VALUES (:departmentId, :UserId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("UserId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAlldepartmentsForAuser(int UserId) {
        ArrayList<Department> departments = new ArrayList<>();

        String joinQuery = "SELECT departmentId FROM departments_users WHERE UserId = :UserId";

        try (Connection con = sql2o.open()) {
            List<Integer> alldepartmentIds = con.createQuery(joinQuery)
                    .addParameter("UserId", UserId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departmentId : alldepartmentIds){
                String DepartmentQuery = "SELECT * FROM departments WHERE id = :departmentId";
                departments.add(
                        con.createQuery(DepartmentQuery)
                                .addParameter("departmentId", departmentId)
                                .executeAndFetchFirst(Department.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id = :id";
        String deleteJoin = "DELETE from departments_users WHERE departmentId = :departmentId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
