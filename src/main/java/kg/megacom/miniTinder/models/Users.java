package kg.megacom.miniTinder.models;

import kg.megacom.miniTinder.models.enums.Gender;

public class Users {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String info;
    private Gender gender;
    private boolean active;




    public Users(String name, String login, String password, String info,Gender gender) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.info = info;
        this.gender=gender;

    }

    public Users() {
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", info='" + info + '\'' +
                ", gender=" + gender +
                ", active=" + active +
                '}';
    }
}
