package ru.hogwarts.schoolnik.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolnik.model.Faculty;
import ru.hogwarts.schoolnik.service.FacultyService;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty.getName(), faculty.getColor());
    }

    @GetMapping
    public Faculty getFaculty(@RequestParam long id) {
        return facultyService.getFaculty(id);
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    @DeleteMapping
    public Faculty removeFaculty(@RequestParam long id) {
        return facultyService.removeFaculty(id);
    }

}
