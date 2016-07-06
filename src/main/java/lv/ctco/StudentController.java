package lv.ctco;

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



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> students() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> studentById(@PathVariable("id") int id) {
        //Student student = students.stream().filter((s) -> s.getId() == id).findFirst().get();
        for (Student s1 : students) {
            if (s1.getId() == id)
                return new ResponseEntity<>(s1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> studentsPost(@RequestBody Student student) {

        if (student.getFirstName().equals("") || student.getLastName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            students.add(student);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll() {
        students.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {


       if (students.remove(students.stream().filter((s) -> s.getId() == id).findFirst().get()))
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@PathVariable("id") int id, @RequestBody Student student) {
        if (student.getFirstName().equals("") || student.getLastName().equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        for (Student s1 : students) {
            if (s1.getId() == id) {
                s1.setFirstName(student.getFirstName());
                s1.setLastName(student.getLastName());
                return new ResponseEntity<>(s1, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}