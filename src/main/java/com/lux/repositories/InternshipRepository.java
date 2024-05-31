package com.lux.repositories;

import com.lux.models.internship.Internships;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internships, Long> {
    List<Internships> findAllByStatus(String status);
    List<Internships> findAllByStatusIn(List<String> statuses);


}

