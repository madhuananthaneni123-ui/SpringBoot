package org.example.Controller;

import org.example.Entity.student;
import org.example.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService=studentService;
    }
    @PostMapping("/create")
    public ResponseEntity<student> create(@RequestBody student s1) {
        student stureq=studentService.savestudent(s1);
        return ResponseEntity.ok(stureq);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<student> get(@PathVariable("id") String id) {
        student rq=studentService.getstudent(id);
        if(rq==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(rq);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<student>> get() {
        List<student> rq=studentService.getall();
        return ResponseEntity.ok(rq);
    }
}
