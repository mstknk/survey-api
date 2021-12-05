package com.surveyapi.service;

import com.surveyapi.dao.SurveyRepository;
import com.surveyapi.db.Patient;
import com.surveyapi.model.SurveyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
	public static final String NAME = "name";

	@Mock
	private static SurveyRepository surveyRepository;

	@InjectMocks
	private SurveyService surveyService;

	@Mock
	private static SurveyDTO surveyDTO;

	@Mock
	private static Patient patient;

	@Test
	void testThatSurveySaveSuccessful() {

		when(surveyDTO.getName()).thenReturn(NAME);
		when(surveyRepository.findPatientByName(NAME)).thenReturn(patient);

		surveyService.saveSurvey(surveyDTO);
		verify(surveyRepository).save(patient);

	}
}