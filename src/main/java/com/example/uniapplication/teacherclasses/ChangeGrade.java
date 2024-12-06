package com.example.uniapplication.teacherclasses;

import com.example.uniapplication.database.DatabaseEnquiries;
import com.example.uniapplication.database.UserDAO;

public class ChangeGrade extends Grade implements Change {
    private final UserDAO userDAO;

    public ChangeGrade(String grade, String studentName, String studentSurname, String subject) {
        super(grade, studentName, studentSurname, subject);
        userDAO = new DatabaseEnquiries();
    }

    @Override
    public void change() {
        userDAO.updateGrade(grade, studentName, studentSurname);
    }
}
