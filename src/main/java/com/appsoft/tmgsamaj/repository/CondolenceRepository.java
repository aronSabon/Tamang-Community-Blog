package com.appsoft.tmgsamaj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appsoft.tmgsamaj.model.Condolence;
import com.appsoft.tmgsamaj.model.Event;

public interface CondolenceRepository extends JpaRepository<Condolence, Integer>{

	@Query(value="SELECT * FROM Condolence ORDER BY Death DESC LIMIT 3",nativeQuery = true)
	List<Condolence> findRecentCondolenceByStatus();
}
