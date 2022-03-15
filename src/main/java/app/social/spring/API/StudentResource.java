package app.social.spring.API;

import app.social.spring.Entity.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentResource {

    private List<Student> students = new ArrayList<>();

//    @PostConstruct
//    public void init() {
//        students.add(new Student(1L, "Mohamad J Makki", "120"));
//        students.add(new Student(2L, "Nabeel D Hussein", "20"));
//        students.add(new Student(3L, "Hassan A Hummadi", "23"));
//    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return students;
    }
}
