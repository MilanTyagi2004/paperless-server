package com.paperless.repository;

import com.paperless.model.InfoDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoDocumentsRepository extends JpaRepository<InfoDocuments,Long> {
    Optional<InfoDocuments> findFirstByDocTypeAndIsDeletedFalse(String docType);

}
