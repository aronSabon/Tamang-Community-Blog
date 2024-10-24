package com.appsoft.tmgsamaj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Family {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String relation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String imageName;
	private String title;
	
}
