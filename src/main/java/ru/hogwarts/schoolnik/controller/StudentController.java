package ru.hogwarts.schoolnik.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student.getName(), student.getAge());
    }

    @GetMapping
    public Student getStudent(@RequestParam long id) {
        return studentService.getStudent(id);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student.getName(), student.getAge());
    }

    @DeleteMapping
    public Student removeStudent(@RequestParam long id) {
        return studentService.removeStudent(id);
    }

    @GetMapping("/age-between")
    public List<Student> getWhenAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.getWhenAgeBetween(min, max);
    }

    @GetMapping("/byFaculty")
    public Collection<Student> findStudentByFaculty(@RequestParam long facId) {
        return studentService.fidStudentsByFaculty(facId);
    }

///////////////////////////
    @GetMapping("/count")
    public  long getCountOfStudents() {
        return studentService.studentsCount();
    }
    @GetMapping("/avgAge")
    public  double getAverageAge() {
        return studentService.averageAge();
    }
    @GetMapping("/lastfive")
    public  Collection<Student> getLastFiveStudents() {
        return studentService.lastFiveStudents();
    }

}
