package model;

import org.h2.engine.User;

import java.util.Objects;

public class user {
    private String name;
    private int id;
    private String position;
    private String roles;

    public user(String name, String position, String roles) {
        this.name = name;
        this.position = position;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof user)) return false;
        user user = (model.user) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(position, user.position) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, position, roles);
    }

    //Getters n setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

    public String getPosition() {
        return position;
    }
}
