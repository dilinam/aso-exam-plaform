package com.aso.examplatform.service;

import com.aso.examplatform.model.Candidate;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.CandidateRepository;
import com.aso.examplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<Candidate> listAll(){
        return (List<Candidate>) repository.findAll();
    }

    public Candidate create(Candidate candidate) {
        repository.save(candidate);
        return candidate;
    }
    public Candidate update(Candidate candidate) throws Exception {
        if(!repository.findById(candidate.getCandidateId()).isPresent()){
            throw new Exception("candidate not found");
        }
        repository.save(candidate);
        return candidate;
    }
    public Candidate get(Long id) throws Exception {
        Optional<Candidate> result = repository.findById(id);
        return result.orElseThrow(() -> new Exception("candidate not found"));
    }
    public void delete(Long id) throws Exception {
        Optional<Candidate> candidateOptional = repository.findById(id);
        if(!candidateOptional.isPresent()){
            throw new Exception("candidate not found");
        }
        Candidate candidate = candidateOptional.get();
        User user = userRepository.getById(candidate.getUser().getUserId());
        user.setDeleted(true);
        userRepository.save(user);
    }

}
