package com.davi.timetable.controllers;

import com.davi.timetable.entities.Subject;
import com.davi.timetable.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Subject subject) {
        Subject subjectData = subjectService.findById(subject.getSubjectId());
        if (subjectData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
        }
        if(subject.getSubjectCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject code cannot be empty.");
        }
        if(subject.getSubjectName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject name cannot be empty.");
        }
        subjectService.update(subject);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Subject updated successfully.");
    }

    @GetMapping("/find/{subjectId}")
    public ResponseEntity<Object> findById(@PathVariable("subjectId") UUID subjectId) {
        Subject subject = subjectService.findById(subjectId);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }

    @GetMapping("/find/code/{subjectCode}")
    public ResponseEntity<Object> findByCode(@PathVariable("subjectCode") String subjectCode) {
        if (subjectCode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject code cannot be empty.");
        }
        Subject subject = subjectService.findByCodeOrName(subjectCode, null);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }

    @GetMapping("/find/name/{subjectName}")
    public ResponseEntity<Object> findByName(@PathVariable(value = "subjectName") String subjectName) {
        if (subjectName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subject name cannot be empty.");
        }
        Subject subject = subjectService.findByCodeOrName(null, subjectName);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Subject>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Object> deleteById(@PathVariable("subjectId") UUID subjectId) {
        Subject subject = subjectService.findById(subjectId);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
        }
        subjectService.deleteById(subjectId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Subject deleted successfully.");
    }
}
