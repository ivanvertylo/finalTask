package com.epam.finaltack.ivanvertylo.db.entity;

import java.util.Objects;

public class TestPoints {
    private Test test;
    private Integer points;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPoints that = (TestPoints) o;
        return Objects.equals(test, that.test) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test, points);
    }

    @Override
    public String toString() {
        return "TestPoints{" +
                "test=" + test +
                ", points=" + points +
                '}';
    }
}
