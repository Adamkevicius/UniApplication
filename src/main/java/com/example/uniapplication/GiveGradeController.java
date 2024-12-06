package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GiveGradeController implements Initializable {

    @FXML
    private Button giveGradeButton;

    @FXML
    private TextField gradeNameText;

    @FXML
    private Label messageFalse;

    @FXML
    private Label messageTrue;

    @FXML
    private TextField studentNameText;

    @FXML
    private TextField studentSurnameText;

    @FXML
    private TextField subjectText;

    private String teacher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDAO userDAO = new DatabaseEnquiries();

    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @FXML
    void giveGradeOnAction(ActionEvent event) {
        String gradeName = gradeNameText.getText().trim();
        String studentName = studentNameText.getText().trim();
        String studentSurname = studentSurnameText.getText().trim();
        String subjectName = subjectText.getText().trim();

        //UserDAO userDAO = new DatabaseEnquiries();


        if (gradeName.isEmpty() || studentName.isEmpty() || studentSurname.isEmpty() || subjectName.isEmpty()) {
            messageFalse.setText("Please fill all the fields");
            messageFalse.setVisible(true);
        } else {
            messageFalse.setText("You are not able to give grade to this subject");
            messageFalse.setVisible(true);
        }


    }
}

