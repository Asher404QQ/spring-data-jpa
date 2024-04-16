package ru.kors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.model.Text;

public interface TextRepository extends JpaRepository<Text, Long> {
}
