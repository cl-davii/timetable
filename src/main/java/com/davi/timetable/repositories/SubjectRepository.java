package com.davi.timetable.repositories;

import com.davi.timetable.entities.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository {
    Subject save(Subject subject);
    Subject update(Subject subject);
    Subject findById(UUID subjectId);
    Subject findByCodeOrName(String subjectCode, String subjectName);
    List<Subject> findAll();
    void delete(UUID subjectId);
}
