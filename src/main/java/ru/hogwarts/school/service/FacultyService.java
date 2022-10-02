package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;


@Service
public class FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Method createFaculty called");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Method findFaculty called");
        return facultyRepository.findById(id).
                orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Method editFaculty called");
        Faculty oldFaculty = facultyRepository.findById(faculty.getId())
                .orElseThrow(FacultyNotFoundException::new);
        oldFaculty.setName(faculty.getName());
        oldFaculty.setColor(faculty.getColor());
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Method deleteFaculty called");
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Method getAllFaculties called");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Method findByColor called");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> findByNameOrColor(String nameOrColor) {
        logger.info("Method findByNameOrColor called");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public String longestFacultyName() {
        Comparator<Faculty> compareByNameLength = Comparator.comparingInt(o -> o.getName().length());
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList.stream()
                .max(compareByNameLength)
                .orElseThrow(() -> new FacultyNotFoundException())
                .getName();
    }
}
