package ru.hogwarts.schoolnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolnik.model.Faculty;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
