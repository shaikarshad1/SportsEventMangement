package com.pod3.participation.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pod3.participation.model.Player;

@FeignClient(name="player", url="http://43.204.36.142:8082")
public interface PlayerFeign {
	@GetMapping("/players/{id}")
	public Player getPlayer(@PathVariable("id") int id,@RequestHeader("Authorization") String token);
	@PostMapping("/addPlayer")
	public Player addPlayer(@RequestBody Player player,@RequestHeader("Authorization") String token);
}
