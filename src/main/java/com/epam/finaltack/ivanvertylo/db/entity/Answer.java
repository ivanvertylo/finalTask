package com.epam.finaltack.ivanvertylo.db.entity;

import java.util.Objects;

public class Answer {
    private Integer id;
    private String name;
    private Boolean isRight;

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

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(name, answer.name) &&
                Objects.equals(isRight, answer.isRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isRight);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isRight=" + isRight +
                '}';
    }
}
