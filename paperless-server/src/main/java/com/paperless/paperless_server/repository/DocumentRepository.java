package com.paperless.paperless_server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paperless.paperless_server.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // JpaRepository already provides basic CRUD methods, so no need to define additional ones unless required.
}

