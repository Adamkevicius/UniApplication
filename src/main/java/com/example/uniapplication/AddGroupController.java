package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddGroupController {
    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;
    @FXML
    private TextField enterGroupText;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void enterGroupOnAction(ActionEvent event) {
        String groupName = enterGroupText.getText();

        if(groupName.isEmpty()) {
            messageFalse.setText("Please enter group name");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            userDAO.insertGroupToDb(groupName);
            messageTrue.setText("Group " +  groupName + " added");
            messageFalse.setVisible(false);
            messageTrue.setVisible(true);
        }
    }


}
