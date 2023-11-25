package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Faculty;

public interface FacultyService {
    Faculty addFaculty(String name, String color);

    Faculty getFaculty(long id);

    Faculty updateFaculty(long id, String name, String color);

    Faculty removeFaculty(long id);

}
