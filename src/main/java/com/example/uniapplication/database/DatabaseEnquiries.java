package com.example.uniapplication.database;

import com.example.uniapplication.StudentInformation;
import com.example.uniapplication.users.Admin;
import com.example.uniapplication.users.Student;
import com.example.uniapplication.users.Teacher;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseEnquiries implements UserDAO {

    protected final String SELECT_ADMIN_BY_DATA = "SELECT * FROM admin WHERE username = ? AND password = ? AND roleID = ?";
    protected final String SELECT_TEACHER_BY_DATA = "SELECT * FROM teacher WHERE username = ? AND password = ? AND roleID = ?";
    protected final String SELECT_STUDENT_BY_DATA = "SELECT * FROM student WHERE username = ? AND password = ? AND roleID = ?";
    protected final String SELECT_ROLE_BY_DATA = "SELECT roleID AS roleId FROM admin WHERE username = ? AND password = ? " +
            "UNION SELECT roleID AS roleId FROM student WHERE username = ? AND password = ? " +
            "UNION SELECT roleID AS roleId FROM teacher WHERE username = ? AND password = ?";
    protected final String SELECT_ALL_TEACHER_SUBJECTS = "SELECT subject.name from subject where teacherID = ?";
    protected final String SELECT_GRADE_ID = "SELECT gradeID FROM grade WHERE grade = ?";
    protected final String SELECT_SUBJECT_NAME_BY_TEACHER_ID = "SELECT subject.name from subject where teacherID = ?";
    protected final String SELECT_TEACHER_WITH_SUBJECT = "SELECT COUNT(*) FROM teacher " +
            "JOIN subject ON teacher.teacherID = subject.teacherID " +
            "WHERE teacher.name = ? AND teacher.surname = ? AND subject.name = ?";
    protected final String SELECT_ALL_STUDENT_INFO = "SELECT student.name AS student_name, student.surname AS student_surname, `groups`.name AS group_name, subject.name AS subject_name " +
            "FROM student JOIN student_group ON student.studentID = student_group.studentID " +
            "JOIN `groups` ON student_group.groupsID = `groups`.groupID " +
            "JOIN subject_group ON `groups`.groupID = subject_group.groupsID " +
            "JOIN subject ON subject_group.subjectID = subject.subjectID " +
            "LEFT JOIN student_grades ON student.studentID = student_grades.studentID AND subject.subjectID = student_grades.subjectID " +
            "LEFT JOIN grade gr ON student_grades.gradeID = gr.gradeID " +
            "WHERE student.name = ? AND student.surname = ? AND `groups`.name = ?";
    protected final String SELECT_GROUP_NAME_BY_STUDENT = "SELECT `groups`.name FROM `groups` WHERE `groups`.groupID IN (SELECT groupsID FROM student_group WHERE studentID = ?)";
    protected final String INSERT_STUDENT = "INSERT INTO student (username, password, name, surname, roleID) VALUES (?, ?, ?, ?, ?)";
    protected final String INSERT_SUBJECT = "INSERT INTO subject (name, teacherID) VALUES (?, ?)";
    protected final String INSERT_TEACHER  = "INSERT INTO teacher (username, password, name, surname, roleID) VALUES (?, ?, ?, ?, ?)";
    protected final String SELECT_TEACHER_ID_BY_SURNAME = "SELECT teacherID FROM teacher WHERE surname = ?";
    protected final String INSERT_GROUP = "INSERT INTO `groups` (name) VALUES (?)";
    protected final String INSERT_STUDENT_GRADE = "INSERT INTO student_grades (gradeID, studentID, subjectID) VALUES (?, ?, ?)";
    protected final String DELETE_GROUP_BY_NAME = "DELETE FROM `groups` WHERE name = ?";
    protected final String DELETE_STUDENT_BY_NAME_AND_SURNAME = "DELETE FROM student WHERE name = ? and surname = ?";
    protected final String DELETE_TEACHER_BY_NAME_ADN_SURNAME = "DELETE FROM `teacher` WHERE name = ? and surname = ?";
    protected final String DELETE_TEACHER_FROM_SUBJECT = "DELETE FROM `subject` WHERE teacherID = ?";
    protected final String SELECT_STUDENT_ID_BY_NAME_AND_SURNAME = "SELECT studentID FROM student WHERE name = ? AND surname = ?";
    protected final String SELECT_GROUP_ID_BY_NAME = "SELECT groupID FROM `groups` WHERE name = ?";
    protected final String INSERT_STUDENT_TO_GROUP = "INSERT INTO student_group (groupsID, studentID) VALUES (?, ?)";
    protected final String SELECT_SUBJECT_ID_BY_NAME = "SELECT subjectID FROM `subject` WHERE name = ?";
    protected final String INSERT_SUBJECT_TO_GROUP = "INSERT INTO subject_group (subjectID, groupsID) VALUES (?, ?)";
    protected final String DELETE_STUDENT_FROM_GROUP = "DELETE FROM student_group WHERE studentID = ?";
    protected final String DELETE_SUBJECT_FROM_GROUP = "DELETE FROM subject_group WHERE subjectID = ?";
    protected final String DELETE_SUBJECT_FROM_DB = "DELETE FROM `subject` WHERE name = ?";
    protected final String DELETE_GROUP_BY_ID_FROM_STUDENT_GROUPS = "DELETE FROM student_group WHERE groupsID = ?";
    protected final String DELETE_GROUP_BY_ID_FROM_SUBJECT_GROUPS = "DELETE FROM subject_group WHERE groupsID = ?";
    protected final String DELETE_STUDENT_GRADE = "DELETE FROM student_grades WHERE studentID = ? AND gradeID = ? AND subjectID = ?";
    protected final String UPDATE_STUDENT_GRADE = "UPDATE student_grades SET gradeID = ? WHERE studentID = ?";

    @Override
    public Admin getAdminFromDb(String username, String password) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (SELECT_ADMIN_BY_DATA)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, 1);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admin(resultSet.getString("username"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting admin from DB" + e);
        }
        return null;    }

    @Override
    public Teacher getTeacherFromDb(String username, String password) {
        try(Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_TEACHER_BY_DATA)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, 2);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Teacher(resultSet.getString("username"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting teacher from DB " +  e);
        }
        return null;
    }

    @Override
    public Student getStudentFromDb(String username, String password) {
        try(Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_DATA)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, 3);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(resultSet.getString("username"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting teacher from DB " +  e);
        }
        return null;
    }

    @Override
    public void getAllTeacherSubjectsFromDb(String teacherName, ObservableList<String> subjects) {
        try(Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TEACHER_SUBJECTS)) {

            int teacherId = getTeacherIdFromDb(teacherName);
            statement.setInt(1, teacherId);

            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subjects.add(resultSet.getString("name"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting subjects " +  e);
        }
    }

    //TODO
    @Override
    public void getAllSubjectsFromDb(ObservableList<String> subjects, String username, String password) {
        int studentId = getStudentIdFromDb(username, password);
        try(Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT s.name AS subject_name FROM student st JOIN student_group sg ON st.studentID = sg.studentID JOIN subject_group subj_grp ON sg.groupsID = subj_grp.groupsID JOIN subject s ON subj_grp.subjectID = s.subjectID WHERE st.studentID = ?;")) {
            statement.setInt(1, studentId);

            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subjects.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting subjects " +  e);
        }
    }

    @Override
    public void getAllStudentInfoFromDb(ObservableList<String> studentSubjects,
                                        ObservableList<String> studentGrades, String studentName, String groupName) {
        String query = "SELECT s.name AS student_name, s.surname AS student_surname, g.name AS group_name, " +
                "sub.name AS subject_name, COALESCE(gr.grade, 'No Grade') AS grade " +
                "FROM student s " +
                "JOIN student_group sg ON s.studentID = sg.studentID " +
                "JOIN `groups` g ON sg.groupsID = g.groupID " +
                "JOIN subject_group subj_g ON g.groupID = subj_g.groupsID " +
                "JOIN subject sub ON subj_g.subjectID = sub.subjectID " +
                "LEFT JOIN student_grades sgd ON s.studentID = sgd.studentID AND sub.subjectID = sgd.subjectID " +
                "LEFT JOIN grade gr ON sgd.gradeID = gr.gradeID " +
                "WHERE s.name = ? AND g.name = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters for student name and group name
            statement.setString(1, studentName);
            statement.setString(2, groupName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String subjectName = resultSet.getString("subject_name");
                    String grade = resultSet.getString("grade");

                    studentSubjects.add(subjectName);  // Add subject to subjects list
                    studentGrades.add(grade);          // Add grade to grades list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting student subjects and grades from DB: " + e.getMessage(), e);
        }
    }
}

