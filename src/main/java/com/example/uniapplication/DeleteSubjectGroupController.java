package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteSubjectGroupController {
    @FXML
    private Button deleteSubjectGroup;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField subjectNameText;

    private UserDAO userDAO;

    @FXML
    void initialize() {
        userDAO = new DatabaseEnquiries();

    }

    @FXML
    void deleteSubjectGroupOnAction(ActionEvent event) {
        String subjectName = this.subjectNameText.getText();

        if (subjectName.isEmpty()) {
            messageFalse.setText("Please enter Subject name and Group name");
        } else {
            userDAO.deleteSubjectFromGroup(subjectName);
            messageTrue.setText("Subject deleted successfully");
        }
    }
}
