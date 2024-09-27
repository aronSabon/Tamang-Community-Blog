package com.skytest.model;

import java.time.LocalDate;
import java.util.List;

import com.skytest.constants.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String location;
	private LocalDate date;
	private String time;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Guest> guest;
	@Enumerated(EnumType.STRING)
	private EventStatus status;

}
