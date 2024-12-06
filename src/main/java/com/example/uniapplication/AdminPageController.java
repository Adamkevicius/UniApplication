package com.example.uniapplication.Controller;

import com.example.uniapplication.handlers.AddScene;
import com.example.uniapplication.handlers.SwitchInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminPageController {
        @FXML
        private Button addGroupButton;

        @FXML
        private Button addStudentButton;

        @FXML
        private Button addStudentToGroup;

        @FXML
        private Button addSubjectButton;

        @FXML
        private Button addSubjectToGroup;

        @FXML
        private Button addTeacherButton;

        @FXML
        private Text adminMessage;

        @FXML
        private Button deleteGroupButton;

        @FXML
        private Button deleteStudentButton;

        @FXML
        private Button deleteSubjectButton;

        @FXML
        private Button deleteStudentFromGroup;

        @FXML
        private Button deleteSubjectFromGroup;

        @FXML
        private Button deleteTeacherButton;

        private SwitchInterface switchScene;

        public void initialize() {
                switchScene = new AddScene(600, 400);
        }

        @FXML
        void addGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addGroupButton, "Add Group", new FXMLLoader(getClass().getResource("layout/add-group-page.fxml")));
        }

        @FXML
        void addStudentOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addStudentButton, "Add Student", new FXMLLoader(getClass().getResource("layout/add-student-page.fxml")));
        }

        @FXML
        void addSubjectOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addSubjectButton, "Add Subject", new FXMLLoader(getClass().getResource("layout/add-subject-page.fxml")));
        }

        @FXML
        void addTeacherOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addTeacherButton, "Add Teacher", new FXMLLoader(getClass().getResource("layout/add-teacher-page.fxml")));
        }

        @FXML
        void addStudentToGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addStudentToGroup, "Add Student to group", new FXMLLoader(getClass().getResource("layout/add-student-to-group-page.fxml")));
        }

        @FXML
        void addSubjectToGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(addSubjectToGroup, "Add Subject to group", new FXMLLoader(getClass().getResource("layout/add-subject-to-group-page.fxml")));

        }

        @FXML
        void deleteGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteGroupButton, "Delete group", new FXMLLoader(getClass().getResource("layout/delete-group-page.fxml")));

        }

        @FXML
        void deleteStudentOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteStudentButton, "Delete student", new FXMLLoader(getClass().getResource("layout/delete-student-page.fxml")));

        }

        @FXML
        void deleteSubjectOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteSubjectButton, "Delete subject", new FXMLLoader(getClass().getResource("layout/delete-subject-page.fxml")));

        }

        @FXML
        void deleteTeacherOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteTeacherButton, "Delete teacher", new FXMLLoader(getClass().getResource("layout/delete-teacher-page.fxml")));

        }

        @FXML
        void deleteStudentFromGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteStudentFromGroup, "Delete student from group", new FXMLLoader(getClass().getResource("layout/delete-student-from-group.fxml")));

        }

        @FXML
        void deleteSubjectFromGroupOnAction(ActionEvent event) throws IOException {
                switchScene.switchScene(deleteSubjectFromGroup, "Delete subject from group", new FXMLLoader(getClass().getResource("layout/delete-subject-from-group.fxml")));

        }
}





