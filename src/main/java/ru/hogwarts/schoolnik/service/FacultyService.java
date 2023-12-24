package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(String name, String color);

    Faculty getFaculty(long id);

    Faculty updateFaculty(long id, String name, String color);

    Faculty removeFaculty(long id);

    Collection<Faculty> findByColor(String name, String color);

    String getLongestName();
}
