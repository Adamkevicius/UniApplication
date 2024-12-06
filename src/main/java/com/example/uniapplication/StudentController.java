package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private Label groupName;

    @FXML
    private ListView<String> subjectsListView;

    @FXML
    private Text studentNameText;

    private final String studentName;

    private final String studentSurname;

    private final ObservableList<String> studentSubjects;

    private final UserDAO userDAO;

    public StudentController(String studentName, String studentSurname) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        studentSubjects = FXCollections.observableArrayList();
        userDAO = new DatabaseEnquiries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupName.setText(userDAO.getGroupNameFromDb(3));
        try {
            userDAO.getAllStudentInfoFromDb(studentSubjects, "Matvej", "Adamkevičius", "PI23B");
            subjectsListView.setItems(studentSubjects);
        } catch (Exception e) {
            System.out.println("Failed to initialize student data: " + e.getMessage());
        }

    }
}