@Override
public int getRoleFromDb(String username, String password) {
    int roleId = -1;
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ROLE_BY_DATA)) {
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, username);
        statement.setString(4, password);
        statement.setString(5, username);
        statement.setString(6, password);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                roleId = resultSet.getInt("roleID");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while getting role from DB " +  e);
    }
    return roleId;
}

@Override
public int getTeacherIdFromDb(String surname) {
    int teacherId = -1;
    try (Connection connection = new DatabaseConnection().getConnection();
         PreparedStatement statement = connection.prepareStatement(SELECT_TEACHER_ID_BY_SURNAME)) {

        statement.setString(1, surname);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                teacherId = resultSet.getInt("teacherID");
            } else {
                System.out.println("No teacher found with surname: " + surname);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving teacher ID", e);
    }
    return teacherId;
}

@Override
public int getStudentIdFromDb(String name, String surname) {
    int studentId = -1;

    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_ID_BY_NAME_AND_SURNAME)) {
        statement.setString(1, name);
        statement.setString(2, surname);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                studentId = resultSet.getInt("studentID");
            } else {
                System.out.println("No student Id found with name: " + name + " and surname " + surname);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving student Id " + e);
    }
    return studentId;
}

@Override
public int getGroupIdFromDb(String groupName) {
    int groupId = -1;

    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_GROUP_ID_BY_NAME)) {
        statement.setString(1, groupName);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                groupId = resultSet.getInt("groupID");
            } else {
                System.out.println("No group Id found with name: " + groupName);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving group Id " + e);
    }

    return groupId;
}

