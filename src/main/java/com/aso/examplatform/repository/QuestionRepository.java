package com.aso.examplatform.repository;

import com.aso.examplatform.model.Question;
import com.aso.examplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("DELETE FROM Question q WHERE q.exam.examId=?1")
    void deleteAllByExamId(Long examId);

    @Query("SELECT q FROM Question q  WHERE q.exam.examId=?1")
    Optional<List<Question>> findAllQuestionsByExamId(Long examId);
}
