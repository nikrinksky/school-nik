package ru.hogwarts.schoolnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolnik.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAgeBetween(Integer min, Integer max);
    Collection<Student> findAllByFaculty_Id(long facultyId);


    //////////////////////////////

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM student",
            nativeQuery = true
    )
    int getCount();

    @Query(
            value = "SELECT AVG(age) " +
                    "FROM student",
            nativeQuery = true
    )
    double getAvgAge();

    @Query(
            value = "SELECT * " +
                    "FROM student " +
                    "ORDER BY id DESC " +
                    "LIMIT 5",
            nativeQuery = true
    )
    List<Student> getLastFiveOrderByIdDesc();

}
