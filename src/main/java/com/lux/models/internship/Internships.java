package com.lux.models.internship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "internships")
//@ApiModel(description = "Модель стажировки")
public class Internships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    //@ApiModelProperty(value = "Описание стажировки", required = false, hidden = true)
    private String description;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "registration_end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationEndDate;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL CHECK (status IN ('открыт', 'закрыт', 'отменен'))")
    private String status; // Here you can use the status field for internship status (e.g., "open", "closed", "cancelled")

    // Constructor, getters, setters, and other methods
}
