package com.sakti.debtoronboarding.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakti.infrastructure.dto.ResponseDto;
import com.sakti.infrastructure.utils.FormatUtils;

@RestController
@RequestMapping(value="${context-path}/registration")
public class PersonRegistrationController {

	@Autowired
	private PersonRegistrationService personRegistrationService;
	
	@PostMapping("")
	public ResponseEntity<Object> addNew(@RequestBody PersonRegistrationDto personRegistrationDto) {
		
		ResponseDto response = null;
		ResponseEntity responseEntity = null;
		try {
			response = this.personRegistrationService.addNew(personRegistrationDto);
			responseEntity =  ResponseEntity
	                .status(response.getHttpStatusCode())
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(response);
		}
		catch(Exception ex) {
			
			responseEntity =  ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(ex.getMessage());
		}
		
		return responseEntity;
		
	}
}
