package com.sakti.debtoronboarding.dto;

import java.util.Date;
import lombok.Data;

@Data
public class DebtorPersonDto {

	private int debtorId;
	private String address1;
	private String address2;
	private String areaPhone;
	private Date dob;
	private String fullName;
	private String gender;
	private String identityImage;
	private String identityNumber;
	private String identityType;
	private Boolean isResSameLegal;
	private String legalAddress1;
	private String legalAddress2;
	private String legalAreaPhone;
	private String legalPhone;
	private String mobilePhone;
	private String motherName;
	private String npwpImage;
	private String npwpNumber;
	private String phone;
	private String pob;
	private int zipcodeId;
	
}
