package ru.kors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kors.model.Text;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long>, JpaSpecificationExecutor<Text> {
}
