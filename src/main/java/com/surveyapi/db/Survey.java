package com.surveyapi.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "survey")
public class Survey implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "skin_condition")
	private byte skinCondition;

	@Column(name = "sleep_last_night")
	private byte sleepLastNight;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@Column(name = "creation_date")
	private Date creation;

	@PrePersist
	protected void onCreate() {
		creation = new Date();
	}

	public Long getId() {
		return id;
	}

	public byte getSkinCondition() {
		return skinCondition;
	}

	public void setSkinCondition(byte skinCondition) {
		this.skinCondition = skinCondition;
	}

	public byte getSleepLastNight() {
		return sleepLastNight;
	}

	public void setSleepLastNight(byte sleepLastNight) {
		this.sleepLastNight = sleepLastNight;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getCreation() {
		return creation;
	}
}
