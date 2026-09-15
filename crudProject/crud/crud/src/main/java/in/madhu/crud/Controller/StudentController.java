package in.madhu.crud.Controller;

import in.madhu.crud.Dto.RequestDto;
import in.madhu.crud.Dto.ResponseDto;
import in.madhu.crud.Dto.UpdateRequestDto;
import in.madhu.crud.Dto.UpdateResponseDto;
import in.madhu.crud.Entity.Student;
import in.madhu.crud.Service.StudentService;
import jakarta.validation.Valid;
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
    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody RequestDto student) {
        ResponseDto studentreq=studentService.create(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentreq);
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> Get(@PathVariable Integer id){
        ResponseDto stu=studentService.getstudent(id);
        return ResponseEntity.ok(stu);
    }
    @GetMapping
    public ResponseEntity<List<UpdateResponseDto>> getAll(){
        List<UpdateResponseDto> stures=studentService.getstuall();
        return ResponseEntity.ok(stures);
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> update(@PathVariable Integer id, @RequestBody UpdateRequestDto student){
        UpdateResponseDto up=studentService.stuup(student,id);
        if(up==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(up);

    }
    @DeleteMapping
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
