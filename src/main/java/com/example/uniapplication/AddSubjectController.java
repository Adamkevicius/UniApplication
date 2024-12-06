package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//TODO
public class AddSubjectController {
    @FXML
    private Button enterSubjectButton;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField subjectNameText;

    @FXML
    private TextField teacherNameText;

    @FXML
    private TextField teacherSurnameText;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void enterSubjectOnAction(ActionEvent event) {
        String subjectName = subjectNameText.getText();
        String teacherName = teacherNameText.getText();
        String teacherSurname = teacherSurnameText.getText();

        if(subjectName.isEmpty() || teacherName.isEmpty() || teacherSurname.isEmpty()) {
            messageFalse.setText("Please enter subject name, teacher name and surname");
            messageTrue.setVisible(false);
            messageFalse.setVisible(true);
        } else {
            userDAO.insertSubjectToDb(subjectName, teacherSurname);
            messageTrue.setText("Subject " + subjectName + " successfully added");
            messageFalse.setVisible(false);
            messageTrue.setVisible(true);
        }
    }
}
