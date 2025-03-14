package com.davi.timetable.controllers;

import com.davi.timetable.entities.Subject;
import com.davi.timetable.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/timetable/subject")
public class SubjectController {

    private SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Subject subject) {
        Subject subjectData = subjectService.findByCodeOrName(subject.getSubjectCode(), subject.getSubjectName());
        if(subjectData != null) {
            if (subjectData.getSubjectCode().equals(subject.getSubjectCode())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This subject code already exist.");
            }
            if (subjectData.getSubjectName().equals(subject.getSubjectName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This subject name already exist.");
            }
        }
        if(subject.getSubjectCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject code cannot be empty.");
        }
        if(subject.getSubjectName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject name cannot be empty.");
        }
        subjectService.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Subject saved successfully.");
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Subject>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }
}
