package ru.hogwarts.schoolnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolnik.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
