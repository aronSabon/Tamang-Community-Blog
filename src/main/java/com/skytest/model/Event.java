package com.skytest.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.skytest.constants.EventStatus;
import com.skytest.constants.EventType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	private String time;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Guest> guest;
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	 @ElementCollection
	private List<String> imageNames;
	 @Enumerated(EnumType.STRING)
	 private EventType type;
	
	public void addImageName(String imageName) {
	    this.imageNames.add(imageName);
	}
}

