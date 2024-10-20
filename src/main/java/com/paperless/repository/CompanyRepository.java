package com.paperless.repository;

import com.paperless.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByEmailId(String emailId);

    Page<Company> findByRole(String role, String search, Pageable pageable);

    Page<Company> findByRoleAndCompanyNameContainingIgnoreCase(String role, String companyName, Pageable pageable);

    Page<Company> findByCompanyNameContainingIgnoreCase(String companyName, Pageable pageable);

    Optional<Company> findByEmailIdAndContactNumber(String emailId, String contactNumber);

    Optional<Company> findByEmailIdOrContactNumber(String emailId, String contactNumber);


    @Query("SELECT c FROM Company c WHERE c.role = :role AND " +
            "(LOWER(c.companyName) LIKE LOWER(CONCAT('%', :target, '%')) " +
            "OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :target, '%')))")
    Page<Company> findByRoleAndNameOrFirstNameContainingIgnoreCase(
            @Param("role") String role,
            @Param("target") String target,
            Pageable pageable);

}
