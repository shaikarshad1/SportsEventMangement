package com.pod3.participation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int participation_id;
	private int player_id;
	private String player_name;
	private int event_id;
	private String event_name;
	private String sports_id;
	private String sports_name;
	private String status;
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public int getParticipation_id() {
		return participation_id;
	}
	public void setParticipation_id(int participation_id) {
		this.participation_id = participation_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getSports_id() {
		return sports_id;
	}
	public void setSports_id(String sports_id) {
		this.sports_id = sports_id;
	}
	public String getSports_name() {
		return sports_name;
	}
	public void setSports_name(String sports_name) {
		this.sports_name = sports_name;
	}
	public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Participation(int participation_id, int player_id, String player_name, int event_id, String event_name,
			String sports_id, String sports_name, String status) {
		super();
		this.participation_id = participation_id;
		this.player_id = player_id;
		this.player_name = player_name;
		this.event_id = event_id;
		this.event_name = event_name;
		this.sports_id = sports_id;
		this.sports_name = sports_name;
		this.status = status;
	}
	
}
