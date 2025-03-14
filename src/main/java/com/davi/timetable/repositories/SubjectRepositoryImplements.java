package com.davi.timetable.repositories;

import com.davi.timetable.entities.Subject;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImplements extends AbstractRepository<Subject, Long> implements SubjectRepository {

    @Override
    public Subject findByCodeOrName(String subjectCode, String subjectName) {
        try {
            return getEntityManager().createQuery(
                    "SELECT d FROM Subject d WHERE d.subjectCode = '" + subjectCode + "'" + " OR d.subjectName = '" + subjectName + "'", Subject.class
            ).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
