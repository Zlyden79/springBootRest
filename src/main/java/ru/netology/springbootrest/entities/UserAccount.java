package ru.netology.springbootrest.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserAccount {
    private final String user;
    private String password;

    public UserAccount(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(user, that.user) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
