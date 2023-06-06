package com.example.lab7;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class DatabaseController {
    private static final String url = "jdbc:mysql://localhost:3306/lab4";
    private static final String username = "root";
    private static final String password = "root";
    public void insertNote(String importance, String text) {
        String sql = "INSERT INTO notes (importance, text, timestamp) VALUES (?, ?, ?)";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, importance);
            stmt.setString(2, text);
            stmt.setTimestamp(3, timestamp);

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
    public class Note {
        private int id;
        private String importance;
        private String text;
        private String timestamp;

        public Note() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImportance() {
            return importance;
        }

        public void setImportance(String importance) {
            this.importance = importance;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "id=" + id +
                    ", importance='" + importance + '\'' +
                    ", text='" + text + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    '}';
        }
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        String sql = "SELECT id, importance, text, timestamp FROM notes";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String importance = rs.getString("importance");
                String text = rs.getString("text");
                String timestamp = rs.getString("timestamp");

                Note note = new Note();
                note.setId(id);
                note.setText(text);
                note.setImportance(importance);
                note.setTimestamp(timestamp);
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

}

