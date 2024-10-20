package com.paperless.repository;

import com.paperless.model.PlConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlConfigRepository extends JpaRepository<PlConfig,Long> {
    PlConfig findByConfigIdAndIsDeletedFalse(String configId);
}
