package com.profileglance.db.repository;

import com.profileglance.db.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    List<Recruit> findAllByCompany_CompanyName(String companyName);

    List<Recruit> findAllByJob_JobName(String jobName);

}
