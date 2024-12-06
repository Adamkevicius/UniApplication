package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteStudentGroupController {
    @FXML
    private Button deleteStudentGroup;

    @FXML
    private TextField groupName;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField studentNameText;

    @FXML
    private TextField studentSurnameText;

    private UserDAO userDAO;

    @FXML
    void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void deleteStudentGroupOnAction(ActionEvent event) {
        String studentName = this.studentNameText.getText();
        String studentSurname = this.studentSurnameText.getText();
        String groupName = this.groupName.getText();

        if(studentName.isEmpty() || studentSurname.isEmpty() || groupName.isEmpty()) {
            messageFalse.setText("Please fill all the fields");
        } else {
            userDAO.deleteStudentFromGroup(studentName, studentSurname, groupName);
            messageTrue.setText("Student deleted successfully");
        }
    }

}
