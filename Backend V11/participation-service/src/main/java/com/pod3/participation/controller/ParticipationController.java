package com.pod3.participation.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pod3.participation.feign.AuthFeign;
import com.pod3.participation.feign.EventFeign;
import com.pod3.participation.feign.PlayerFeign;
import com.pod3.participation.model.Event;
import com.pod3.participation.model.JwtResponse;
import com.pod3.participation.model.Participation;
import com.pod3.participation.model.Player;
import com.pod3.participation.model.Sports;
import com.pod3.participation.repository.ParticipationRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class ParticipationController {
	
	@Autowired
	ParticipationRepository repo;

	@Autowired
	AuthFeign authFeign;
	
	@Autowired
	PlayerFeign playerFeign;
	@Autowired
	EventFeign eventFeign;
	
	
	Logger logger=LoggerFactory.getLogger(ParticipationController.class);
	public Boolean isValid(String token) throws Exception {
		JwtResponse response=authFeign.verifyToken(token);
		if(response.isValid()) {
			log.info("Token Verified {}",token);
			return true;
		}
		else {
			logger.error("Invalid Token");
			throw new Exception("Invalid token: " + token);
		}
	}
	@GetMapping("/testPlayerMicroservice/{id}")
	public Player testPlayerMicroservice(@RequestHeader("Authorization") String token,@PathVariable("id")int id) throws Exception {
		Player player=playerFeign.getPlayer(id,token);
		return player;
		
	}
	@PostMapping("/testPlayerAdd")
	public Player testPlayerAdd(@RequestHeader("Authorization") String token,@RequestBody Player player) throws Exception {
		return playerFeign.addPlayer(player,token);
		
	}
	
	@ApiOperation(value = "Add the Participation", notes = "Required Parameters are- `Status`:`pending`, `Player_id`:`ID of the player whom you want to register`, `Event_name`:`Give the event id instead of name`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	})
	@PostMapping("/addParticipation")
	public ResponseEntity<Participation> addParticipation(@RequestHeader("Authorization") String token,@RequestBody @Valid Participation participation) throws Exception {

		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		System.out.println("ID="+participation.getParticipation_id());
		System.out.println("Status="+participation.getStatus());
		System.out.println("Player ID="+participation.getPlayer_id());
		System.out.println("Event ID="+participation.getEvent_name());
		
		
		Player player=playerFeign.getPlayer(participation.getPlayer_id(), token);
		Event event=eventFeign.getEvent(Long.parseLong(participation.getEvent_name()), token);
		List<Sports> sports=eventFeign.getSport(player.getSportsName(),token);
		System.out.println("Player="+player.getPlayerName());
		System.out.println("Event="+event.getEventId());
		Sports sport = sports.get(0);
		System.out.println("Sport="+sport.getSportsId());
		participation.setPlayer_name(player.getPlayerName());
		participation.setEvent_id((int)event.getEventId());
		participation.setEvent_name(event.getEventName());
		participation.setSports_name(player.getSportsName());
		participation.setSports_id(String.valueOf(sport.getSportsId()));
		//event=eventFeign.
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(participation));
	}
	@ApiOperation(value = "Update The Participation status By ID", notes = "Status Values are- `Approved` , `Declined` and `pending`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	})
	@PutMapping("/updateStatus/{id}/{status}")
	public ResponseEntity<Participation> updateStatus(@RequestHeader("Authorization") String token,@PathVariable("id") int id,@PathVariable("status") String status)throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		System.out.println("Updating Status: " + status);
		Participation participationStatus=repo.findById(id).get();
		participationStatus.setStatus(status);
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(participationStatus));
		
	}
	@ApiOperation(value = "Get All The Participations", notes = "Returns all the Participations of the players")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	})
	@GetMapping("/getParticipations")
	public ResponseEntity<List<Participation>> getParticipations(@RequestHeader("Authorization") String token) throws Exception {
		if(!isValid(token))
		{
			logger.trace("Invalid Token");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		logger.trace("Getting All Participations");
		List<Participation> participation= (List<Participation>) repo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(participation);
	}
	@ApiOperation(value = "Get The Participation By ID", notes = "Returns the Participation of the matching entry")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code = 404, message = "ID Not Found"),
	})
	@GetMapping("/getParticipation/{id}")
	public ResponseEntity<Participation> getParticipations(@RequestHeader("Authorization") String token,@PathVariable("id") int id)throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		logger.trace("Getting Participations by ID");
		return ResponseEntity.of(repo.findById(id));
	}
	@ApiOperation(value = "Get All The Participations By Status", notes = "Status Values are- `Approved` , `Declined` and `pending`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	})
	@GetMapping("/getApprovedParticipations/{status}")
	public ResponseEntity<List<Participation>> getApprovedParticipations(@RequestHeader("Authorization") String token,@PathVariable("status") String status)throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		logger.trace("Getting Participations Status {}",status);
		return ResponseEntity.ok(repo.findByStatus(status));
	}
	@GetMapping("/getDeclinedParticipations/{status}")
	public ResponseEntity<List<Participation>> getDeclinedParticipations(@RequestHeader("Authorization") String token,@PathVariable("status") String status)throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(repo.findByStatus(status));
	}
	@GetMapping("/getPendingParticipations{status}")
	public ResponseEntity<List<Participation>> getPendingParticipations (@RequestHeader("Authorization") String token,@PathVariable("status") String status)throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(repo.findByStatus(status));
	}
}
