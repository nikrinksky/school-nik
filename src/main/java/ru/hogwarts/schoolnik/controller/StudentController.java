package ru.hogwarts.schoolnik.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
    }

    @GetMapping("/by-age")
    public Collection<Student> getStudentByAge(@RequestParam Integer age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping
    public Map<Long, Student> getAllStudent() {
        return studentService.getAllStudent();
    }

}
