package com.example.jwt.security.Security_JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.security.Security_JWT.entity.Categories;
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>{
//	@Query("FROM Categories category JOIN FETCH category.company c WHERE c.companyId = :companyId")
//	List<Categories> findByCompanyId(@Param("companyId") Long companyId);
}