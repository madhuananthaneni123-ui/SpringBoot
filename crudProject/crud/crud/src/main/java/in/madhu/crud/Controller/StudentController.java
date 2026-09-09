package in.madhu.crud.Controller;

import in.madhu.crud.Entity.Student;
import in.madhu.crud.Service.StudentService;
import org.apache.el.parser.BooleanNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        student.setDeleted(false);
        Student studentreq=studentService.create(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentreq);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> Get(@PathVariable Integer id){
        Student stu=studentService.getstudent(id);
        if(stu==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stu);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> stures=studentService.getstuall();
        if(stures.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stures);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id,@RequestBody Student student){
        Student up=studentService.stuup(student,id);
        if(up==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(up);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        Boolean isdeleted=studentService.deletestudent(id);
        if(!isdeleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Record deleted");
    }
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> soft(@PathVariable Integer id){
        Boolean isdeleted=studentService.studentsoft(id);
        if(!isdeleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Soft Deleted");
    }
}
