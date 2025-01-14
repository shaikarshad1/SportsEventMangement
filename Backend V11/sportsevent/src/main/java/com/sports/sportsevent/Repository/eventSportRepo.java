package com.sports.sportsevent.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.sportsevent.Models.Event;

public interface eventSportRepo extends JpaRepository<Event,Long> {

	List<Event> findByEventName(String eventname);

}
