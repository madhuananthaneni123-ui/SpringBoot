package in.madhu.crud.Service;

import in.madhu.crud.Entity.Student;
import in.madhu.crud.Repostiry.StudentRepostiry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepostiry studentRepostiry;

    public StudentService(StudentRepostiry studentRepostiry){
        this.studentRepostiry=studentRepostiry;
    }

    public Student create(Student studentreq){

        Student stu=studentRepostiry.save(studentreq);

        return stu;
    }
    public Student getstudent(Integer id){
        Optional<Student> res=studentRepostiry.findByIdAndDeletedIsFalse(id);
        if(res.isPresent()){
            return res.get();
        }
        return null;
    }
    public List<Student> getstuall() {
        List<Student> rew=studentRepostiry.findByDeletedIsFalse();
        return rew;
    }
    public Student stuup(Student student,Integer id){
        Optional<Student> tr=studentRepostiry.findById(id);
        if(tr.isEmpty()) return null;
        Student studenttosave=tr.get();
        studenttosave.setSubject(student.getSubject());
        studenttosave.setEmail(student.getEmail());
        studenttosave.setRollno(student.getRollno());
        studenttosave.setName(student.getName());
        Student req=studentRepostiry.save(studenttosave);
        return req;
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
}
