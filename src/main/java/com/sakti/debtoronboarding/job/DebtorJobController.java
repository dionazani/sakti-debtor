package com.sakti.debtoronboarding.job;

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

@RestController
@RequestMapping(value="${context-path}/job")
public class DebtorJobController {

	@Autowired
	private DebtorJobService debtorJobService;
	
	@PostMapping("")
	public ResponseEntity<Object> addNew(@RequestBody DebtorJobDto dto) {
		
		ResponseDto response = null;
		ResponseEntity responseEntity = null;
		try {
			response = this.debtorJobService.addNew(dto);
			responseEntity =  ResponseEntity
	                .status(response.getHttpStatusCode())
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(response);
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			responseEntity =  ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(ex.getMessage());
		}
		
		return responseEntity;
		
	}
}
