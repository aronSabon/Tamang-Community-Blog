package com.skytest.service;

import java.util.List;

import com.skytest.model.Donor;



public interface DonorService {
	void addDonor(Donor donor);
	List<Donor> getAllDonor();
	void deleteDonorById(int id);
	Donor getDonorById(int id);
	void updateDonor(Donor donor);

}
