package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class News implements Comparable<News> {
    private String headline;
    private String content;
    private String author;
    private int id;
    private int departmentid; //will be used to connect Department to News (one-to-many)
    private long createdat;
    private String formattedCreatedAt;

    public News(String headline, String content, String author, int departmentid) {
        this.headline = headline;
        this.content = content;
        this.author = author;
        this.departmentid = departmentid;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt(); //we'll make me in a minute
    }

    //Create comparison

    @Override
    public int compareTo(News newsObject) {
        if (this.createdat < newsObject.createdat)
        {
            return -1; //this object was made earlier than the second object.
        }
        else if (this.createdat > newsObject.createdat){ //this object was made later than the second object
            return 1;
        }
        else {
            return 0; //they were made at the same time, which is very unlikely, but mathematically not impossible.
        }
    }


    //Hashcode n equals override


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                departmentid == news.departmentid &&
                Objects.equals(headline, news.headline) &&
                Objects.equals(content, news.content) &&
                Objects.equals(author, news.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, content, author, id, departmentid);
    }

    //Getters


    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    public int getId() {
        return id;
    }

    public int getdepartmentid() {
        return departmentid;
    }

    public long getCreatedat() {
        return createdat;
    }

    public String getFormattedCreatedAt(){
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a"; //see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }


    //Setters

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setdepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public void setCreatedat() {
        this.createdat = System.currentTimeMillis(); // It'll become clear soon why we need this explicit setter
    }

    public void setFormattedCreatedAt(){
        Date date = new Date(this.createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }




}
