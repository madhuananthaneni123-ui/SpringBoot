package org.example.Service;

import org.example.Entity.student;
import org.example.Repositry.StudentRepositry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepositry studentRepositry;
     public StudentService(StudentRepositry studentRepositry) {
         this.studentRepositry=studentRepositry;
     }
     public student savestudent(student st) {
         student req=studentRepositry.save(st);
         return req;
     }
     public student getstudent(String id) {
         return studentRepositry.find(id);
     }
     public List<student> getall() {
         return studentRepositry.findAll();
     }
}
