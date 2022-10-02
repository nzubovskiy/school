package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Method createStudent called");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Method findStudent called");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Method editStudent called");
        return studentRepository.save(student);
    }


    public void deleteStudent(Long id) {
        logger.info("Method deleteStudent called");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Method getAllStudents called");
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age) {
        logger.info("Method findByAge called");
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Method findByAgeBetween called");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Long getNumberStudents() {
        logger.info("Method getNumberStudents called");
        return studentRepository.getNumberStudents();
    }

    public Double getAverageAge() {
        logger.info("Method getAverageAge called");
        return studentRepository.getAverageAge();
    }

    public List<String> getLastStudents(int count) {
        logger.info("Method getLastStudents called");
        return studentRepository.getLastStudents(count).stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public List<String> getStudentsNameBeginsWith(String letter) {
        logger.info("Method getStudentsNameBeginsWith called");
        List<Student> studentList = studentRepository.findAll();
        String string = letter.toUpperCase();
        return studentList.stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith(string))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double  getAverageAgeThroughFindAll() {
        logger.info("Method getAverageAgeThroughFindAll called");
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow();

    }
}
