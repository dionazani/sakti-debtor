package com.sakti.debtoronboarding.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakti.infrastructure.dto.ResponseDto;
import com.sakti.infrastructure.entity.DebtorJob;
import com.sakti.infrastructure.repository.DebtorJobRepository;
import com.sakti.infrastructure.utils.FormatUtils;

@Service
public class DebtorJobService {

	@Autowired
	private DebtorJobRepository debtorJobRepository;
	
	public ResponseDto addNew(DebtorJobDto dto) {
		
		ResponseDto response = new ResponseDto();
		
		// insert into debtor_job.
		DebtorJob entity = new DebtorJob();
		entity.setDebtorId(dto.getDebtorId());
		entity.setJobTypeId(dto.getJobTypeId());
		entity.setCorporateTypeId(dto.getCorporateTypeId());
		entity.setCorporateName(dto.getCorporateName());
		entity.setAddress1(dto.getAddress1());
		entity.setAddress2(dto.getAddress2());
		entity.setVillageId(dto.getVillageId());
		entity.setRt(dto.getRt());
		entity.setRw(dto.getRw());
		entity.setAreaPhone(dto.getAreaPhone());
		entity.setPhone(dto.getPhone());
		entity.setEconomicSectorId(dto.getEconomicSectorId());
		entity.setJobRoleId(dto.getJobRoleId());
		entity.setIdentityNumber(dto.getIdentityNumber());
		entity.setJoiningDate(dto.getJoiningDate());
		entity.setMonthlyIncome(dto.getMonthlyIncome());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setCreatedUserId(dto.getCreatedUserId());
		this.debtorJobRepository.save(entity);
		long debtorJobId = entity.getId();
		
		// set Response.
		Map<String, String> map = new HashMap<String, String>();
		map.put("debtorJobId", String.valueOf(debtorJobId));
				
		response = new ResponseDto();
		response.setHttpStatusCode(200);
		response.setResponseCode("000");
		response.setResponseMessage("");
		response.setTimestamp(FormatUtils.getCurrentTimestamp());
		response.setData(map);
		
		return response;
	}
	
}
