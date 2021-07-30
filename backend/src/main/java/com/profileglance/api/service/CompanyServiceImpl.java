package com.profileglance.api.service;

import com.profileglance.db.entity.Company;
import com.profileglance.db.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Company createCompany(Company company) {

        return companyRepository.save(Company.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .companyEmail(company.getCompanyEmail())
                .companyPassword(passwordEncoder.encode(company.getCompanyPassword()))
                .companyPhone(company.getCompanyPhone())
//                .companyImg(company.getCompanyImg())
                .build()
        );
    }

}
