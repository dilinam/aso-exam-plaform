package com.aso.examplatform.controller;

import com.aso.examplatform.model.Examiner;
import com.aso.examplatform.service.ExaminerService;
import com.aso.examplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/examiners")
@RequiredArgsConstructor
public class ExaminerController {

    private final ExaminerService examinerService;
    private final UserService userService;

    @GetMapping("")
    public List<Examiner> getAll(){
        return examinerService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Examiner> save(@Valid @RequestBody Examiner newexaminer){
        return new ResponseEntity<>(examinerService.create(newexaminer), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Examiner> update(@Valid @RequestBody Examiner examiner) throws Exception{
        return new ResponseEntity<>(examinerService.update(examiner), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examiner> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(examinerService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Examiner> delete(@PathVariable("id") Long id) throws Exception {
        examinerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
