package ru.hogwarts.schoolnik.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolnik.dto.StudentDto;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody StudentDto student) {
        return studentService.addStudent(student.getName(), student.getAge());
    }

    @GetMapping
    public Student get(@RequestParam long id) {
        return studentService.getStudent(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student.getName(), student.getAge());
    }

    @DeleteMapping
    public Student delete(@RequestParam long id) {
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
    public  int getCount() {
        return studentService.getCount();
    }
    @GetMapping("/avg-age")
    public  double getAvgAge() {
        return studentService.getAvgAge();
    }
    @GetMapping("/last-five")
    public  Collection<Student> getLastFiveStudents() {
        return studentService.getLastFive();
    }

    ///////////////// Stream API

    @GetMapping("/names-start-with-a")
    public List<String> getAllNamesStartWithA(){
        return studentService.getAllNamesStartWithA();
    }

    @GetMapping("/avg-age-with-stream")
    public double getAvgAgeWithStream() {
        return studentService.getAvgAgeWithStream();
    }

}
