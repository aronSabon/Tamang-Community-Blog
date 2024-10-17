package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.Condolence;



public interface CondolenceService {
	void addCondolence(Condolence condolence);
	List<Condolence> getAllCondolence();
	void deleteCondolenceById(int id);
	Condolence getCondolenceById(int id);
	void updateCondolence(Condolence condolence);

}
