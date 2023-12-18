package ru.hogwarts.schoolnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolnik.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
