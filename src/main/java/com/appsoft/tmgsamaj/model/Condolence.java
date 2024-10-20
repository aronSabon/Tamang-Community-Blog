package com.appsoft.tmgsamaj.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Condolence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birth;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate death;
	private String description;
	private String imageName;

}
