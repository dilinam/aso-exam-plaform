package com.aso.examplatform.service;

import com.aso.examplatform.dto.AddCandidate;
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
        return examRepository.findAllNotDeleted(false);
    }
    public Exam create(ExamRequest examRequest){
        examRepository.save(examRequest.getExam());
            for (Question question: examRequest.getQuestions()) {
                question.setExam(examRequest.getExam());
            }
            questionRepository.saveAll(examRequest.getQuestions());

        return examRequest.getExam();
    }
    public List<Question> updateQuestions(ExamRequest examRequest){
        questionRepository.deleteAll(examRequest.getQuestions());
        List<Question> questionList = null ;
        for (Question question: examRequest.getQuestions()) {
            question.setExam(examRequest.getExam());
            assert false;
            questionList.add(question);
        }
        assert false;
        questionRepository.saveAll(questionList);
        return examRequest.getQuestions();
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
    public List<ExamUser> addCandidateExam(AddCandidate addCandidate) throws Exception {
        Optional<Exam> examOptional = examRepository.findById(addCandidate.getExam().getExamId());
        List<ExamUser> examUser;
        if (examOptional.isEmpty()) {
            throw new Exception("Exam not found");
        } else {
            examUser = null;
            if (addCandidate.getExam().isForAll()) {
                addCandidate.getExam().setForAll(true);
                examRepository.save(addCandidate.getExam());
                for (User user : userRepository.findAll()) {
                    assert false;
                    examUser.add(new ExamUser(addCandidate.getExam(), user));
                }

            } else {
                for (User user : userRepository.findAllById(addCandidate.getTenantUserID())) {
                    assert false;
                    examUser.add(new ExamUser(addCandidate.getExam(), user));
                }
            }
            assert false;
            examUserRepository.saveAll(examUser);
        }


        return examUser;
    }
}
