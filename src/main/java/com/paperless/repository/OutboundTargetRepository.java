package com.paperless.repository;

import com.paperless.model.OutboundTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundTargetRepository extends JpaRepository<OutboundTarget,Long> {
    OutboundTarget findByTargetNameAndIsDeletedFalse(String parkingSpotService);

    OutboundTarget findByTargetNameAndTargetContextAndIsDeletedFalse(String targetName, String targetContext);
}
