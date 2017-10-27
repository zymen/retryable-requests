package net.zymen.retryablerequests.app.dto;

public class User {
    private String name;
    private String login;
    private Long age;

    public User() {
    }

    public User(String name, String login, Long age) {
        this.name = name;
        this.login = login;
        this.age = age;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
