package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.Donor;
import com.appsoft.tmgsamaj.repository.DonorRepository;
import com.appsoft.tmgsamaj.service.DonorService;



@Service
public class DonorServiceImpl implements DonorService {
	@Autowired
	DonorRepository donorRepository;
		@Override
		public void addDonor(Donor donor) {
			// TODO Auto-generated method stub
			donorRepository.save(donor);
		}

		@Override
		public List<Donor> getAllDonor() {
			// TODO Auto-generated method stub
			return donorRepository.findAll();
		}

		@Override
		public void deleteDonorById(int id) {
			// TODO Auto-generated method stub
			donorRepository.deleteById(id);
		}

		@Override
		public Donor getDonorById(int id) {
			// TODO Auto-generated method stub
			return donorRepository.findById(id).get();
		}

		@Override
		public void updateDonor(Donor donor) {
			// TODO Auto-generated method stub
			donorRepository.save(donor);
		}

}
