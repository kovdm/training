package com.chat.server;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
public class RegistrationPacket implements Serializable {

    private int id;
    private String name;
    private String phone;
    private String login;
    private String password;

    public RegistrationPacket(String name, String phone, String login, String password) {
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
