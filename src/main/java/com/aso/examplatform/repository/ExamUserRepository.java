package com.aso.examplatform.repository;

import com.aso.examplatform.model.ExamUser;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface ExamUserRepository extends JpaRepository<ExamUser,Long> {
    @Query("SELECT xu.user FROM ExamUser xu WHERE xu.exam.examId=?1")
    List<User> findUsersByExamId(Long examId);
}
