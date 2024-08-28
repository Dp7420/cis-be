package com.example.jwt.security.Security_JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.security.Security_JWT.entity.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
