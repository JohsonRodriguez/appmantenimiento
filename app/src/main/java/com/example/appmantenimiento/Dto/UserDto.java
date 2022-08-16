package com.example.appmantenimiento.Dto;

public class UserDto {
    private String name;
    private String rol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UserDto() {
    }

    public UserDto(String name, String rol) {
        this.name = name;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
