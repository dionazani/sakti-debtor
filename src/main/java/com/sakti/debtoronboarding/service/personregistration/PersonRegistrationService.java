package com.sakti.debtoronboarding.service.personregistration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sakti.infrastructure.dto.*;
import com.sakti.infrastructure.entity.AppRole;
import com.sakti.infrastructure.entity.AppUser;
import com.sakti.infrastructure.entity.Debtor;
import com.sakti.infrastructure.entity.DebtorPerson;
import com.sakti.infrastructure.repository.DebtorPersonRepository;
import com.sakti.infrastructure.repository.DebtorRepository;
import com.sakti.infrastructure.repository.RoleRepository;
import com.sakti.infrastructure.repository.AppUserRepository;
import com.sakti.infrastructure.utils.*;

@Service
@Transactional
public class PersonRegistrationService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AppUserRepository userRepository;
	
	@Autowired
	private DebtorRepository debtorRepository;
	
	@Autowired
	private DebtorPersonRepository debtorPersonRepository;
	
	private final static String ROLE_DEBT = "DEBT";
	private final static String DEBTOR_TYPE_PERSON = "P";
	private final static short USER_TYPE_USER = 2;
	
	@Transactional
	public ResponseDto addNew(PersonRegistrationDto personRegistrationDto) throws Exception {
		
		ResponseDto response = new ResponseDto();
		
		response = this.debtorValidation(personRegistrationDto);
		if (response != null) {
			return response;
		}
		
		// find DEBT from app_role.
		AppRole appRole = this.roleRepository.findByCode(ROLE_DEBT);
		if (appRole == null) {
			throw new Exception("Cannot find role DEBT");
		}
		
		long appRoleId = appRole.getId();

		// insert into app_user.
		AppUser user = new AppUser();
		user.setAppRoleId(appRoleId);
		user.setUserType(USER_TYPE_USER);
		user.setUsername(personRegistrationDto.getEmail());
		user.setPassword(personRegistrationDto.getPassword());
		user.setMustChangePassword(Short.valueOf("1"));
		user.setIsLock(Short.valueOf("1"));
		user.setCreatedAt(FormatUtils.getCurrentTimestamp());
		user.setRegisteredFrom("WEB");
		
		this.userRepository.save(user);
		
		long appUserId = user.getId();
		
		//insert into debtor.
		Debtor debtor = new Debtor();
		debtor.setDebtorType(DEBTOR_TYPE_PERSON);
		debtor.setEmail(personRegistrationDto.getEmail());
		debtor.setAppUserId(appUserId);
		debtor.setCreatedAt(FormatUtils.getCurrentTimestamp());
		debtor.setCreatedUserId(appUserId);
		debtor.setMobilePhone(personRegistrationDto.getMobilePhone());

		this.debtorRepository.save(debtor);
		
		long debtorId = debtor.getId();
		
		// insert into debtor_person.
		DebtorPerson debtorPerson = new DebtorPerson();
		debtorPerson.setDebtorId(debtorId);
		debtorPerson.setIdentityType(personRegistrationDto.getIdentityType());
		debtorPerson.setIdentityNumber(personRegistrationDto.getIdentityNumber());
		debtorPerson.setFullName(personRegistrationDto.getFullName());
		debtorPerson.setGender(personRegistrationDto.getGender());
		debtorPerson.setPob(personRegistrationDto.getPob());
		debtorPerson.setDob(personRegistrationDto.getDob());
		debtorPerson.setMotherName(personRegistrationDto.getMotherName());
		debtorPerson.setLegalAddress1(personRegistrationDto.getLegalAddress1());
		debtorPerson.setLegalAddress2(personRegistrationDto.getLegalAddress2());
		debtorPerson.setLegalZipCodeId(personRegistrationDto.getLegalZipcodeId());
		debtorPerson.setLegalAreaPhone(personRegistrationDto.getLegalAreaPhone());
		debtorPerson.setLegalPhone(personRegistrationDto.getLegalPhone());
		debtorPerson.setIsResSameLegal(personRegistrationDto.getIsResSameLegal());
		debtorPerson.setAddress1(personRegistrationDto.getAddress1());
		debtorPerson.setAddress2(personRegistrationDto.getAddress2());
		debtorPerson.setZipCodeId(personRegistrationDto.getZipcodeId());
		debtorPerson.setAreaPhone(personRegistrationDto.getLegalAreaPhone());
		debtorPerson.setPhone(personRegistrationDto.getLegalPhone());
		debtorPerson.setNpwpNumber(personRegistrationDto.getNpwpNumber());
		debtorPerson.setNpwpImage(personRegistrationDto.getNpwpImage());
		debtorPerson.setIdentityImage(personRegistrationDto.getIdentityImage());
		this.debtorPersonRepository.save(debtorPerson);
		
		// set Response.
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", String.valueOf(appUserId));
		map.put("debtorId", String.valueOf(debtorId));

		response = new ResponseDto();
		response.setHttpStatusCode(200);
		response.setResponseCode("000");
		response.setResponseMessage("");
		response.setTimestamp(FormatUtils.getCurrentTimestamp());
		response.setData(map);
		
		return response;
	}
	
	private ResponseDto debtorValidation(PersonRegistrationDto personRegistrationDto) {
		
		ResponseDto response = null;

		// check is mobile phone is exists.
		if (this.isMobilePhoneExists(personRegistrationDto.getMobilePhone())) {
			response = new ResponseDto();
			response.setHttpStatusCode(400);
			response.setResponseCode("101");
			response.setResponseMessage("Mobile Phone has been exist.");
			response.setTimestamp(FormatUtils.getCurrentTimestamp());
			response.setData(null);
			
			return response;
		}
				
		// check is email is exists.
		if (this.isEmailExists(personRegistrationDto.getEmail())) {
			response = new ResponseDto();
			response.setHttpStatusCode(400);
			response.setResponseCode("102");
			response.setResponseMessage("Email has been exist.");
			response.setTimestamp(FormatUtils.getCurrentTimestamp());
			response.setData(null);
			
			return response;
		}
		
		// check is identity number is exists.
		if (this.isIdentityNumberExists(personRegistrationDto.getIdentityNumber())) {
			response = new ResponseDto();
			response.setHttpStatusCode(400);
			response.setResponseCode("103");
			response.setResponseMessage("Identity Number (KTP/NIK) has been exist.");
			response.setTimestamp(FormatUtils.getCurrentTimestamp());
			response.setData(null);
			
			return response;
		}
		
		return response;
	}
	
	// check existing mobile phone
	private boolean isMobilePhoneExists(String mobilePhone) {
		
		boolean isExist = true;
		
		Debtor debtorExisting = this.debtorRepository.findByMobilePhone(mobilePhone);
		if (debtorExisting == null)
			isExist = false;
		
		return isExist;
	}

	// check existing email.
	private boolean isEmailExists(String email) {
		
		boolean isExist = true;
		
		Debtor debtorExisting = this.debtorRepository.findByEmail(email);
		if (debtorExisting == null)
			isExist = false;
		
		return isExist;
	}
	
	// check existing identity number.
	private boolean isIdentityNumberExists(String identityNumber) {
		
		boolean isExist = true;
		
		DebtorPerson debtorPersonExisting = this.debtorPersonRepository.findByIdentityNumber(identityNumber);
		if (debtorPersonExisting == null)
			isExist = false;
		
		return isExist;
	}

}

