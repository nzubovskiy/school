package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void createStudentTest() {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student");
        firstStudent.setAge(13);
        when(studentRepository.save(firstStudent)).thenReturn(firstStudent);
        assertThat(studentService.createStudent(firstStudent)).isEqualTo(firstStudent);
    }

    @Test
    public void findStudentTest() {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student");
        firstStudent.setAge(13);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("Student2");
        secondStudent.setAge(12);

        studentService.createStudent(firstStudent);
        studentService.createStudent(secondStudent);

        when(studentRepository.findById(2L)).thenReturn(Optional.of(secondStudent));
        assertThat(studentService.findStudent(2L)).isEqualTo(secondStudent);
    }

    @Test
    public void editStudentTest() {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student");
        firstStudent.setAge(13);

        studentService.createStudent(firstStudent);
        firstStudent.setName("Student1");

        when(studentRepository.save(firstStudent)).thenReturn(firstStudent);
        assertThat(studentService.createStudent(firstStudent)).isEqualTo(studentService.editStudent(firstStudent));
    }

    @Test
    public void deleteStudentTest() {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student");
        firstStudent.setAge(13);

        studentService.createStudent(firstStudent);
        studentService.deleteStudent(1L);
        assertThat(studentService.getAllStudents()).isEmpty();
    }

    @Test
    public void findStudentByAgeTest() {
        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Student");
        firstStudent.setAge(13);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("Student2");
        secondStudent.setAge(12);

        studentService.createStudent(firstStudent);
        studentService.createStudent(secondStudent);

        when(studentRepository.findByAge(12)).thenReturn(Collections.singletonList(secondStudent));
        assertThat(studentService.findByAge(12)).contains(secondStudent);
    }
}