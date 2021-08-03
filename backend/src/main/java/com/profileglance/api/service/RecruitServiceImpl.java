package com.profileglance.api.service;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.db.entity.Company;
import com.profileglance.db.entity.Job;
import com.profileglance.db.entity.Recruit;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.JobRepository;
import com.profileglance.db.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitServiceImpl implements RecruitService{

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobRepository jobRepository;

    @Override
    public Boolean uploadRecruit(RecruitPostReq recruitPostReq, String companyId) {

        Company company = companyRepository.findByCompanyId(companyId).get();

        String jobName = recruitPostReq.getJob();

        Job job = jobRepository.findByJobName(jobName).get();

        Recruit recruit = recruitRepository.save(Recruit.builder()
                .company(company)
                .job(job)
                .descriptionURL(recruitPostReq.getDescriptionURL())
                .recruitURL(recruitPostReq.getRecruitURL())
                .career(recruitPostReq.getCareer())
                .jobDetail(recruitPostReq.getJobDetail())
                .recruitDate(recruitPostReq.getRecruitDate())
                .presentationDate(recruitPostReq.getPresentationDate())
                .build()
        );

        company.getRecruits().add(recruit);

        companyRepository.save(company);

        return true;
    }
}
