package lv.ctco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<Student>() {{
        Student student1 = new Student();
        student1.setFirstName("Ivan");
        student1.setLastName("a");
        add(student1);
    }};
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        return new ResponseEntity<>(studentRepository.findOne(id), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll() {
        studentRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

        studentRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody Student student) {
        if (student.getFirstName().equals("") || student.getLastName().equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Student student1 = studentRepository.getOne(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        studentRepository.save(student1);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}