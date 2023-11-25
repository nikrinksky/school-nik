package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Student;

public interface StudentService {
    Student addStudent(String name, Integer age);

    Student getStudent(long id);

    Student updateStudent(long id, String name, Integer age);

    Student removeStudent(long id);

}
