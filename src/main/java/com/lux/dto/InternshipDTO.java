package com.lux.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Модель стажировки")
public class InternshipDTO {

    @ApiModelProperty(value = "Название стажировки", required = true)
    @NotNull
    private String name;

    @ApiModelProperty(value = "Описание стажировки", required = false)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Дата начала стажировки", required = true)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Дата окончания стажировки", required = true)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date endDate;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Дата окончания регистрации на стажировку", required = true)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date registrationEndDate;

    @ApiModelProperty(value = "Статус стажировки", required = true)
    @NotNull
    private String status;
}
