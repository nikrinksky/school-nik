package ru.hogwarts.schoolnik.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.model.Faculty;
import ru.hogwarts.schoolnik.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(String name, String color) {
        logger.info("вызван метод addFaculty");
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty getFaculty(long id) {
        logger.info("вызван метод getFaculty");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(long id, String name, String color) {
        logger.info("вызван метод updateFaculty");
        Faculty facultyForUpdate = getFaculty(id);
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);
        return facultyRepository.save(facultyForUpdate);
    }

    @Override
    public Faculty removeFaculty(long id) {
        logger.info("вызван метод removeFaculty");
        Faculty facultyForDelete = getFaculty(id);
        facultyRepository.delete(facultyForDelete);
        return facultyForDelete;
    }


    public Collection<Faculty> getAllFacults() {
        logger.info("вызван метод getAllFacults");

        return facultyRepository.findAll();
    }

    @Override

    public Collection<Faculty> findByColor(String name, String color) {
        logger.info("вызван метод findByColor");
        return facultyRepository.findAllByColorOrNameIgnoreCase(name, color);

    }

}
