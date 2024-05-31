package com.lux.controllers.v1.user;

import java.util.*;

import com.lux.dto.AuthDTO;
import com.lux.dto.InternshipRegisterDTO;
import com.lux.dto.UserDTO;
import com.lux.models.internship.InternshipRegistration;
import com.lux.models.internship.Internships;
import com.lux.models.user.Users;
import com.lux.repositories.InternshipRegisterRepository;
import com.lux.repositories.InternshipRepository;
import com.lux.repositories.UserRepository;
import com.lux.services.AuthService;
import com.lux.services.InternshipRegisterService;
import com.lux.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.lux.exceptions.ResourceNotFoundException;



@Api(value = "Internship Management Systems", tags = "Пользователи")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private InternshipRegisterService internshipRegisterService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private InternshipRegisterRepository internshipRegisterRepository;

    // get all Internships
    @ApiOperation(value = "Get list of all Users")
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Get an User by its ID")
    @GetMapping("/user-info")
    public ResponseEntity<Users> getUserById() {
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :"
                        + authentication.getName()));
        return ResponseEntity.ok(user);
    }

//    @ApiOperation(value = "Get archive internships")
//    @GetMapping("/internship-archive")
//    public List<Internship> getArchiveInternships() {
//        return internshipService.getFindAllByStatusIn(Arrays.asList("закрыт", "отменен"));
//    }

    // create Internship rest api
    @ApiOperation(value = "Create a new user")
    @PostMapping("/register")
    public AuthDTO createUser(@RequestBody UserDTO userDTO) {
        Users user = userService.toEntity(userDTO); // Сначала в Entity
        userRepository.save(user);
        return authService.createAuth(user.getUsername(), user.getId());
    }

    // get Internship by id rest api

    @PutMapping("/update-user")
    public ResponseEntity<Users> updateUser(@RequestBody UserDTO userDTO){
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users existingUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with usernsmr :"
                        + authentication.getName()));

        // Обновляем существующий объект Internship данными из InternshipDTO
        userService.updateUserFromDTO(existingUser, userDTO);
        Users updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Map<String, Boolean>> deleteUser(){
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with username :"
                        + authentication.getName()));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Get an User by its ID")
    @GetMapping("/internship-registration/{internship_id}")
    public ResponseEntity<InternshipRegisterDTO> getUserInternshipRegister(@PathVariable Long internship_id) {
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with name :"
                        + authentication.getName()));

        Internships internship = internshipRepository.findById(internship_id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not exist with id :" + internship_id));

        InternshipRegistration internshipRegistration = new InternshipRegistration();
        if (!internshipRegisterService.checkingUserRegistrationForInternship(
                user.getId(), internship.getId())){
            internshipRegistration.setUser(user);
            internshipRegistration.setInternship(internship);
        }else {
            // убрать такие ошибки и поставить нормальные
            throw new ResourceNotFoundException("Пользователь уже зарегистрирован на стажировку");
        }
        internshipRegisterRepository.save(internshipRegistration);
        InternshipRegisterDTO internshipRegisterDTO = new InternshipRegisterDTO(
                internship_id,
                internshipRegistration.getRegistrationDate()
        );
        return ResponseEntity.ok(internshipRegisterDTO);
    }


    @ApiOperation(value = "Get an Users")
    @GetMapping("/internship-registrations")
    public ResponseEntity<List<InternshipRegisterDTO>> getUsersInternshipRegister() {
        // Получаем объект Authentication из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with name :"
                        + authentication.getName()));
        List<InternshipRegistration> internshipRegistrations = internshipRegisterRepository.findByUserId(user.getId());

        List<InternshipRegisterDTO> dtos = new ArrayList<>();
        for (InternshipRegistration registration : internshipRegistrations) {
            dtos.add(internshipRegisterService.convertToDTO(registration));
        }
        return ResponseEntity.ok(dtos);
    }

    // нужно сделать разделение на активные и архивные стажировки, добавить в таблицу поле status
    // также нужно что-то сделать с комментариями для тасок и успеваемостью

}
