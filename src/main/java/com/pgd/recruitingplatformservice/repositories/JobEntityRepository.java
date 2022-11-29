package com.pgd.recruitingplatformservice.repositories;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobEntityRepository extends JpaRepository<JobEntity, Long>, JpaSpecificationExecutor<JobEntity> {

    List<JobEntity> findAll(Specification<JobEntity> spc);
}
