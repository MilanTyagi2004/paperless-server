package com.paperless.repository;

import com.paperless.model.OutboundAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundAuditRepository extends JpaRepository<OutboundAudit,Long> {
}
