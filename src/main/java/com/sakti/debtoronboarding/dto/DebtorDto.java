package com.sakti.debtoronboarding.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class DebtorDto {

	private int id;
	private Timestamp createdAt;
	private String debtorType;
	private String email;
	private Timestamp updatedAt;
	private int userId;
}
