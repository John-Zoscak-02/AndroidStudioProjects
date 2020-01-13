package com.example.account_project;

import java.util.Objects;

public class Account {
    private String username;
    private String password;
    private String data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) &&
                Objects.equals(password, account.password) &&
                Objects.equals(data, account.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, data);
    }

    public Account(String username, String password, String data) {
        this.username = username;
        this.password = password;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
