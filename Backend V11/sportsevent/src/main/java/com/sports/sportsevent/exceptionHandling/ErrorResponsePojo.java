package com.sports.sportsevent.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponsePojo {
	private String message;
	private String title;
//	public ErrorResponsePojo(String message, String title) {
//		this.message = message;
//		this.title = title;
//	}
	
	
}