package com.epam.finaltack.ivanvertylo.db.entity;

import java.util.Objects;

public class CountSubjects {
    private Integer count;
    private String name;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountSubjects that = (CountSubjects) o;
        return Objects.equals(count, that.count) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, name);
    }

    @Override
    public String toString() {
        return "CountSubjects{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}
