package com.davi.timetable.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subjectId;

    @Column(name = "subject_code", nullable = false, unique = true)
    private String subjectCode;
    @Column(name = "subject_name", nullable = false, unique = true)
    private String subjectName;
}