package model;

import java.util.Objects;

public class Department {

    private String name;
    private String about;
    private String website;
    private int id;


    public Department(String name, String about, String website, int id) {
        this.name = name;
        this.about = about;
        this.website = website;
    }

    public Department(String name, String about) {
        this.name = name;
        this.about = about;
        this.website = "no websites listed";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getWebsite() {
        return website;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(about, that.about) &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, about, website, id);
    }

}


