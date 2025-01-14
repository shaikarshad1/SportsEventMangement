package com.sports.sportsevent.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.sportsevent.Models.Event;
import com.sports.sportsevent.Models.Sports;

public interface sportsEveRepo extends JpaRepository<Sports,Long> {

	void save(Event eve);
	
	List<Sports> findBySportsName(String sportname);

}
