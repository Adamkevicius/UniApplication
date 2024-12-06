package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import com.example.uniapplication.handlers.AddScene;
import com.example.uniapplication.handlers.SwitchInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    @FXML
    private Button changeGradeButton;

    @FXML
    private Button deleteGradeButton;

    @FXML
    private Button giveStudentGradeButton;
    @FXML
    private Text teacherName;

    @FXML
    private ListView<String> teacherSubjectView;

    private ObservableList<String> teacherSubjects;

    private UserDAO userDAO;

    private String teacherSurname;

    private String currentSubject;

    private SwitchInterface switchScene;

    public void setTeacherSurname(String surname) {
        this.teacherSurname = surname;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchScene = new AddScene(600, 400);
        userDAO = new DatabaseEnquiries();
        teacherSubjects = FXCollections.observableArrayList();
        userDAO.getAllTeacherSubjectsFromDb(teacherSurname, teacherSubjects);
        teacherSubjectView.setItems(teacherSubjects);
    }

    @FXML
    void giveStudentGradeOnAction(ActionEvent event) throws IOException {
        switchScene.switchScene(giveStudentGradeButton, "Student grade", new FXMLLoader(getClass().getResource("layout/give-grade-page.fxml")));

    }

    @FXML
    void changeGradeOnAction(ActionEvent event) throws IOException {
        switchScene.switchScene(changeGradeButton, "Student grade update", new FXMLLoader(getClass().getResource("layout/change-grade-page.fxml")));

    }

    @FXML
    void deleteGradeOnAction(ActionEvent event) throws IOException {
        switchScene.switchScene(changeGradeButton, "Student grade update", new FXMLLoader(getClass().getResource("layout/delete-grade-page.fxml")));

    }

}
