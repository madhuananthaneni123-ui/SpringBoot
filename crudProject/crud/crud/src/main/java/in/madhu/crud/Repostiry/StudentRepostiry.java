package in.madhu.crud.Repostiry;

import in.madhu.crud.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentRepostiry extends JpaRepository<Student, Integer> {
     Optional<Student> findByIdAndDeletedIsFalse(Integer id);
     List<Student> findByDeletedIsFalse();
     boolean existsByEmail(String Email);
}
