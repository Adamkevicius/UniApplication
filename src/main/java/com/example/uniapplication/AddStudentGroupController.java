package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddStudentGroupController {
    @FXML
    private Button enterToGroupButton;

    @FXML
    private TextField groupNameText;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField studentNameText;

    @FXML
    private TextField studentSurnameText;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void enterToGroupOnAction(ActionEvent event) {
        String groupName = groupNameText.getText();
        String studentName = studentNameText.getText();
        String studentSurname = studentSurnameText.getText();

        if(groupName.isEmpty() || studentName.isEmpty() || studentSurname.isEmpty()) {
            messageFalse.setText("Please fill all the fields");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            userDAO.insertStudentToGroup(studentName, studentSurname, groupName);
            messageTrue.setText("Student " + studentName + " " + studentSurname + " added to " + groupName);
            messageFalse.setVisible(false);
            messageTrue.setVisible(true);
        }
    }
}
