package com.sxau.novel.LoginUtils;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String username;
    public String password;
    public String age;
    public String gender;
    public String nickname;

    public User() {
        id = 0;
        username = "";
        age = "";
        gender = "";
        nickname = "";
        password = "";
        //super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String age, String gender,String nickname) {
        super();
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender=gender;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername()
    {
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
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNickname(){
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
    }
    @Override
    public String toString() {
        return "Userinfo [id=" + id + ", username=" + username + ", password="
                + password + ", age=" + age + ", gender=" + gender + ",nickname=" + nickname +"]";
    }
}

