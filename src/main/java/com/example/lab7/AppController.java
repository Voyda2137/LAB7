package com.example.lab7;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AppController {
    DatabaseController db = new DatabaseController();
    @CrossOrigin
    @GetMapping("/students")
    private List<DatabaseController.Student> getStudents(){
        return db.getAllStudents();
    }
    @CrossOrigin
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public String createStudent(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("age") Integer age,
                                @RequestParam("index_number") Integer index_number ) {

        db.insertStudent(name, surname, age, index_number);
        return "Successfully added the student";
    }
    @CrossOrigin
    @GetMapping("/students/id")
    private ResponseEntity<Object> getSingleStudent(@RequestParam("id") Integer id){
        DatabaseController.Student student = db.getSingleStudent(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        } else {
            return ResponseEntity.ok(student);
        }
    }
}
