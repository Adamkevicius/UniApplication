package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddStudentController {
    @FXML
    private Button addStudentButton;


    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentSurname;

    private DatabaseEnquiries dbEnquiries;

    public void initialize() {
        dbEnquiries = new DatabaseEnquiries();
    }

    @FXML
    void createStudentOnAction(ActionEvent event) {
        String name = studentName.getText();
        String surname = studentSurname.getText();

        if(name.isEmpty() || surname.isEmpty()) {
            messageFalse.setText("Please enter username and password");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            dbEnquiries.insertStudentToDb(name, surname);
            messageTrue.setText("Student " + name + " " + surname + " added successfully");
            messageFalse.setVisible(false);
            messageTrue.setVisible(true);
        }
    }
}
