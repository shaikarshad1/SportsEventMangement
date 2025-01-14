package com.pod3.player.Controller;

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

import com.pod3.player.Model.JwtResponse;
import com.pod3.player.Model.Player;
import com.pod3.player.Repository.playerRepo;
import com.pod3.player.feign.AuthFeign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class playerController {
	
	@Autowired
	private playerRepo repo;
	
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
	
	@ApiOperation(value = "Get All Players", notes = "Returns All Players")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Player Details Found")
	})
	@GetMapping("/players")
	public ResponseEntity<List<Player>> allPlayer(@RequestHeader("Authorization") String token) throws Exception{
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	@ApiOperation(value = "Get Player By ID", notes = "Returns Player Details with given ID")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully retrieved"),
	@ApiResponse(code=404,message="No Player Details Found")
	})
	@GetMapping("/players/{id}")
	public ResponseEntity<Player> findPlayerById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws Exception{
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.of(repo.findById(id));
	}
	@ApiOperation(value = "Add Player Details", notes = "Required Parameters are- `Player ID`:`ID of the player whom you want to register`, `Player Name`:`Name of the player whom you want to register`, `Player Age`:`Age of the player whom you want to register`,"
			+ "`Contact Number`:`Contact Number of the player whom you want to register`,`Email`:`Email of the player whom you want to register`,`Player Gender`:`Gender of the player whom you want to register`,`Spoarts Name`:`Name of the Sports Player wants to register`")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Added details of Player"),
	@ApiResponse(code=404,message="Error in adding details to DataBase")
	})
	@PostMapping("/addPlayer")
	public ResponseEntity<Player> addplayer(@RequestHeader("Authorization") String token,@RequestBody Player p) throws Exception {
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		repo.save(p);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	@ApiOperation(value = "Delete Player By Given ID", notes = "Deletes Player Details with given ID")
	@ApiResponses(value={
	@ApiResponse(code = 200, message = "Successfully Deleted"),
	@ApiResponse(code=404,message="Error in deleteing Player")
	})
	@DeleteMapping("/deletePlayer/{id}")
	public ResponseEntity<String> deleteplay(@PathVariable("id") int id, @RequestHeader("Authorization")String token) throws Exception {
		if(!isValid(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Player pp=repo.findById(id).orElse(null);
		repo.delete(pp);
		return ResponseEntity.status(HttpStatus.OK).body(id+" removed successfully");
		
	}

}
