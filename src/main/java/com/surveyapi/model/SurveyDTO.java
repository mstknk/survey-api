package com.surveyapi.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SurveyDTO {

	@NotBlank(message = "Name is mandatory")
	private String name;

	@Min(0)
	@Max(10)
	@NotNull(message = "SkinCondition is mandatory")
	private Byte skinCondition;

	@Min(0)
	@Max(10)
	@NotNull(message = "SleepLastNight is mandatory")
	private Byte sleepLastNight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getSkinCondition() {
		return skinCondition;
	}

	public void setSkinCondition(Byte skinCondition) {
		this.skinCondition = skinCondition;
	}

	public Byte getSleepLastNight() {
		return sleepLastNight;
	}

	public void setSleepLastNight(Byte sleepLastNight) {
		this.sleepLastNight = sleepLastNight;
	}
}
