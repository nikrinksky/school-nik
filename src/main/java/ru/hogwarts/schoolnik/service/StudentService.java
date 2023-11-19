package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Long id, Student student);

    void removeStudent(Long id);

    List<Student> getStudentByAge(int age);

    Map<Long, Student> getAllStudent();
}
