package ru.hogwarts.schoolnik.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.model.Faculty;
import ru.hogwarts.schoolnik.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(String name, String color) {
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(long id, String name, String color) {
        Faculty facultyForUpdate = getFaculty(id);
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);
        return facultyRepository.save(facultyForUpdate);
    }

    @Override
    public Faculty removeFaculty(long id) {
        Faculty facultyForDelete = getFaculty(id);
        facultyRepository.delete(facultyForDelete);
        return facultyForDelete;
    }


    public Collection<Faculty> getAllFacults() {

        return facultyRepository.findAll();
    }

    @Override

    public Collection<Faculty> findByColor(String name, String color) {
        return facultyRepository.findAllByColorOrNameIgnoreCase(name, color);

    }


//    public List<Faculty> getByColor(String color) {
//        return facultyRepository.findAll().stream()
//                .filter(faculty -> faculty.getColor().equals(color))
//                .collect(Collectors.toList());
//    }
}