@Override
public String getGroupNameFromDb(int groupId) {
    return "";
}

@Override
public int getSubjectIdFromDb(String subjectName) {
    int subjectId = -1;

    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECT_ID_BY_NAME)) {
        statement.setString(1, subjectName);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                subjectId = resultSet.getInt("subjectID");
            } else {
                System.out.println("No subject Id found with name: " + subjectName);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving subject Id " + e);
    }
    return subjectId;
}

@Override
public int getGradeFromDb(String grade) {
    int gradeId = -1;

    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_GRADE_ID)) {
        statement.setString(1, grade);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                gradeId = resultSet.getInt("gradeID");
            } else {
                System.out.println("No grade Id found with name: " + grade);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving grade Id " + e);
    }

    return gradeId;
}

@Override
public String getSubjectNameByTeacherId(String teacherSurname) {
    int teacherId;
    String subjectName = "";

    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECT_NAME_BY_TEACHER_ID)) {
        teacherId = getTeacherIdFromDb(teacherSurname);

        statement.setInt(1, teacherId);
        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                subjectName = resultSet.getString("name");
                return subjectName;
            } else {
                System.out.println("No subject Name found with name: " + teacherSurname);
            }
        }


    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving grade Id " + e);
    }

    return subjectName;
}

@Override
public boolean getTeacherWithSubjectFromDb(String teacherName, String teacherSurname, String subjectName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_TEACHER_WITH_SUBJECT)) {
        statement.setString(1, teacherName);
        statement.setString(2, teacherSurname);
        statement.setString(3, subjectName);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error while retrieving teacher with subject " + e);
    }
    return false;
}

@Override
public void insertStudentToDb(String name, String surname) {
    try (Connection connection = new DatabaseConnection().getConnection();
         PreparedStatement statement = connection.
                 prepareStatement(INSERT_STUDENT)) {
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, name);
        statement.setString(4, surname);
        statement.setInt(5, 3);
        statement.executeUpdate();

            /*MessageInterface message = new CreateMessage();
            message.message(textMessage, "Student added");*/
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

@Override
public void insertSubjectToDb(String subjectName, String teacherSurname) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT)) {
        int teacherId = getTeacherIdFromDb(teacherSurname);

        statement.setString(1, subjectName);
        statement.setInt(2, teacherId);
        statement.executeUpdate();

            /*MessageInterface message = new CreateMessage();
            message.message(messageText, "Subject added");*/
    } catch (SQLException e) {
        throw new RuntimeException("Error while creating subject" + e);
    }
}

@Override
public void insertTeacherToDb(String name, String surname) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER)) {
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, name);
        statement.setString(4, surname);
        statement.setInt(5, 2);
        statement.executeUpdate();

           /* MessageInterface message = new CreateMessage();
            message.message(messageText, "Teacher added");*/
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

@Override
public void insertGroupToDb(String name) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_GROUP)) {
        statement.setString(1, name);
        statement.executeUpdate();

        //Create message
    } catch (SQLException e) {
        throw new RuntimeException("Error while creating group " + e);
    }
}

@Override
public void insertStudentToGroup(String studentName, String studentSurname, String groupName) {
    try (Connection connection = new DatabaseConnection().getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT_TO_GROUP)) {
        int studentId = getStudentIdFromDb(studentName, studentSurname);
        int groupId = getGroupIdFromDb(groupName);

        statement.setInt(1, groupId);
        statement.setInt(2, studentId);
        statement.executeUpdate();

        //TODO message
    } catch (SQLException e) {
        throw new RuntimeException("Error while inserting student to group " + e);
    }
}

