package com.sports.sportsevent.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sports {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sportsId;
	private int noOfPlayers;
	private String sportsName;
	private String sportsType;
	
	public Sports() {
		super();
	}
	public Sports(long sportsId, int noOfPlayers, String sportsName, String sportsType) {
		super();
		this.sportsId = sportsId;
		this.noOfPlayers = noOfPlayers;
		this.sportsName = sportsName;
		this.sportsType = sportsType;
	}
	public long getSportsId() {
		return sportsId;
	}
	public void setSportsId(long sportsId) {
		this.sportsId = sportsId;
	}
	public int getNoOfPlayers() {
		return noOfPlayers;
	}
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
	public String getSportsType() {
		return sportsType;
	}
	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
	}

}
