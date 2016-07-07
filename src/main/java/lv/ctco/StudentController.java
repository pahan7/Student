package lv.ctco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


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

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(path = "/{id}/assignment", method = RequestMethod.POST)
    public ResponseEntity addAssignment(@PathVariable("id") int id, @RequestBody Assignment assignment){
        Student st = studentRepository.findOne(id);
        st.addAssignment(assignment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll() {
        studentRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(path = "/{id}/assignment/{aId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteAssignmentById(@PathVariable("id") int id,@PathVariable("aId") int aId){
        Student st = studentRepository.getOne(id);
        st.deleteAssignment(aId);
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