package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSubjectGroupController {
    @FXML
    private Button addSubjectGroupButton;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField groupName;

    @FXML
    private TextField subjectName;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void addSubjectGroupOnAction(ActionEvent event) {
        String groupName = this.groupName.getText();
        String subjectName = this.subjectName.getText();

        if(groupName.isEmpty() || subjectName.isEmpty()) {
            messageFalse.setText("Please fill all the fields");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            userDAO.insertSubjectToGroup(subjectName, groupName);
            messageTrue.setText("Subject " + subjectName + " added" + " to group " + groupName);
            messageFalse.setVisible(false);
            messageFalse.setVisible(true);
        }
    }
}
