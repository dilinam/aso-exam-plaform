package com.aso.examplatform.service;

import com.aso.examplatform.dto.ExamRequest;
import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.Question;
import com.aso.examplatform.repository.ExamRepository;
import com.aso.examplatform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    public List<Exam> listAll(){
        return examRepository.findAll();
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
        for (Question question: examRequest.getQuestions()) {
            question.setExam(examRequest.getExam());
            questionRepository.save(question);
        }
//        questionRepository.saveAll(examRequest.getQuestions());
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
}
