package com.aso.examplatform.controller;

import com.aso.examplatform.dto.AddCandidateRequest;
import com.aso.examplatform.dto.ExamRequest;
import com.aso.examplatform.dto.UpdateQuestionRequest;
import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.ExamUser;
import com.aso.examplatform.model.Question;
import com.aso.examplatform.model.User;
import com.aso.examplatform.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("")
    public List<Exam> getAll(){
        return examService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Exam> save(@Valid @RequestBody ExamRequest examRequest){
        return new ResponseEntity<>(examService.create(examRequest), HttpStatus.CREATED);
    }
    @PostMapping(path = "/candidate")
    public ResponseEntity<List<ExamUser>> candiadateSave(@Valid @RequestBody AddCandidateRequest addCandidateRequest){
        try {
            return new ResponseEntity<>(examService.addCandidateExam(addCandidateRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/Questions")
    public ResponseEntity<List<Question>> updateQuestions(@Valid @RequestBody UpdateQuestionRequest examRequest){
        try {
            return new ResponseEntity<>(examService.updateQuestions(examRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "")
    public ResponseEntity<Exam> update(@Valid @RequestBody Exam exam){
        try {
            return new ResponseEntity<>(examService.update(exam), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(examService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/examUser/{id}")
    public ResponseEntity<List<User>> getAllCandidateExam(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(examService.getAllCandidateExam(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/examQuestion/{id}")
    public ResponseEntity<List<Question>> getAllQuestion(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(examService.getAllQuestion(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> delete(@PathVariable("id") Long id){
        try {
            examService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
