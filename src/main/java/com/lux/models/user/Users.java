package com.lux.models.user;

import com.lux.validations.RussianPhoneNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"users\"")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя должно содержать только буквы")
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Фамилия должна содержать только буквы")
    @NotNull
    private String lastName;

    @Column(name = "middle_name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Отчество должно содержать только буквы")
    @NotNull
    private String middleName;

    @Column(nullable = false, unique = true, length = 255)
    @Email(message = "Не корректный формат email")
    @NotNull
    private String email;

//    @Pattern(regexp = "^\\+?\\d{1,3}[- ]?\\d{3,4}[- ]?\\d{6,8}$", message = "Неверный формат номера телефона")
    @Column(name = "phone_number", nullable = false, unique = true, length = 20)
    @RussianPhoneNumber(message = "Неверный формат российского номера телефона")
    @NotNull
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 255)
    @NotNull
    private String username;

    @Column(name = "telegram_id", nullable = false, length = 255)
    @Pattern(regexp = "^@[a-zA-Z0-9_]{5,}$", message = "Неверный формат Telegram ID")
    @NotNull
    private String telegramId;

    @Column(name = "about_info", columnDefinition = "TEXT")
    private String aboutInfo;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBirth;

    @Column(nullable = false, length = 50)
    @NotNull
    private String city;

    @Column(name = "education_status",
            columnDefinition = "VARCHAR(50) NOT NULL CHECK (education_status IN ('студент', 'закончил обучение', 'не имею профильного образования'))")
    @NotNull
    private String educationStatus;

    @Column(length = 150)
    private String university;

    @Column(length = 150)
    private String faculty;

    @Column(length = 150)
    private String specialty;

    @Column(columnDefinition = "INT CHECK (course <= 6 AND course > 0)")
    private Integer course;
}
