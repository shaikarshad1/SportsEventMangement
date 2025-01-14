package com.sports.sportsevent.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sports.sportsevent.Models.Event;
import com.sports.sportsevent.Models.JwtResponse;
import com.sports.sportsevent.Models.Sports;
import com.sports.sportsevent.Repository.eventSportRepo;
import com.sports.sportsevent.Repository.sportsEveRepo;
import com.sports.sportsevent.feign.AuthFeign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class homeController {
	
	@Autowired
	private sportsEveRepo repo;
	
	@Autowired
	private eventSportRepo everepo;
	
	@Autowired
	private AuthFeign fen;
	
	public Boolean isValid(String token) throws Exception {
		JwtResponse reponse=fen.verifyToken(token);
		if(reponse.isValid()) {
			log.info("Token Verified {}",token);
			return true;
		}else {
			throw new Exception("Invalid");
		}
	}
	@ApiOperation(value = "Add a Sport", notes = "Insert A Sport")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Added"),
	@ApiResponse(code=403,message="Forbidden")
	})
	@PostMapping("/addSports")
	public ResponseEntity<Sports> addingSports(@RequestBody Sports sport, @RequestHeader("Authorization")String token) throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(repo.save(sport));
	}
	@ApiOperation(value = "Get All Sports", notes = "Returns All Sports")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Sports Details Found")
	})
	@GetMapping("/sports")
	public ResponseEntity<List<Sports>> allSports(@RequestHeader("Authorization")String token) throws Exception{
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	@ApiOperation(value = "Get Sport By it's Name", notes = "Returns Sport Details with given Name")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Sport Details Found")
	})
	@GetMapping("/sports/{getSportsByName}")
	public ResponseEntity<List<Sports>> sportByname(@PathVariable String getSportsByName,@RequestHeader("Authorization") String token) throws Exception {
		
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(repo.findBySportsName(getSportsByName));
	}
	@ApiOperation(value = "Add Event Details", notes = "Required Parameters are- `Event ID`:`ID of the Event you want to Add`,`Event Date`:`Date at when Event happens`, `Event Name`:`Name of the Event you want to Add`, `No Of Slots`:`No Of Slots are  there For Event`,"
			+ "`Spoarts Name`:`Name of the Sport that which Event Happened on.`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Added details of Event"),
	@ApiResponse(code=404,message="Error in adding Event details to DataBase")
	})

	@PostMapping("/addEvent")
	public ResponseEntity<Event> addingEvent(@RequestBody Event eve, @RequestHeader("Authorization")String token) throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		System.out.println("Adding"+eve.getEventId());
		
		return ResponseEntity.status(HttpStatus.OK).body(everepo.save(eve));
	}
	@ApiOperation(value = "Delete Event By Given Event ID", notes = "Deletes Event Details with given ID")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Deleted"),
	@ApiResponse(code=404,message="Error in deleteing Event")
	})
	@DeleteMapping("/deleteEvent/{id}")
	public ResponseEntity<String> deleteEve(@PathVariable long id,@RequestHeader("Authorization") String token) throws Exception {
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Event eee=everepo.findById(id).orElse(null);
		everepo.delete(eee);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Event with id "+eee+" deleted successfully");
		
	}
	@ApiOperation(value = "Get All Events", notes = "Returns All Events")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Event Details Found")
	})
	@GetMapping("/events")
	public ResponseEntity<List<Event>> allEvents(@RequestHeader("Authorization") String token) throws Exception{
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(everepo.findAll());
	}

@ApiOperation(value = "Get Event By it's Name", notes = "Returns Event Details with given Name")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Event Details Found")
	})
	@GetMapping("/events/{eventsByName}")
	public ResponseEntity<List<Event>> eventByname(@PathVariable String eventsByName,@RequestHeader("Authorization") String token) throws Exception {
	
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(everepo.findByEventName(eventsByName));
	}
	@GetMapping("/event/{eventsById}")
	public ResponseEntity<Event> eventById(@PathVariable long eventsById,@RequestHeader("Authorization") String token) throws Exception {
	
		if(!isValid(token))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.of(everepo.findById(eventsById));
	}

}
