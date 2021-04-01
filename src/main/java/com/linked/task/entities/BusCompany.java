package com.linked.task.entities;

public enum BusCompany {
    Posh("Posh Bus Company"),
    Grotty("Grotty Bus Company");

    private String name;

    BusCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
