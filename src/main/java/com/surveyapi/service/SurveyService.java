package com.surveyapi.service;

import com.surveyapi.dao.SurveyRepository;
import com.surveyapi.db.Patient;
import com.surveyapi.db.Survey;
import com.surveyapi.model.SurveyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyService {
	Logger logger = LoggerFactory.getLogger(SurveyService.class);

	@Autowired
	private SurveyRepository surveyRepository;

	public void saveSurvey(SurveyDTO surveyDTO) {
		String patientName = surveyDTO.getName();
		Patient patient = surveyRepository.findPatientByName(patientName);

		if (patient == null) {
			patient = new Patient();
			patient.setName(patientName);
		}
		Survey survey = createSurvey(surveyDTO);

		patient.addSurvey(survey);
		surveyRepository.save(patient);

		logger.info("Survey successfully saved for patient : {}", patientName);
	}

	private Survey createSurvey(SurveyDTO surveyDTO) {
		Survey survey = new Survey();
		survey.setSkinCondition(surveyDTO.getSkinCondition());
		survey.setSleepLastNight(surveyDTO.getSleepLastNight());
		return survey;
	}
}
