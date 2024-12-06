package com.example.uniapplication.Controller;


import com.example.uniapplication.users.AuthenticationInterface;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import com.example.uniapplication.users.Admin;
import com.example.uniapplication.users.Student;
import com.example.uniapplication.users.Teacher;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginController {

    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;
    @FXML
    private Text messageTrue;
    @FXML
    private Text messageFalse;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private UserDAO userDAO;

    protected AuthenticationInterface authentication;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    public void exitButtonOnAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        int roleId = userDAO.getRoleFromDb(username, password);

        if (username.isEmpty() || password.isEmpty()) {
            messageFalse.setText("Enter name and password");
        } else if (roleId == 1) {
            authentication = new Admin(username, password);
            authentication.login(messageTrue, loginButton, new FXMLLoader(getClass().getResource("layout/admin-page.fxml")));
        } else if (roleId == 2) {
            authentication = new Teacher(username, password);
            authentication.login(messageTrue, loginButton, new FXMLLoader(getClass().getResource("layout/teacher-page.fxml")));
        } else if (roleId == 3) {
            authentication = new Student(username, password);
            authentication.login(messageTrue, loginButton, new FXMLLoader(getClass().getResource("layout/student-page.fxml")));
        }


    }
}
