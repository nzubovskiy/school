package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentInfo(@PathVariable Long id) {
        Object student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/filterByAge")
    public ResponseEntity<Collection<Student>> filterStudents(@RequestParam int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/findByAgeBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        if (maxAge > minAge && minAge > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudent(id).getFaculty());
    }

    @GetMapping("/getNumberStudents")
    public Long getNumberStudents() {
        return studentService.getNumberStudents();
    }

    @GetMapping("/getAverageAge")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping(" getAverageAge1")
    public Double getAverageAgeThroughFindAll() {
        return studentService. getAverageAgeThroughFindAll();
    }

    @GetMapping("/getLastStudents")
    public List<String> getLastStudents(@RequestParam int count) {
        return studentService.getLastStudents(count);
    }

    @GetMapping("/getStudentsNameBeginsWith")
    public List<String> getStudentsNameBeginsWith(@RequestParam String letter) {
        return studentService.getStudentsNameBeginsWith(letter);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
