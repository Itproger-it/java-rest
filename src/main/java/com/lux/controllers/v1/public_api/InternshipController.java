package com.lux.controllers.v1.public_api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lux.dto.InternshipDTO;
import com.lux.repositories.UserRepository;
import com.lux.services.InternshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.lux.exceptions.ResourceNotFoundException;
import com.lux.models.internship.Internships;
import com.lux.repositories.InternshipRepository;


@Api(value = "Internship Management System", tags = "Стажировки")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private UserRepository userRepository;

    // get all Internships
    @ApiOperation(value = "Get list of all Internships")
    @GetMapping("/internships")
    public List<Internships> getAllInternships(){
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Получаем логин аутентифицированного пользователя
        System.out.println("*********************************" + authentication.getName());
        return internshipService.getFindAllByStatus();
    }

    @ApiOperation(value = "Get an Internship by its ID")
    @GetMapping("/internships/{id}")
    public ResponseEntity<Internships> getInternshipById(@PathVariable Long id) {
        Internships internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not exist with id :" + id));
        return ResponseEntity.ok(internship);
    }

    @ApiOperation(value = "Get archive internships")
    @GetMapping("/internship-archive")
    public List<Internships> getArchiveInternships() {
        return internshipService.getFindAllByStatusIn(Arrays.asList("закрыт", "отменен"));
    }

    // create Internship rest api
    @ApiOperation(value = "Create a new Internship")
    @PostMapping("/internships")
    public Internships createInternship(@RequestBody InternshipDTO internshipDTO) {
        Internships internship = internshipService.toEntity(internshipDTO); // Сначала в Entity
        internshipRepository.save(internship);
        return internship;
    }

    // get Internship by id rest api

    @PutMapping("/internships/{id}")
    public ResponseEntity<Internships> updateInternship(@PathVariable Long id, @RequestBody InternshipDTO internshipDTO){
        Internships existingInternship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not exist with id :" + id));

        // Обновляем существующий объект Internship данными из InternshipDTO
        internshipService.updateInternshipFromDTO(existingInternship, internshipDTO);
        Internships updatedInternship = internshipRepository.save(existingInternship);
        return ResponseEntity.ok(updatedInternship);
    }

    @DeleteMapping("/internships/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInternship(@PathVariable Long id){
        Internships internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not exist with id :" + id));

        internshipRepository.delete(internship);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
