package com.aso.examplatform.service;

import com.aso.examplatform.model.Examiner;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.ExaminerRepository;
import com.aso.examplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminerService {
    @Autowired
    private ExaminerRepository examinerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Examiner> listAll(){
        return (List<Examiner>) examinerRepository.findAll();
    }

    public Examiner create(Examiner examiner){
        examinerRepository.save(examiner);
        return examiner;
    }

    public Examiner update(Examiner examiner) throws Exception{
        if (!examinerRepository.findById(examiner.getExaminerId()).isPresent()){
            throw new Exception("Examiner not found");
        }
        examinerRepository.save(examiner);
        return examiner;
    }

    public Examiner get(Long id) throws Exception{
        Optional<Examiner> result = examinerRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Examiner not found"));
    }

    public void delete(Long id) throws Exception{
        Optional<Examiner> examinerOptional = examinerRepository.findById(id);
        if (!examinerOptional.isPresent()){
            throw new Exception("Examiner not found");
        }
        Examiner examiner = examinerOptional.get();
        User user = userRepository.getById(examiner.getUser().getUserId());
        user.setDeleted(true);
        userRepository.save(user);
    }

}
