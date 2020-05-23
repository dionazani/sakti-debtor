package com.sakti.debtoronboarding.service.personregistration;

import com.sakti.infrastructure.dto.*;

public interface PersonRegistrationService {

	ResponseDto addNew(PersonRegistrationDto registrationRequestDto);
}
