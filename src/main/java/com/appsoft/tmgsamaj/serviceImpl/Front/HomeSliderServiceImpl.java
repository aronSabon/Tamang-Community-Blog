package com.appsoft.tmgsamaj.serviceImpl.Front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.Front.HomeSlider;
import com.appsoft.tmgsamaj.repository.Front.HomeSliderRepository;
import com.appsoft.tmgsamaj.service.Front.HomeSliderService;





@Service
public class HomeSliderServiceImpl implements HomeSliderService {
	@Autowired
	HomeSliderRepository homeSliderRepository;
		@Override
		public void addHomeSlider(HomeSlider homeSlider) {
			// TODO Auto-generated method stub
			homeSliderRepository.save(homeSlider);
		}

		@Override
		public List<HomeSlider> getAllHomeSlider() {
			// TODO Auto-generated method stub
			return homeSliderRepository.findAll();
		}

		@Override
		public void deleteHomeSliderById(int id) {
			// TODO Auto-generated method stub
			homeSliderRepository.deleteById(id);
		}

		@Override
		public HomeSlider getHomeSliderById(int id) {
			// TODO Auto-generated method stub
			return homeSliderRepository.findById(id).get();
		}

		@Override
		public void updateHomeSlider(HomeSlider homeSlider) {
			// TODO Auto-generated method stub
			homeSliderRepository.save(homeSlider);
		}

}
