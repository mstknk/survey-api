package com.survey.surveyapi;

import com.surveyapi.dao.SurveyRepository;
import com.surveyapi.db.Patient;
import com.surveyapi.model.SurveyDTO;
import com.surveyapi.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { com.surveyapi.SurveyApiApplication.class })
class SurveyServiceIntegrationTests {
	private static final Byte SKIN_CONDITION = 10;
	private static final Byte SLEEP_LAST_NIGHT = 10;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private SurveyRepository surveyRepository;

	@Test
	@Transactional
	void testThatSurveySavedCorrectlyOnDatabase() {
		SurveyDTO surveyDTO = new SurveyDTO();
		surveyDTO.setName("dummyName");
		surveyDTO.setSkinCondition(SKIN_CONDITION);
		surveyDTO.setSleepLastNight(SLEEP_LAST_NIGHT);
		surveyService.saveSurvey(surveyDTO);
		Patient patientDB = surveyRepository.findPatientByName("dummyName");

		assertNotNull(patientDB);
		assertThat(patientDB.getSurveyList().size(), is(1));
		assertThat(patientDB.getSurveyList().get(0).getSkinCondition(), is(SKIN_CONDITION));
		assertThat(patientDB.getSurveyList().get(0).getSleepLastNight(), is(SLEEP_LAST_NIGHT));
	}

	@Test
	@Transactional
	void testThatSurveyWithSamePatientSavedCorrectlyOnDatabase() {
		SurveyDTO surveyDTO = new SurveyDTO();
		surveyDTO.setName("mesut");
		surveyDTO.setSkinCondition(SKIN_CONDITION);
		surveyDTO.setSleepLastNight(SLEEP_LAST_NIGHT);

		surveyService.saveSurvey(surveyDTO);
		surveyService.saveSurvey(surveyDTO);

		Patient patientDB = surveyRepository.findPatientByName("mesut");

		assertNotNull(patientDB);
		assertThat(patientDB.getSurveyList().size(), is(2));
		assertThat(patientDB.getSurveyList().get(0).getSkinCondition(), is(SKIN_CONDITION));
		assertThat(patientDB.getSurveyList().get(0).getSleepLastNight(), is(SLEEP_LAST_NIGHT));
		assertThat(patientDB.getSurveyList().get(1).getSkinCondition(), is(SKIN_CONDITION));
		assertThat(patientDB.getSurveyList().get(1).getSleepLastNight(), is(SLEEP_LAST_NIGHT));
	}

}
