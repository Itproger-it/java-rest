package com.lux.services;

import com.lux.dto.InternshipRegisterDTO;
import com.lux.models.internship.InternshipRegistration;
import com.lux.repositories.InternshipRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternshipRegisterService {
    @Autowired
    private InternshipRegisterRepository internshipRegisterRepository;

    public boolean checkingUserRegistrationForInternship(Long user_id, Long internship_id) {
        // Проверяем, зарегистрирован ли пользователь на стажировку
        Optional<InternshipRegistration> registration = internshipRegisterRepository.findByUserIdAndInternshipId(
                user_id, internship_id);

        return registration.isPresent();
    }

    public InternshipRegisterDTO convertToDTO(InternshipRegistration registration) {
        InternshipRegisterDTO dto = new InternshipRegisterDTO();
        dto.setInternshipId(registration.getInternship().getId());
        dto.setRegistrationDate(registration.getRegistrationDate());
        return dto;
    }
}
