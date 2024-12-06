package com.example.uniapplication.Controller;

import com.example.uniapplication.teacherclasses.Delete;
import com.example.uniapplication.teacherclasses.DeleteGrade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteGradeController {
    @FXML
    private Button deleteGrade;

    @FXML
    private TextField gradeText;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentSurname;

    @FXML
    private TextField subject;

    @FXML
    private Label messageTrue;

    @FXML
    private Label messageFalse;

    @FXML
    void deleteGradeOnAction(ActionEvent event) {
        String grade = gradeText.getText().trim();
        String name = studentName.getText().trim();
        String surname = studentSurname.getText().trim();
        String subjectName = subject.getText().trim();

        if(grade.isEmpty() || name.isEmpty() || surname.isEmpty() || subjectName.isEmpty()) {
            messageFalse.setText("Please fill all the fields");
        } else {
            Delete deleteGrade = new DeleteGrade(grade, name, surname, subjectName);
            deleteGrade.delete();
            messageTrue.setText("Deleted Successfully");
            messageTrue.setVisible(true);
        }
    }
}
