package com.davi.timetable.repositories;

import com.davi.timetable.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SubjectRepository extends AbstractRepository<Subject, UUID> implements SubjectRepository {


}
