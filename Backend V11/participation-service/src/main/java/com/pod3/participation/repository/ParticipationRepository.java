package com.pod3.participation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pod3.participation.model.Participation;

public interface ParticipationRepository extends CrudRepository<Participation,Integer> {
	List<Participation> findByStatus(String val);
}
