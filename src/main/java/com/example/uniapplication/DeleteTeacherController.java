package com.example.uniapplication.Controller;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

//TODO ADD DESIGN
public class DeleteTeacherController {

    @FXML
    private Button deleteTeacherButton;

    @FXML
    private TextField deleteTeacherName;

    @FXML
    private TextField deleteTeacherSurname;

    private UserDAO userDAO;

    public void initialize() {
        userDAO = new DatabaseEnquiries();
    }

    @FXML
    void deleteTeacherButtonOnAction(ActionEvent event) {
        String name = deleteTeacherName.getText();
        String surname = deleteTeacherSurname.getText();

        if (name.isEmpty() || surname.isEmpty()) {
            System.out.println("Name or Surname cannot be empty");
            //TODO message
        } else {
            userDAO.deleteTeacherFromDb(name, surname);
        }
    }
}
