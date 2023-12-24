package ru.hogwarts.schoolnik.service;

import ru.hogwarts.schoolnik.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(String name, Integer age);

    Student getStudent(long id);

    Student updateStudent(long id, String name, Integer age);

    Student removeStudent(long id);

    List<Student> getWhenAgeBetween(Integer min, Integer max);

    Collection<Student> fidStudentsByFaculty(long facultyId);

    //////////////////////////////////

    int getCount();

    double getAvgAge();

    List<Student> getLastFive();

    ///////////////// Stream API
    List<String> getAllNamesStartWithA();

    double getAvgAgeWithStream();
}
