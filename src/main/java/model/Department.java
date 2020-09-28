package model;

import java.util.Objects;

public class Department {

    private String name;
    private String about;
    private String website;
    private String email;
    private int id;


    public Department(String name, String about) {
        this.name = name;
        this.about = about;
        this.website = "no website listed";
        this.email = "no email available";
    }

    public Department(String name, String about, String website, String email) {
        this.name = name;
        this.about = about;
        this.website = website;
        this.email = email;
    }

    //override equals n hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(about, that.about) &&
                Objects.equals(website, that.website) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, about, website, email, id);
    }


    ///Getters

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }


    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }


}
