package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteSubjectController {
    @FXML
    private Button deleteGroupButton;

    @FXML
    private TextField deleteGroupText;

    @FXML
    private Button deleteSubjectButton;

    @FXML
    private TextField deleteSubjectText;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    private UserDAO userDAO;

    @FXML
    void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void deleteSubjectButtonOnAction(ActionEvent event) {
        String subjectName = this.deleteSubjectText.getText();

        if(subjectName.isEmpty()) {
            messageFalse.setText("Please enter a subject name");
        } else {
            userDAO.deleteSubjectFromDb(subjectName);
            messageTrue.setText("Subject '" + subjectName + "' deleted successfully");
        }
    }


}
