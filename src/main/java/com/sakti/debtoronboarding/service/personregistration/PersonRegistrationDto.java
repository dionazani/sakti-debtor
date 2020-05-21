package com.sakti.debtoronboarding.service.personregistration;

import java.util.Date;
import lombok.Data;

@Data
public class PersonRegistrationDto {
	
	// debtor
	private long id;
	private String email;
	private String password;
	private String userId;
	private String createdUserId;
	
	//debtor_person
	private String debtorId;
	private String address1;
	private String address2;
	private String areaPhone;
	private Date dob;
	private String fullName;
	private String gender;
	private String identityImage;
	private String identityNumber;
	private String identityType;
	private int isResSameLegal;
	private String legalAddress1;
	private String legalAddress2;
	private String legalAreaPhone;
	private long legalZipcodeId;
	private String legalPhone;
	private String mobilePhone;
	private String motherName;
	private String npwpImage;
	private String npwpNumber;
	private String phone;
	private String pob;
	private long zipcodeId;
}
