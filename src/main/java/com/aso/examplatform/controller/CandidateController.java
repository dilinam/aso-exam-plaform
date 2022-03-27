package com.aso.examplatform.controller;

import com.aso.examplatform.model.Candidate;
import com.aso.examplatform.service.CandidateService;
import com.aso.examplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final UserService userService;

    @GetMapping("")
    public List<Candidate> getAll(){
        return candidateService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Candidate> save(@Valid @RequestBody Candidate newcandidate) {
        return new ResponseEntity<>(candidateService.create(newcandidate), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Candidate> update(@Valid @RequestBody Candidate candidate) throws Exception {
        return new ResponseEntity<>(candidateService.update(candidate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(candidateService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candidate> delete(@PathVariable("id") Long id) throws Exception {
        candidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
