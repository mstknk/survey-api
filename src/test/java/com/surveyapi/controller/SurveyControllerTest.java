package com.surveyapi.controller;

import com.surveyapi.service.SurveyService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
class SurveyControllerTest {

	@MockBean
	private SurveyService surveyService;

	@Autowired
	SurveyController surveyController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Successful response with valid survey request")
	public void testThatRequestWithValidSurveyPayloadJson() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : 1, \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@DisplayName("Validation response: Name is mandatory")
	public void testThatValidationMessageRequestWithEmptyNameField() throws Exception {
		String surveyJson = "{\"name\": \"\", \"skinCondition\" : 1, \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Name is mandatory")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("name")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: Name is mandatory")
	public void testThatValidationMessageRequestWithOutNameField() throws Exception {
		String surveyJson = "{ \"skinCondition\" : 1, \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Name is mandatory")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("name")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: must be less than or equal to 10")
	public void testThatValidationMessageRequestWithSkinConditionExceedMax() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : 20, \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("must be less than or equal to 10")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("skinCondition")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: must be less than or equal to 10")
	public void testThatValidationMessageRequestWithSleepLastNightExceedMax() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : 1, \"sleepLastNight\" : 12}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("must be less than or equal to 10")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("sleepLastNight")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: must be greater than or equal to 0")
	public void testThatValidationMessageRequestWithSleepLastNightExceedMin() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : 1, \"sleepLastNight\" : -12}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("must be greater than or equal to 0")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("sleepLastNight")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: must be greater than or equal to 0")
	public void testThatValidationMessageRequestWithSkinConditionExceedMin() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : -12, \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("must be greater than or equal to 0")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("skinCondition")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: SkinCondition is mandatory")
	public void testThatValidationMessageRequestWithOutSkinCondition() throws Exception {
		String surveyJson = "{\"name\": \"mesut\",  \"sleepLastNight\" : 1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("SkinCondition is mandatory")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("skinCondition")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@DisplayName("Validation response: SleepLastNight is mandatory")
	public void testThatValidationMessageRequestWithOutSleepLastNight() throws Exception {
		String surveyJson = "{\"name\": \"mesut\", \"skinCondition\" : 2}";
		mockMvc.perform(MockMvcRequestBuilders.post("/survey/save")
				.content(surveyJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("SleepLastNight is mandatory")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.field", Is.is("sleepLastNight")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}