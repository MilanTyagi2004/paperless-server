package com.paperless.repository;

import com.paperless.model.S3FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface S3MetadataRepository extends JpaRepository<S3FileMetadata,Long> {
    S3FileMetadata findByOriginalFileNameAndIsDeletedIsFalse(String originalFilename);

    Long countByCompanyIdAndIsDeletedFalse(Long id);

    S3FileMetadata findByFileIdAndIsDeletedFalse(Long fileId);

    List<S3FileMetadata> findByFileIdInAndIsDeletedFalse(Collection<Long> fileIds);
}
