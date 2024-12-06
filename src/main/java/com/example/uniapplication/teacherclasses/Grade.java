package com.example.uniapplication;

public class Grade {
    String grade;
    String studentName;
    String studentSurname;
    String subject;

    public Grade(String grade, String studentName, String studentSurname, String subject) {
        this.grade = grade;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
