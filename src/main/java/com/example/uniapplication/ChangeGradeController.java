package com.example.uniapplication.Controller;

import com.example.uniapplication.teacherclasses.Change;
import com.example.uniapplication.teacherclasses.ChangeGrade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeGradeController implements Initializable {
    @FXML
    private Button updateGradeButton;

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


    private boolean isTeacher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setIsTeacher(boolean isTeacher) { this.isTeacher = isTeacher; }


    @FXML
    void updateGradeOnAction(ActionEvent event) {
        String gradeName = gradeNameText.getText().trim();
        String studentName = studentNameText.getText().trim();
        String studentSurname = studentSurnameText.getText().trim();
        String subjectName = subjectText.getText().trim();

        if(gradeName.isEmpty() || studentName.isEmpty() || studentSurname.isEmpty() || subjectName.isEmpty()){
            messageFalse.setText("Please fill all the fields");
            messageFalse.setVisible(true);
        } else if (isTeacher) {
            Change changeGrade = new ChangeGrade(studentName, studentSurname, subjectName, gradeName);
            changeGrade.change();
            messageTrue.setText("Grade updated");
            messageTrue.setVisible(true);
        } else {
            messageFalse.setText("You are not able to update the grade");
            messageFalse.setVisible(true);
        }
    }

}
