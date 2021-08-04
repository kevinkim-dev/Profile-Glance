package com.profileglance.db.repository;

import com.profileglance.db.entity.Recruit;
import org.hibernate.criterion.LikeExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    List<Recruit> findAllByCompany_CompanyNameContaining(String companyName);

    List<Recruit> findAllByJob_JobName(String jobName);

    List<Recruit> findAllByOrderByRecruitEndDate();

    @Transactional
    void deleteByRecruitId(Long recruitId);
}
