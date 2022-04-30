package com.aso.examplatform.service;

import com.aso.examplatform.model.Question;
import com.aso.examplatform.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> listAll(){
        return (List<Question>) questionRepository.findAll();
    }
    public Question create(Question question){
        questionRepository.save(question);
        return question;
    }
    public Question update(Question question) throws Exception{
        if (!questionRepository.findById(question.getQuestionId()).isPresent()){
            throw new Exception("Question not found");
        }
        questionRepository.save(question);
        return question;
    }
    public Question get(Long id) throws Exception{
        Optional<Question> result = questionRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Question not found"));
    }
    public void delete(Long id) throws Exception{
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (!questionOptional.isPresent()){
            throw new Exception("Exam not found");
        }
        Question question = questionOptional.get();
        question.setDeleted(true);
        questionRepository.save(question);
    }




}
