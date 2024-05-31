package com.lux.models.internship;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import com.lux.models.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "internship_registrations", schema = "public")
public class InternshipRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registration_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date registrationDate;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "internship_id", referencedColumnName = "id", nullable = false)
    private Internships internship;
}

