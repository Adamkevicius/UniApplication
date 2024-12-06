package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTeacherController {
    @FXML
    private Button addteachertButton;

    @FXML
    private TextField teacherName;

    @FXML
    private TextField teacherSurname;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void createTeacherOnAction(ActionEvent event) {
        String name = teacherName.getText();
        String surname = teacherSurname.getText();

        if(name.isEmpty() || surname.isEmpty()) {
            messageFalse.setText("Please enter username and password");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            userDAO.insertTeacherToDb(name, surname);
            messageTrue.setText("Teacher " + name + " " + surname + " added.");
            messageFalse.setVisible(false);
            messageTrue.setVisible(true);
        }
    }
}
