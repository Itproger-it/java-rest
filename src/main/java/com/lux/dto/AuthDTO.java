package com.lux.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Модель авторизации")
public class AuthDTO {

    @ApiModelProperty(value = "Логин", required = true)
    @NotNull
    private String login;

    @ApiModelProperty(value = "Пароль", required = true)
    @NotNull
    private String password;
}
