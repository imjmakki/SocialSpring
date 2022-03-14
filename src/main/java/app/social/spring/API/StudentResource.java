package app.social.spring.API;

import app.social.spring.Entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentResource {

    private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student(1L, "Mohamad J Makki", "120"));
        students.add(new Student(2L, "Nabeel D Hussein", "20"));
        students.add(new Student(3L, "Hassan A Hummadi", "23"));
    }

    public List<Student> getStudents() {
        return students;
    }
}
