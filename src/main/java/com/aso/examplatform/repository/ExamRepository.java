package com.aso.examplatform.repository;

import com.aso.examplatform.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findAllDeleted(boolean deleted);
}
