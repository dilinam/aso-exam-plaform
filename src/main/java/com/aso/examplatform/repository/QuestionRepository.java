package com.aso.examplatform.repository;

import com.aso.examplatform.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("DELETE FROM Question q WHERE q.exam.examId=?1")
    void deleteAllByExamId(Long examId);
}
