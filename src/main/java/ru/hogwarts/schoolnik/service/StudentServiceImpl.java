package ru.hogwarts.schoolnik.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.exception.StudentNotFoundException;
import ru.hogwarts.schoolnik.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Student addStudent(Student student) {
        Long id = counter++;
        Student newStudent = new Student(id, student.getName(), student.getAge());
        studentMap.put(id, newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(String.format("Студента [%s] нет", id));
        }
        return studentMap.get(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentMap.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    @Override
    public void removeStudent(Long id) {
        studentMap.remove(id);
        System.out.println(String.format("Student %s has been removed", id));
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return studentMap.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();

    }
    @Override
    public Map<Long, Student> getAllStudent() {
        return studentMap;
    }
}
