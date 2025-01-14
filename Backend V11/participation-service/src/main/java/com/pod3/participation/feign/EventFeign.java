package com.pod3.participation.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pod3.participation.model.Event;
import com.pod3.participation.model.Sports;

@FeignClient(name="sportevent", url="http://65.1.181.38:8081")
public interface EventFeign {
	@GetMapping("/sports/{getSportsByName}")
	public List<Sports> getSport(@PathVariable("getSportsByName") String getSportsByName,@RequestHeader("Authorization") String token);
	@GetMapping("/event/{eventId}")
	public Event getEvent(@PathVariable("eventId") long eventId,@RequestHeader("Authorization") String token);
}
