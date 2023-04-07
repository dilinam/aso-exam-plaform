package com.aso.examplatform.controller;

import com.aso.examplatform.model.Question;
import com.aso.examplatform.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Question")
@RequiredArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @GetMapping("")
    public List<Question> getAll(){
        return questionService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Question> save(@Valid @RequestBody Question newQuestion){
        return new ResponseEntity<>(questionService.create(newQuestion), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Question> update(@Valid @RequestBody Question question){
        try {
            return new ResponseEntity<>(questionService.update(question), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(questionService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> delete(@PathVariable("id") Long id){
        try {
            questionService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
