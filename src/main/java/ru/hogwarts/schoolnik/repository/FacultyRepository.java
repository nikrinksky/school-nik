package ru.hogwarts.schoolnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolnik.model.Faculty;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColorOrNameIgnoreCase(String name, String color);
}
