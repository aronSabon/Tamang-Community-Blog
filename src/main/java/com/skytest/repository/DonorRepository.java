package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.Donor;


public interface DonorRepository extends JpaRepository<Donor, Integer> {

}
