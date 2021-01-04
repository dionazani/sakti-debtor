package com.sakti.debtoronboarding.debtorjob;

import lombok.Data;

@Data
public class DebtorJobDto {

	private Integer id;
	private Integer debtorId;
	private Integer jobTypeId;
	private Integer corporateTypeId;
	private String corporateName;
	private String address1;
	private String address2;
	private Integer zipcodeId;
	private String rt;
	private String rw;
	private String areaPhone;
	private String phone;
	private Integer economicSectorId;
	private Integer jobRoleId;
	private String identityNumber;
	private java.util.Date joiningDate;
	private double monthlyIncome;
	private java.util.Date createdAt;
	private Integer createdUserId;
	private java.util.Date updatedAt;
	private Integer updatedUserId;
}
