package app.social.spring.API;

import app.social.spring.Entity.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentResource {

    private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student(1L,"Nabeel","10"));
        students.add(new Student(1L,"MJ","20"));
        students.add(new Student(1L,"Hassan","15"));
        students.add(new Student(1L,"Ali","17"));
        students.add(new Student(1L,"Saif","25"));
    }
    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return students;
    }
}
