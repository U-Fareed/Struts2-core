package com.example.actions;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
    private String message;
    private String name;

    public String execute() {
        message = "Hello, " + name + "! Struts2 is working.";
        return SUCCESS;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }
}