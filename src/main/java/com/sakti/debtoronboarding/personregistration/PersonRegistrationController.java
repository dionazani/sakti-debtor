package com.sakti.debtoronboarding.personregistration;

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

import com.sakti.infrastructure.dto.ResponseDto;
import com.sakti.infrastructure.utils.FormatUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="${context-path}/v1/person-registration")
public class PersonRegistrationController {

	@Autowired
	private PersonRegistrationService personRegistrationService;
	
	@PostMapping("/ping")
	public ResponseEntity<Object> ping() {
		
		String message = "PING!";
		
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
