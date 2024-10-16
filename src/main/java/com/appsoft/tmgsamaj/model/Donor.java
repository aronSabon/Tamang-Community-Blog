package com.appsoft.tmgsamaj.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Donor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String contact;
	private String address;
	private String description;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	private int amount;
	private String imageName;

}
