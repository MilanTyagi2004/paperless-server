package com.paperless.repository;

import com.paperless.model.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog,Long> {
    TransactionLog findByTransactionExpirationTimestampAndIsDeletedFalseAndChannelId(Date transactionExpirationTimestamp, String channelId);

    TransactionLog findByEncryptedTransactionDataAndIsDeletedFalse(String token);
}
