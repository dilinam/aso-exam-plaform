package com.aso.examplatform.controller;

import com.aso.examplatform.model.Examinor;
import com.aso.examplatform.service.ExaminorService;
import com.aso.examplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/examinors")
@RequiredArgsConstructor
public class ExaminorController {

    private final ExaminorService examinorService;
    private final UserService userService;

    @GetMapping("")
    public List<Examinor> getAll(){
        return examinorService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Examinor> save(@Valid @RequestBody Examinor newexaminor){
        return new ResponseEntity<>(examinorService.create(newexaminor), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Examinor> update(@Valid @RequestBody Examinor examinor) throws Exception{
        return new ResponseEntity<>(examinorService.update(examinor), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examinor> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(examinorService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Examinor> delete(@PathVariable("id") Long id) throws Exception {
        examinorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
