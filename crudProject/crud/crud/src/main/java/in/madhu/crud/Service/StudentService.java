package in.madhu.crud.Service;

import in.madhu.crud.Dto.RequestDto;
import in.madhu.crud.Dto.ResponseDto;
import in.madhu.crud.Dto.UpdateRequestDto;
import in.madhu.crud.Dto.UpdateResponseDto;
import in.madhu.crud.Entity.Student;
import in.madhu.crud.Exception.DuplicateResourceException;
import in.madhu.crud.Exception.ResourceNotFoundException;
import in.madhu.crud.Repostiry.StudentRepostiry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepostiry studentRepostiry;

    public StudentService(StudentRepostiry studentRepostiry){
        this.studentRepostiry=studentRepostiry;
    }

    public ResponseDto create(RequestDto studentreq){

        Student stu=mapToEntity(studentreq);
        if(emailExsits(stu)){
            throw  new DuplicateResourceException("Given Student Email " + stu.getEmail()+"is already Exsits");
        }
        Student stu2=studentRepostiry.save(stu);
        return mapToDto(stu2);
    }
    public ResponseDto getstudent(Integer id){
        Student student=studentRepostiry.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id "+id+" Not found"));
        return mapToDto(student);
    }
    public List<UpdateResponseDto> getstuall() {
        List<Student> rew=studentRepostiry.findByDeletedIsFalse();
        return rew.stream()
                .map(this::maptoupdate)
                .toList();
    }
    public UpdateResponseDto stuup(UpdateRequestDto student, Integer id){
        Student  studenttosave=studentRepostiry.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Record Not found"));
        studenttosave.setSubject(student.getSubject());
        studenttosave.setRollno(student.getRollno());
        studenttosave.setName(student.getName());
        Student req=studentRepostiry.save(studenttosave);
        UpdateResponseDto updateResponseDto=maptoupdate(req);
        return updateResponseDto;
    }
    public Boolean deletestudent(Integer id){
       Boolean is= studentRepostiry.existsById(id);
       if(!is) return false;
       studentRepostiry.deleteById(id);
       return true;
    }
    public Boolean studentsoft(Integer id){
    Optional<Student> tre=studentRepostiry.findByIdAndDeletedIsFalse(id);
    if(tre.isEmpty()){
        return false;
    }
    Student tosave=tre.get();
    tosave.setDeleted(true);
        studentRepostiry.save(tosave);
        return true;
    }
    private Student mapToEntity(RequestDto requestDto) {
        Student student=new Student();
        student.setName(requestDto.getName());
        student.setRollno(requestDto.getRollno());
        student.setSubject(requestDto.getSubject());
        student.setEmail(requestDto.getEmail());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return student;
    }
    private ResponseDto mapToDto(Student student) {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setEmail(student.getEmail());
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setRollno(student.getRollno());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student Saved successful");
        responseDto.setUpdatedAt(LocalDateTime.now());
        return responseDto;
    }
    public UpdateResponseDto maptoupdate(Student student){
        UpdateResponseDto responseDto=new UpdateResponseDto();
        responseDto.setEmail(student.getEmail());
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setRollno(student.getRollno());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student Saved successful");
        responseDto.setUpdatedAt(LocalDateTime.now());
        return responseDto;
    }
    public boolean emailExsits(Student student) {
        return  studentRepostiry.existsByEmail(student.getEmail());
    }
}
