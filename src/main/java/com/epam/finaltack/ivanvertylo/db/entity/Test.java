package com.epam.finaltack.ivanvertylo.db.entity;

import java.util.Objects;

public class Test {
    private Integer id;
    private String name;
    private String subject;
    private String author;
    private Boolean isPublic;
    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(name, test.name) &&
                Objects.equals(subject, test.subject) &&
                Objects.equals(author, test.author) &&
                Objects.equals(isPublic, test.isPublic) &&
                Objects.equals(time, test.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subject, author, isPublic, time);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", author='" + author + '\'' +
                ", isPublic=" + isPublic +
                ", time=" + time +
                '}';
    }
}
