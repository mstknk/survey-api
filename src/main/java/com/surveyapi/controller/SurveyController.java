package com.surveyapi.controller;

import com.surveyapi.model.SurveyDTO;
import com.surveyapi.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@PostMapping("/survey/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody @Valid SurveyDTO surveyDTO) {
		surveyService.saveSurvey(surveyDTO);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put("field", fieldName);
			errors.put("message", errorMessage);
		});

		return errors;
	}
}
