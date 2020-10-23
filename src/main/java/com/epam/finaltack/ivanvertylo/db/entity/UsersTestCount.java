package com.epam.finaltack.ivanvertylo.db.entity;

import java.util.Objects;

public class UsersTestCount {
    private User user;
    private Integer count;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersTestCount that = (UsersTestCount) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, count);
    }

    @Override
    public String toString() {
        return "UsersTestCount{" +
                "user=" + user +
                ", count=" + count +
                '}';
    }
}
