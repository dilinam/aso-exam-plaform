package com.aso.examplatform.repository;

import com.aso.examplatform.model.ExamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamUserRepository extends JpaRepository<ExamUser,Long> {
}
