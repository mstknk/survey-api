package com.surveyapi.dao;

import com.surveyapi.db.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Patient, Long> {
	Patient findPatientByName(String name);
}
