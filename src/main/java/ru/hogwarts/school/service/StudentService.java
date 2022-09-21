package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

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

    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents called");
        return studentRepository.getLastFiveStudents();
    }
}
