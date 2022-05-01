package com.aso.examplatform.service;

import com.aso.examplatform.dto.AddCandidateRequest;
import com.aso.examplatform.dto.ExamRequest;
import com.aso.examplatform.dto.UpdateQuestionRequest;
import com.aso.examplatform.model.*;
import com.aso.examplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final ExamUserRepository examUserRepository;
    private final TenantUserCourseRepository tenantUserCourseRepository;
    private final TenantUserRepository tenantUserRepository;

    public List<Exam> listAll(){
        return examRepository.findAllByDeleted(false);
    }
    public Exam create(ExamRequest examRequest){
        examRepository.save(examRequest.getExam());
            for (Question question: examRequest.getQuestions()) {
                question.setExam(examRequest.getExam());
            }
            questionRepository.saveAll(examRequest.getQuestions());

        return examRequest.getExam();
    }
    public List<Question> updateQuestions(UpdateQuestionRequest examRequest) throws Exception{
        Exam exam = examRepository.findById(examRequest.getExamId()).orElseThrow(() -> new Exception("Exam Not found")); // get exam using id, If not found throw exception
        questionRepository.deleteAllByExamId(exam.getExamId()); // delete all questions of the exm from db
        for (Question question: examRequest.getQuestions()) {
            question.setExam(exam); // set exam to questions
        }
        return questionRepository.saveAll(examRequest.getQuestions());
    }
    public Exam update(Exam exam) throws Exception{
        if (examRepository.findById(exam.getExamId()).isEmpty()){
            throw new Exception("Exam not found");
        }
        examRepository.save(exam);
        return exam;
    }
    public Exam get(Long id) throws Exception{
        Optional<Exam> result = examRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Exam not found"));
    }
    public void delete(Long id) throws Exception{
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()){
            throw new Exception("Exam not found");
        }
        Exam exam = examOptional.get();
        exam.setDeleted(true);
        examRepository.save(exam);
    }
    public List<ExamUser> addCandidateExam(AddCandidateRequest addCandidateRequest) throws Exception {
        Exam exam = examRepository.findById(addCandidateRequest.getExamId()).orElseThrow(() -> new Exception("Exam not found")); // get exam, if not exists throw exception
        List<ExamUser> examUsers = new ArrayList<>();
        if (addCandidateRequest.isForAll()) {
            exam.setForAll(true); // If exam is available for all users in the course set for_all to TRUE
            examRepository.save(exam);
            // Get all users of the relevant course and add to list
            for (TenantUser tenantUser : tenantUserCourseRepository.findTenantUserByCourseId(exam.getCourse().getCourseId())) {
                examUsers.add(new ExamUser(exam, tenantUser.getUser()));
            }
        } else {
            // Get selected tenant users by id and add to list
            for (TenantUser tenantUser : tenantUserRepository.findAllById(addCandidateRequest.getTenantUserIds())) {
                examUsers.add(new ExamUser(exam, tenantUser.getUser()));
            }
        }
        return examUserRepository.saveAll(examUsers);
    }
    public List<User> getAllCandidateExam(Long id) throws Exception{
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()){
            throw new Exception("Exam not found");
        }
        Optional<List<User>> users = examUserRepository.findUsersByExamId(examOptional.get().getExamId());
        return users.get();

    }
    public List<Question> getAllQuestion(Long id) throws Exception{
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()){
            throw new Exception("Exam not found");
        }
        Optional<List<Question>> Questions = questionRepository.findAllQuestionsByExamId(examOptional.get().getExamId());
        return Questions.get();
    }
}
