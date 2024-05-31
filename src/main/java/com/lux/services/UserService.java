package com.lux.services;

import com.lux.dto.UserDTO;
import com.lux.models.user.Users;
import com.lux.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getFindByUsername() {
        return userRepository.findByUsername("").get();
    }

    public Users getFindByEmail() {
        return userRepository.findByEmail("").get();
    }





    // пока что не придумал для чего
//    public List<InternshipDTO> getAllOpenInternshipDTOs() {
//        List<Internship> internships = internshipRepository.findAllByStatus("открыт");
//        return internships.stream().map(this::toDTO).collect(Collectors.toList());
//    }

    public UserDTO toDTO(Users user) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setMiddleName(user.getMiddleName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUsername(user.getUsername());
        dto.setTelegramId(user.getTelegramId());
        dto.setAboutInfo(user.getAboutInfo());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setCity(user.getCity());
        dto.setEducationStatus(user.getEducationStatus());
        dto.setUniversity(user.getUniversity());
        dto.setFaculty(user.getFaculty());
        dto.setSpecialty(user.getSpecialty());
        dto.setCourse(user.getCourse());
        return dto;
    }

    public Users toEntity(UserDTO userDTO) {
        Users user = new Users();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUsername(userDTO.getUsername());
        user.setTelegramId(userDTO.getTelegramId());
        user.setAboutInfo(userDTO.getAboutInfo());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setCity(userDTO.getCity());
        user.setEducationStatus(userDTO.getEducationStatus());
        user.setUniversity(userDTO.getUniversity());
        user.setFaculty(userDTO.getFaculty());
        user.setSpecialty(userDTO.getSpecialty());
        user.setCourse(userDTO.getCourse());
        return user;
    }

    public void updateUserFromDTO(Users user, UserDTO userDTO) {
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
        if (userDTO.getMiddleName() != null) {
            user.setMiddleName(userDTO.getMiddleName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getTelegramId() != null) {
            user.setTelegramId(userDTO.getTelegramId());
        }
        if (userDTO.getAboutInfo() != null) {
            user.setAboutInfo(userDTO.getAboutInfo());
        }
        if (userDTO.getDateOfBirth() != null) {
            user.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if (userDTO.getCity() != null) {
            user.setCity(userDTO.getCity());
        }
        if (userDTO.getEducationStatus() != null) {
            user.setEducationStatus(userDTO.getEducationStatus());
        }
        if (userDTO.getUniversity() != null) {
            user.setUniversity(userDTO.getUniversity());
        }
        if (userDTO.getFaculty() != null) {
            user.setFaculty(userDTO.getFaculty());
        }
        if (userDTO.getSpecialty() != null) {
            user.setSpecialty(userDTO.getSpecialty());
        }
        if (userDTO.getCourse() != null) {
            user.setCourse(userDTO.getCourse());
        }
    }


    // Другие методы сервиса для работы с данными о стажировках
}
