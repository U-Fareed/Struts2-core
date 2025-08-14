package com.example.actions;

import com.example.dao.UserDAO;
import com.example.models.User;
import com.example.utils.HibernateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserAction extends ActionSupport
        implements ModelDriven<User>, Preparable {

    private User user;
    private List<User> users;
    private String message;
    private boolean emailExists;
    private final UserDAO userDAO;

    public UserAction() {
        this.userDAO = new UserDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public void prepare() {
        if (user == null) {
            user = new User();
        }
    }

    public String register() {
        userDAO.saveUser(user);
        message = "User registered successfully!";
        return SUCCESS;
    }

    public void validateRegister() {
        if (StringUtils.isBlank(user.getUsername())) {
            addFieldError("user.username", "Username is required");
        } else if (user.getUsername().length() < 3 ||
                user.getUsername().length() > 20) {
            addFieldError("user.username", "Username must be 3-20 characters");
        }

        if (StringUtils.isBlank(user.getEmail())) {
            addFieldError("user.email", "Email is required");
        } else if (!isValidEmail(user.getEmail())) {
            addFieldError("user.email", "Invalid email format");
        } else if (userDAO.findByEmail(user.getEmail()) != null) {
            addFieldError("user.email", "Email already exists");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            addFieldError("user.password", "Password is required");
        } else if (user.getPassword().length() < 6) {
            addFieldError("user.password", "Password must be at least 6 characters");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public String list() {
        users = userDAO.findAll();
        return SUCCESS;
    }

    public String checkEmail() {
        emailExists = userDAO.findByEmail(user.getEmail()) != null;
        return SUCCESS;
    }

    // Getters and setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<User> getUsers() { return users; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isEmailExists() { return emailExists; }
}