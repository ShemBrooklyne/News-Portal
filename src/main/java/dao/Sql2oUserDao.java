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
        String sql = "INSERT INTO departments_users (departmentid, userid) VALUES (:departmentid, :userid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentid", department.getId())
                    .addParameter("userid", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAlldepartmentsForAuser(int UserId) {
        ArrayList<Department> departments = new ArrayList<>();

        String joinQuery = "SELECT departmentid FROM departments_users WHERE userid = :userid";

        try (Connection con = sql2o.open()) {
            List<Integer> alldepartmentids = con.createQuery(joinQuery)
                    .addParameter("userid", UserId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departmentid : alldepartmentids){
                String DepartmentQuery = "SELECT * FROM departments WHERE id = :departmentid";
                departments.add(
                        con.createQuery(DepartmentQuery)
                                .addParameter("departmentid", departmentid)
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
        String deleteJoin = "DELETE from departments_users WHERE departmentid = :departmentid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentid", id)
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
