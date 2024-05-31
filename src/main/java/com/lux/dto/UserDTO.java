package com.lux.dto;

import com.lux.validations.RussianPhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Модель пользователей")
public class UserDTO {

    private Long id;

    @ApiModelProperty(value = "Имя пользователя", required = true)
    @NotNull
    private String firstName;

    @ApiModelProperty(value = "Фамилия пользователя", required = true)
    @NotNull
    private String lastName;

    @ApiModelProperty(value = "Отчество пользователя", required = true)
    @NotNull
    private String middleName;

    @ApiModelProperty(value = "Email пользователя - (email@example.com)", required = true)
    @Email
    @NotNull
    private String email;

    @ApiModelProperty(value = "Номер телефона пользователя - (8(999)999-99-99, +79991239867)", required = true)
    @RussianPhoneNumber
    @NotNull
    private String phoneNumber;

    @ApiModelProperty(value = "Username пользователя", required = true)
    @NotNull
    private String username;

    @ApiModelProperty(value = "Telegram Id пользователя - (@example)", required = true)
    @Pattern(regexp = "^@[a-zA-Z0-9_]{5,}$", message = "Неверный формат Telegram ID")
    @NotNull
    private String telegramId;

    @ApiModelProperty(value = "О пользователе")
    private String aboutInfo;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Дата рождения пользователя (2000-10-25)", required = true)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBirth;

    @ApiModelProperty(value = "Город", required = true)
    @NotNull
    private String city;

    @ApiModelProperty(value = "Статус обучения - (студент, закончил обучение, не имею профильного образования)",
            required = true)
    @NotNull
    private String educationStatus;

    @ApiModelProperty(value = "Университет")
    @NotNull
    private String university;

    @ApiModelProperty(value = "Факультет")
    @NotNull
    private String faculty;

    @ApiModelProperty(value = "Специальность")
    @NotNull
    private String specialty;

    @ApiModelProperty(value = "Курс - (1-8)")
    @NotNull
    private Integer course;
}
