package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class News {
    private String headline;
    private String content;
    private String author;
    private int id;
    private int newsId;
    private long createdat;
    private String formattedCreatedAt;

    public News(String headline, String content, String author, int newsId) {
        this.headline = headline;
        this.content = content;
        this.author = author;
        this.newsId = newsId;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

//    setters

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

//    getters

    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public int getNewsId() {
        return newsId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                newsId == news.newsId &&
                Objects.equals(headline, news.headline) &&
                Objects.equals(content, news.content) &&
                Objects.equals(author, news.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, content, author, id, newsId);
    }
}
