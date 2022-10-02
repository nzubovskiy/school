package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    Optional<Student> findById(Long id);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Long getNumberStudents();

    @Query(value = "select avg(age) as age from student", nativeQuery = true)
    Double getAverageAge();

    @Query(value = "select * from student order by id desc limit :count", nativeQuery = true)
    List<Student> getLastStudents(@Param("count") int count);
}
