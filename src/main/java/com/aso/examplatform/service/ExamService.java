package com.aso.examplatform.service;

import com.aso.examplatform.model.Exam;
import com.aso.examplatform.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> listAll(){
        return (List<Exam>) examRepository.findAll();
    }
    public Exam create(Exam exam){
        examRepository.save(exam);
        return exam;
    }
    public Exam update(Exam exam) throws Exception{
        if (!examRepository.findById(exam.getExamId()).isPresent()){
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
        if (!examOptional.isPresent()){
            throw new Exception("Exam not found");
        }
        Exam exam = examOptional.get();
        exam.setDeleted(true);
        examRepository.save(exam);
    }
}
