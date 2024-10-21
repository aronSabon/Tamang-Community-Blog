package com.appsoft.tmgsamaj.service.Front;

import java.util.List;

import com.appsoft.tmgsamaj.model.Front.HomeSlider;




public interface HomeSliderService {
	void addHomeSlider(HomeSlider homeSlider);
	List<HomeSlider> getAllHomeSlider();
	void deleteHomeSliderById(int id);
	HomeSlider getHomeSliderById(int id);
	void updateHomeSlider(HomeSlider homeSlider);

}
