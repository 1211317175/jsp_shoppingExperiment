package com.study.entity;

/**
 * @author lxy
 * @date 2020-05-10-11:41
 * @function
 **/
public class User {

    private String username;
    private String password;
    private Integer age;
    private String gender;
    private String email;

    public User(){};

    public User(String username, Integer age,String gender,String email) {
        this.username = username;
        this.age=age;
        this.gender=gender;
        this.email=email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
