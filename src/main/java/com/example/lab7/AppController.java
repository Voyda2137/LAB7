package com.example.lab7;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {
    DatabaseController db = new DatabaseController();
    @CrossOrigin
    @GetMapping("/getAllNotes")
    private List<DatabaseController.Note> getNotes(){
        return db.getAllNotes();
    }
    @CrossOrigin
    @PostMapping("/createNote")
    @ResponseStatus(HttpStatus.OK)
    public String createNote(@RequestParam("importance") String importance,
                             @RequestParam("text") String text) {

        db.insertNote(importance, text);
        return "Successfully added the note";
    }
}
