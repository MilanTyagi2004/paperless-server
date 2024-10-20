package com.paperless.repository;

import com.paperless.model.Company;
import com.paperless.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByEmailIdAndPassword(String username,String password);

    Optional<UserInfo> findByEmailId(String emailId);
    Optional<UserInfo> findByUsername(String username);

    Page<UserInfo> findByRoleAndUsernameContainingIgnoreCase(String role, String username, Pageable pageable);

    Page<UserInfo> findByRole(String role, String search, Pageable pageable);

    Page<UserInfo> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    Set<UserInfo> findByCompany(Company company);

    boolean existsByEmailId(String email);

    String findByRoleName(String name);
    @Query("SELECT u FROM UserInfo u JOIN u.role r WHERE u.company.id = :companyId AND r.name IN :roles")
    List<UserInfo> findByCompanyAndRoleIn(@Param("companyId") Long companyId, @Param("roles") List<String> roles, Pageable pageable);

    @Query("SELECT u FROM UserInfo u JOIN u.role r WHERE u.company.id = :companyId AND r.name IN :roles AND (u.firstName LIKE %:search% OR u.lastName LIKE %:search%)")
    List<UserInfo> findByCompanyAndRoleIn(@Param("companyId") Long companyId, @Param("roles") List<String> roles, @Param("search") String search, Pageable pageable);

    Set<UserInfo> findByCompany_IdAndActiveTrue(long id);

    @Query("SELECT u FROM UserInfo u JOIN u.role r WHERE r.name IN :roles")
    List<UserInfo> findByRoleIn(List<String> roles, Pageable pageable);

    List<UserInfo> findByAndRoleIn(List<String> roles, String search, Pageable pageable);

    Optional<UserInfo> findByEmailIdAndId(String email, Long userId);

    Optional<UserInfo> findByIdAndActiveTrue(Long userId);

    @Query(value = "SELECT * FROM pl_master.users WHERE role_id IN (:roleIds)", nativeQuery = true)
    List<UserInfo> findUsersByRoleIds(@Param("roleIds") List<Long> roleIds);

}
