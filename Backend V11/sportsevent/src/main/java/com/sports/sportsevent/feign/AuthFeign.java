package com.sports.sportsevent.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sports.sportsevent.Models.JwtResponse;


@FeignClient(name="authorization-service", url="http://3.108.66.245:8084/api/auth")
public interface AuthFeign {
	@GetMapping("/validate")
	public JwtResponse verifyToken(@RequestHeader("Authorization") String token);

}

