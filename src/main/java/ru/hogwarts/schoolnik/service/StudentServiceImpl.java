package ru.hogwarts.schoolnik.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(String name, Integer age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(long id, String name, Integer age) {
        Student studentForUpdate = getStudent(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    @Override
    public Student removeStudent(long id) {
        Student studentForDelete = getStudent(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    @Override
    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> fidStudentsByFaculty(long facultyId) {
        return studentRepository.findAllByFaculty_Id(facultyId);
    }
////////////////////////////////////
    @Override
    public int getCount() {
        return studentRepository.getCount();
    }

    @Override
    public double getAvgAge() {
        return studentRepository.getAvgAge();
    }

    @Override
    public  List<Student> getLastFive() {
        return studentRepository.getLastFiveOrderByIdDesc();

    }
}
