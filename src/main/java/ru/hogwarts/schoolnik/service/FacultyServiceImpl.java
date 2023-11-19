package ru.hogwarts.schoolnik.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.exception.FacultyNotFoundException;
import ru.hogwarts.schoolnik.exception.StudentNotFoundException;
import ru.hogwarts.schoolnik.model.Faculty;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        Long id = counter++;
        Faculty newFaculty = new Faculty(id, faculty.getName(), faculty.getColor());
        facultyMap.put(id, newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(String.format("Студента [%s] нет", id));
        }
        return facultyMap.get(id);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyMap.get(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public void removeFaculty(Long id) {
        facultyMap.remove(id);
        System.out.println(String.format("Faculty %s has been removed", id));
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, Faculty> getAllFaculty() {
        return facultyMap;
    }
}