@Override
public void insertSubjectToGroup(String subjectName, String groupName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT_TO_GROUP)) {
        int subjectId = getSubjectIdFromDb(subjectName);
        int groupId = getGroupIdFromDb(groupName);

        statement.setInt(1, subjectId);
        statement.setInt(2, groupId);
        statement.executeUpdate();

        //TODO message
    } catch (SQLException e) {
        throw new RuntimeException("Error while inserting subject to group " + e);
    }
}

@Override
public void insertStudentGradeToDb(String grade, String studentName, String studentSurname, String subject) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT_GRADE)) {
        int gradeId = getGradeFromDb(grade);
        int studentId = getStudentIdFromDb(studentName, studentSurname);
        int subjectId = getSubjectIdFromDb(subject);

        statement.setInt(1, gradeId);
        statement.setInt(2, studentId);
        statement.setInt(3, subjectId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while inserting student grade " + e);
    }
}

@Override
public void deleteGroupFromDb(String name) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_BY_NAME)) {

        deleteGroupFromStudentGroups(name);
        deleteGroupFromSubjectGroups(name);
        statement.setString(1, name);
        statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting group " + e);
    }
}

@Override
public void deleteStudentFromDb(String name, String surname) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.
                prepareStatement(DELETE_STUDENT_BY_NAME_AND_SURNAME)){
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.executeUpdate();

        //TODO Message
    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting student " + e);
    }
}

@Override
public void deleteTeacherFromDb(String name, String surname) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_FROM_SUBJECT);
        PreparedStatement statement1 = connection.prepareStatement(DELETE_TEACHER_BY_NAME_ADN_SURNAME)) {
        int teacherID = getTeacherIdFromDb(surname);

        if(teacherID <= 0) {
            throw new RuntimeException("Teacher Id not found: " + teacherID);
            //TODO Message
        }

        statement.setInt(1, teacherID);
        statement.executeUpdate();

        statement1.setString(1, name);
        statement1.setString(2, surname);
        statement1.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting teacher " + e);
    }
}

@Override
public void deleteSubjectFromDb(String subjectName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_FROM_DB)) {
        deleteSubjectFromGroup(subjectName);
        statement.setString(1, subjectName);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting subject " + e);
    }
}

@Override
public void deleteStudentFromGroup(String name, String surname, String groupName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_FROM_GROUP)) {
        int studentId = getStudentIdFromDb(name, surname);

        statement.setInt(1, studentId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting student from group " + e);
    }
}

@Override
public void deleteSubjectFromGroup(String subjectName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_FROM_GROUP)) {
        int subjectId = getSubjectIdFromDb(subjectName);

        statement.setInt(1, subjectId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting subject from group " + e);
    }
}

@Override
public void deleteGroupFromStudentGroups(String groupName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_BY_ID_FROM_STUDENT_GROUPS)) {
        int groupId = getGroupIdFromDb(groupName);

        statement.setInt(1, groupId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting group from student groups " + e);
    }
}

@Override
public void deleteGroupFromSubjectGroups(String groupName) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_BY_ID_FROM_SUBJECT_GROUPS)) {
        int groupId = getGroupIdFromDb(groupName);

        statement.setInt(1, groupId);
        statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting group from subject groups " + e);
    }
}

@Override
public void deleteGradeFromDb(String grade, String studentName, String studentSurname, String subject) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_GRADE)) {

        int gradeId = getGradeFromDb(grade);
        int studentId = getStudentIdFromDb(studentName, studentSurname);
        int subjectId = getSubjectIdFromDb(subject);
        statement.setInt(1, studentId);
        statement.setInt(2, gradeId);
        statement.setInt(3, subjectId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while deleting student grade " + e);
    }
}

@Override
public void updateGrade(String grade, String studentName, String studentSurname) {
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_GRADE)) {
        int gradeId = getGradeFromDb(grade);
        int studentId = getStudentIdFromDb(studentName, studentSurname);

        statement.setInt(1, gradeId);
        statement.setInt(2, studentId);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error while updating grade " + e);
    }
}

@Override
public boolean validation(String subjectName, String teacherSurname) {
    int subjectId = getSubjectIdFromDb(subjectName);
    try(Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT teacher.teacherID from teacher where teacherID = ? " +
                "UNION SELECT subject.subjectID from subject where subjectID = ?;")) {
        int teacherID = getTeacherIdFromDb(teacherSurname);
        statement.setInt(1, teacherID);
        statement.setInt(2, subjectId);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("SQLException: " + e.getMessage());
    }
    return false;
}

}
