package com.aso.examplatform.service;

import com.aso.examplatform.model.Candidate;
import com.aso.examplatform.model.Examinor;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.ExaminorRepository;
import com.aso.examplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminorService {
    @Autowired
    private ExaminorRepository examinorRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Examinor> listAll(){
        return (List<Examinor>) examinorRepository.findAll();
    }

    public Examinor create(Examinor examinor){
        examinorRepository.save(examinor);
        return examinor;
    }

    public Examinor update(Examinor examinor) throws Exception{
        if (!examinorRepository.findById(examinor.getExaminorId()).isPresent()){
            throw new Exception("Examinor not found");
        }
        examinorRepository.save(examinor);
        return examinor;
    }

    public Examinor get(Long id) throws Exception{
        Optional<Examinor> result = examinorRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Examinor not found"));
    }

    public void delete(Long id) throws Exception{
        Optional<Examinor> examinorOptional = examinorRepository.findById(id);
        if (!examinorOptional.isPresent()){
            throw new Exception("Examinor not found");
        }
        Examinor examinor = examinorOptional.get();
        User user = userRepository.getById(examinor.getUser().getUserId());
        user.setDeleted(true);
        userRepository.save(user);
    }

}
