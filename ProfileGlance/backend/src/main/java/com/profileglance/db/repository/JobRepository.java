package com.profileglance.db.repository;

import com.profileglance.db.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findByJobName(String job);

}
