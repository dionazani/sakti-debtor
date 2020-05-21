package com.sakti.debtoronboarding.service.personregistration;

import com.sakti.debtoronboarding.dto.ResponseDto;

public interface PersonRegistrationService {

	ResponseDto addNew(PersonRegistrationDto registrationRequestDto);
}
