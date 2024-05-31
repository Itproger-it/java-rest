package com.lux.services;

import com.lux.dto.InternshipDTO;
import com.lux.models.internship.Internships;
import com.lux.repositories.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;

    @Autowired
    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    public List<Internships> getFindAllByStatus() {
        return internshipRepository.findAllByStatus("открыт");
    }

    public List<Internships> getFindAllByStatusIn(List<String> statuses) {
        return internshipRepository.findAllByStatusIn(statuses);
    }

    // пока что не придумал для чего
//    public List<InternshipDTO> getAllOpenInternshipDTOs() {
//        List<Internship> internships = internshipRepository.findAllByStatus("открыт");
//        return internships.stream().map(this::toDTO).collect(Collectors.toList());
//    }

    public InternshipDTO toDTO(Internships internship) {
        InternshipDTO dto = new InternshipDTO();
        dto.setName(internship.getName());
        dto.setDescription(internship.getDescription());
        dto.setStartDate(internship.getStartDate());
        dto.setEndDate(internship.getEndDate());
        dto.setRegistrationEndDate(internship.getRegistrationEndDate());
        dto.setStatus(internship.getStatus());
        return dto;
    }

    public Internships toEntity(InternshipDTO internshipDTO) {
        Internships internship = new Internships();
        internship.setName(internshipDTO.getName());
        internship.setDescription(internshipDTO.getDescription());
        internship.setStartDate(internshipDTO.getStartDate());
        internship.setEndDate(internshipDTO.getEndDate());
        internship.setRegistrationEndDate(internshipDTO.getRegistrationEndDate());
        internship.setStatus(internshipDTO.getStatus());
        return internship;
    }

    public void updateInternshipFromDTO(Internships internship, InternshipDTO internshipDTO) {
        if (internshipDTO.getName() != null) {
            internship.setName(internshipDTO.getName());
        }
        if (internshipDTO.getDescription() != null) {
            internship.setDescription(internshipDTO.getDescription());
        }
        if (internshipDTO.getStartDate() != null) {
            internship.setStartDate(internshipDTO.getStartDate());
        }
        if (internshipDTO.getEndDate() != null) {
            internship.setEndDate(internshipDTO.getEndDate());
        }
        if (internshipDTO.getRegistrationEndDate() != null) {
            internship.setRegistrationEndDate(internshipDTO.getRegistrationEndDate());
        }
        if (internshipDTO.getStatus() != null) {
            internship.setStatus(internshipDTO.getStatus());
        }
    }

    // Другие методы сервиса для работы с данными о стажировках
}
