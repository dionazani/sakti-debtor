package com.sakti.debtoronboarding.service.personregistration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sakti.infrastructure.entity.AppRole;
import com.sakti.infrastructure.entity.AppUser;
import com.sakti.infrastructure.entity.Debtor;
import com.sakti.infrastructure.entity.DebtorPerson;
import com.sakti.infrastructure.repository.DebtorPersonRepository;
import com.sakti.infrastructure.repository.DebtorRepository;
import com.sakti.infrastructure.repository.RoleRepository;
import com.sakti.infrastructure.repository.UserRepository;
import com.sakti.infrastructure.dto.*;
import com.sakti.infrastructure.utils.*;

@Service
public class PersonrRegistrationServiceImpl implements PersonRegistrationService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DebtorRepository debtorRepository;
	
	@Autowired
	private DebtorPersonRepository debtorPersonRepository;
	
	private final static String ROLE_DEBT = "DEBT";
	private final static String DEBTOR_TYPE_PERSON = "P";
	
	@Override
	@Transactional
	public ResponseDto addNew(PersonRegistrationDto personRegistrationDto) {
		
		ResponseDto response = new ResponseDto();
		response.setResponseCode("001");
		response.setResponseMessage("");
		response.setTime(FormatUtils.getCurrentTimestampInLong());
		response.setData(null);
			
		try {
			
			// check is email is exists.
			if (this.isEmailExisting(personRegistrationDto.getEmail())) {
				response = new ResponseDto();
				response.setResponseCode("101");
				response.setResponseMessage("Email has been exist.");
				response.setTime(FormatUtils.getCurrentTimestampInLong());
				response.setData(null);
				
				return response;
			}
			
			// find DEBT from app_role.
			AppRole appRole = this.roleRepository.findByCode(ROLE_DEBT);
			if (appRole == null) {
				response.setResponseMessage("Cannot find role DEBT");
				return response;
			}
			
			long roleId = appRole.getId();
					
			// insert into app_user.
			AppUser user = new AppUser();
			user.setRoleId(roleId);
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
			debtor.setCreatedUserId(appUserId);;

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
			debtorPerson.setMobilePhone(personRegistrationDto.getMobilePhone());
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

			response.setResponseCode("000");
			response.setResponseMessage("");
			response.setTime(FormatUtils.getCurrentTimestampInLong());
			response.setData(map);
			
		}
		catch(Exception ex) {
			response.setResponseCode("002");
			response.setResponseMessage(ex.getMessage());
			response.setTime(FormatUtils.getCurrentTimestampInLong());
			response.setData(null);
			
			ex.printStackTrace();
		}
		
		return response;
	}
	
	private boolean isEmailExisting(String email) {
		
		boolean isExist = true;
		
		Debtor debtorExisting = this.debtorRepository.findByEmail(email);
		if (debtorExisting == null)
			isExist = false;
		
		return isExist;
	}

}

