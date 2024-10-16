package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.Donor;



public interface DonorService {
	void addDonor(Donor donor);
	List<Donor> getAllDonor();
	void deleteDonorById(int id);
	Donor getDonorById(int id);
	void updateDonor(Donor donor);

}
