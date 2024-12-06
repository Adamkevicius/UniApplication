package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteGroupController {
    @FXML
    private TextField deleteGroupText;

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
    void deleteGroupButtonOnAction(ActionEvent event) {
        String group = this.deleteGroupText.getText();

        if (group.isEmpty()) {
            messageFalse.setText("Enter subject name");
        } else {
            userDAO.deleteGroup(group);
            messageTrue.setText("Group  '" + group + "' deleted successfully");
        }
    }
}
