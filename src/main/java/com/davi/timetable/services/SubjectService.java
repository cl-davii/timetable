package com.davi.timetable.services;

import com.davi.timetable.entities.Subject;
import com.davi.timetable.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject update(Subject subject) {
        return subjectRepository.update(subject);
    }

    public Subject findById(UUID id) {
        return subjectRepository.findById(id);
    }

    public Subject findByCodeOrName(String subjectCode, String subjectName) {
        return subjectRepository.findByCodeOrName(subjectCode, subjectName);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public void deleteById(UUID id) {
        subjectRepository.delete(id);
    }
}
