package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Faculty;

import java.util.List;
import java.util.Map;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty updateFaculty(Long id, Faculty faculty);

    void removeFaculty(Long id);

    List<Faculty> getFacultyByColor(String color);

    Map<Long, Faculty> getAllFaculty();
}
