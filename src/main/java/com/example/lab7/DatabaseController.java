package com.example.lab7;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class DatabaseController {
    private static final String url = "jdbc:mysql://localhost:3306/students";
    private static final String username = "root";
    private static final String password = "root";
    public void insertStudent(String name, String surname, Integer age, Integer index_number) {
        String sql = "INSERT INTO student (name, surname, age, index_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setInt(3, age);
            stmt.setInt(4, index_number);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new note was inserted successfully.");
            } else {
                System.out.println("Failed to insert the note.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public class Student {
        private int id;
        private String name;
        private String surname;
        private Integer age;
        private Integer index_number;

        public Student() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
        public Integer getIndex_number(){
            return index_number;
        }
        public void setIndex_number(Integer index_number) {
            this.index_number = index_number;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age='" + age + '\'' +
                    ", index_number='" + index_number + '\'' +
                    '}';
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT student_id, name, surname FROM student";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setSurname(surname);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
    public Student getSingleStudent(Integer studentId) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        Student student = null;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("student_id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    Integer age = rs.getInt("age");
                    Integer index_number = rs.getInt("index_number");
                    student = new Student();
                    student.setId(id);
                    student.setName(name);
                    student.setSurname(surname);
                    student.setAge(age);
                    student.setIndex_number(index_number);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}

