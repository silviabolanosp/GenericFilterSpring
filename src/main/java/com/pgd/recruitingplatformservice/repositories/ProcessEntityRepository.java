package com.pgd.recruitingplatformservice.repositories;

import com.pgd.recruitingplatformservice.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProcessEntityRepository extends JpaRepository<ProcessEntity, LocalDate> {
}
