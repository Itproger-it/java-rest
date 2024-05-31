package com.lux.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Модель регистрации на стажировку")
public class InternshipRegisterDTO {

    @ApiModelProperty(value = "internshipId", required = true)
    @NotNull
    private Long internshipId;

    @ApiModelProperty(value = "registrationDate", required = true)
    @NotNull
    private Date registrationDate;
}
