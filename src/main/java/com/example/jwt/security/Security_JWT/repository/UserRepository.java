package com.example.jwt.security.Security_JWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jwt.security.Security_JWT.entity.Role;
import com.example.jwt.security.Security_JWT.entity.Status;
import com.example.jwt.security.Security_JWT.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);

	Boolean existsByUserName(String userName);

	Boolean existsByMobile(Long mobile);

	Boolean existsByEmail(String email);
  
	List<User> findByRole(Role role);
	
	Optional<User> findByRoleAndStatus(String role, Status status);
	
	@Query("FROM User user JOIN FETCH user.company c WHERE c.companyId = :companyId")
	List<User> findByCompanyId(@Param("companyId") Long companyId);
}
