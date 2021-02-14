package com.sakti.debtoronboarding.registration;

import java.util.Date;
import lombok.Data;

@Data
public class PersonRegistrationDto {
	
	// debtor
	private long id;
	private String email;
	private String password;
	private String userId;
	
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
	private long legalVillageId;
	private String legalPhone;
	private String mobilePhone;
	private String motherName;
	private String npwpImage;
	private String npwpNumber;
	private String phone;
	private String pob;
	private int villageId;
	private int jobRoleId;
	private String legalRtRw;
	private String rtRw;
	
}
