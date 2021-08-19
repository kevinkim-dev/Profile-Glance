package com.profileglance.db.repository;

import com.profileglance.db.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findByCompanyId(String companyId);

}
