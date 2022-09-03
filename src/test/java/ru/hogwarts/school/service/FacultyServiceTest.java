package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import java.util.Collections;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    @Test
    public void createFacultyTest() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");
        when(facultyRepository.save(firstFaculty)).thenReturn(firstFaculty);
        assertThat(facultyService.createFaculty(firstFaculty)).isEqualTo(firstFaculty);
    }

    @Test
    public void findFacultyTest() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");

        Faculty secondFaculty = new Faculty();
        secondFaculty.setId(2L);
        secondFaculty.setName("Fac");
        secondFaculty.setColor("col");

        facultyService.createFaculty(firstFaculty);
        facultyService.createFaculty(secondFaculty);

        when(facultyRepository.findById(2L)).thenReturn(Optional.of(secondFaculty));
        assertThat(facultyService.findFaculty(2L)).isEqualTo(secondFaculty);
    }

    @Test
    public void editFacultyTest() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");

        facultyService.createFaculty(firstFaculty);
        firstFaculty.setName("Ff");

        when(facultyRepository.save(firstFaculty)).thenReturn(firstFaculty);
        assertThat(facultyService.createFaculty(firstFaculty)).isEqualTo(facultyService.editFaculty(firstFaculty));
    }

    @Test
    public void deleteFacultyTest() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");

        facultyService.createFaculty(firstFaculty);
        facultyService.deleteFaculty(1L);
        assertThat(facultyService.getAllFaculties()).isEmpty();
    }

    @Test
    public void findFacultyByColorTest() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");

        Faculty secondFaculty = new Faculty();
        secondFaculty.setId(2L);
        secondFaculty.setName("Fac");
        secondFaculty.setColor("col");

        facultyService.createFaculty(firstFaculty);
        facultyService.createFaculty(secondFaculty);

        when(facultyRepository.findByColorIgnoreCase("Col")).thenReturn(Collections.singletonList(secondFaculty));
        assertThat(facultyService.findByColor("Col")).contains(secondFaculty);
    }

    @Test
    public void findByName() {
        Faculty firstFaculty = new Faculty();
        firstFaculty.setId(1L);
        firstFaculty.setName("F");
        firstFaculty.setColor("color");

        Faculty secondFaculty = new Faculty();
        secondFaculty.setId(2L);
        secondFaculty.setName("Fac");
        secondFaculty.setColor("col");

        facultyService.createFaculty(firstFaculty);
        facultyService.createFaculty(secondFaculty);

        when(facultyRepository.findByNameIgnoreCase("fac")).thenReturn(Collections.singletonList(secondFaculty));
        assertThat(facultyService.findByName("fac")).contains(secondFaculty);
    }


}