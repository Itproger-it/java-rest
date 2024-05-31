package com.lux.repositories;

import com.lux.models.internship.InternshipRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternshipRegisterRepository extends JpaRepository<InternshipRegistration, Long> {
    Optional<InternshipRegistration> findByUserIdAndInternshipId(Long userId, Long internshipId);

    List<InternshipRegistration> findByUserId(Long userId);


}
