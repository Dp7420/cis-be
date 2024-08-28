package com.example.jwt.security.Security_JWT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jwt.security.Security_JWT.entity.Services;
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>{
	@Query("FROM Services s WHERE s.category.id = :categoryId")
    List<Services> findServicesByCategoryId(@Param("categoryId") Long categoryId);
	
	@Modifying
	@Query("DELETE FROM Services s WHERE s.category.categoryId=:categoryId")
	void deleteByCategoryId(@Param("categoryId") Long categoryId);
}
