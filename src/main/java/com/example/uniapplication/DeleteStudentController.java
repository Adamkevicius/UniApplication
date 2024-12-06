package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteStudentController {
    @FXML
    private Button deleteStudentButton;

    @FXML
    private TextField deleteStudentNameText;

    @FXML
    private TextField deleteStudentSurnameText;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void deleteStudentButtonOnAction(ActionEvent event) {
        String name = deleteStudentNameText.getText();
        String surname = deleteStudentSurnameText.getText();

        if (name.isEmpty() || surname.isEmpty()) {
            messageFalse.setText("Please enter all fields");
        } else {
            userDAO.deleteStudentFromDb(name, surname);
        }
    }
}
