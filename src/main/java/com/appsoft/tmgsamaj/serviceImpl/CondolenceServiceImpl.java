package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.Condolence;
import com.appsoft.tmgsamaj.repository.CondolenceRepository;
import com.appsoft.tmgsamaj.service.CondolenceService;



@Service
public class CondolenceServiceImpl implements CondolenceService {
	@Autowired
	CondolenceRepository condolenceRepository;
		@Override
		public void addCondolence(Condolence condolence) {
			// TODO Auto-generated method stub
			condolenceRepository.save(condolence);
		}

		@Override
		public List<Condolence> getAllCondolence() {
			// TODO Auto-generated method stub
			return condolenceRepository.findAll();
		}

		@Override
		public void deleteCondolenceById(int id) {
			// TODO Auto-generated method stub
			condolenceRepository.deleteById(id);
		}

		@Override
		public Condolence getCondolenceById(int id) {
			// TODO Auto-generated method stub
			return condolenceRepository.findById(id).get();
		}

		@Override
		public void updateCondolence(Condolence condolence) {
			// TODO Auto-generated method stub
			condolenceRepository.save(condolence);
		}

}
