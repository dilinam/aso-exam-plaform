package com.aso.examplatform.service;

import com.aso.examplatform.dto.AddCandidateRequest;
import com.aso.examplatform.dto.ExamRequest;
import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.ExamUser;
import com.aso.examplatform.model.Question;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.ExamRepository;
import com.aso.examplatform.repository.ExamUserRepository;
import com.aso.examplatform.repository.QuestionRepository;
import com.aso.examplatform.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ExamUserRepository examUserRepository;

    public List<Exam> listAll(){
        return examRepository.findAllDeleted(false);
    }
    public Exam create(ExamRequest examRequest){
        examRepository.save(examRequest.getExam());
        List<Question> questionList = new ArrayList<>();
            for (Question question: examRequest.getQuestions()) {
                question.setExam(examRequest.getExam());

                questionList.add(question);
            }
        questionRepository.saveAll(questionList);

        return examRequest.getExam();
    }
    public List<Question> updateQuestions(ExamRequest examRequest){
        questionRepository.deleteAll(examRequest.getQuestions());
        List<Question> questionList = new ArrayList<>();
        for (Question question: examRequest.getQuestions()) {
            question.setExam(examRequest.getExam());
            questionList.add(question);
        }
        questionRepository.saveAll(questionList);
        return questionList;
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
        Optional<Exam> examOptional = examRepository.findById(addCandidateRequest.getExamId());
        List<ExamUser> examUser ;
        if (examOptional.isEmpty()) {
            throw new Exception("Exam not found");
        } else {
            examUser = new ArrayList<>();;
            if (addCandidateRequest.isForAll()) {
                examOptional.get().setForAll(true);
                examRepository.save(examOptional.get());
                for (User user : userRepository.findAll()) {
                    examUser.add(new ExamUser(examOptional.get(), user));
                }

            } else {
                for (User user : userRepository.findAllById(addCandidateRequest.getTenantUserID())) {
                    examUser.add(new ExamUser(examOptional.get(), user));
                }
            }
            examUserRepository.saveAll(examUser);
        }


        return examUser;
    }
}
