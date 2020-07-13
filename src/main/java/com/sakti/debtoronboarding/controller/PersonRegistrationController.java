package com.sakti.debtoronboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakti.debtoronboarding.service.personregistration.PersonRegistrationDto;
import com.sakti.debtoronboarding.service.personregistration.PersonRegistrationService;
import com.sakti.infrastructure.dto.ResponseDto;
import com.sakti.infrastructure.utils.FormatUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="${context-path}/{version}/person-registration")
public class PersonRegistrationController {

	@Autowired
	private PersonRegistrationService personRegistrationService;
	
	@PostMapping("/ping")
	public ResponseEntity<Object> ping(@PathVariable ("version") String version) {
		
		String message = "PING!";
		
		if (version.toUpperCase().equals("V2")) {
			message = "PING v2!!";
		}
		
		ResponseDto response = new ResponseDto();
		response.setTimestamp(FormatUtils.getCurrentTimestamp());
		response.setResponseCode("999");
		response.setResponseMessage(message);
		
		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response); 
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addNew(@PathVariable ("version") String version,
												@RequestBody PersonRegistrationDto personRegistrationDto) {
		
		ResponseDto response = null;
		try {
			if (version.toLowerCase().equals("v1"))
				response = this.personRegistrationService.addNew(personRegistrationDto);

			return ResponseEntity
	                .status(HttpStatus.OK)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(response);
		}
		catch(Exception ex) {
			
			response = new ResponseDto();
			response.setResponseCode("002");
			response.setResponseMessage(ex.getMessage());
			response.setTimestamp(FormatUtils.getCurrentTimestamp());
			response.setData(null);
			
			return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(response);
		}
		
	}
}
