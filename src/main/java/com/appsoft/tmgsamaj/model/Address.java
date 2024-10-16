package com.appsoft.tmgsamaj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	private String houseNumberOrName;
	private String street;
	private String locality;
	private String town;
	private String country;
	private String postalCode;
	

}
