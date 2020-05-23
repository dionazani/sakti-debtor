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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sakti.infrastructure.dto.*;
import com.sakti.debtoronboarding.service.personregistration.PersonRegistrationDto;
import com.sakti.debtoronboarding.service.personregistration.PersonRegistrationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api/{version}/person-registration")
public class PersonRegistrationController {

	@Autowired
	private PersonRegistrationService personRegistrationService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> addNew(@PathVariable ("version") String version,
												@RequestBody PersonRegistrationDto personRegistrationDto) {
		
		ResponseDto responseDto = null;
		if (version.toLowerCase().equals("v1"))
			responseDto = this.personRegistrationService.addNew(personRegistrationDto);

		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
		
	}
}
