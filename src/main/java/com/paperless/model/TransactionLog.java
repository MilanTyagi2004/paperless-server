package com.paperless.model;

import com.paperless.common.enums.ChannelType;
import com.paperless.model.baseModal.BaseEntity;
import com.paperless.model.baseModal.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class TransactionLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private Long userId;
    private Date accessTimestamp;
    private String channelId;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    private Date transactionCreationTimestamp;

    private Date transactionExpirationTimestamp;
    private String encryptedTransactionData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getAccessTimestamp() {
        return accessTimestamp;
    }

    public void setAccessTimestamp(Date accessTimestamp) {
        this.accessTimestamp = accessTimestamp;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public Date getTransactionCreationTimestamp() {
        return transactionCreationTimestamp;
    }

    public void setTransactionCreationTimestamp(Date transactionCreationTimestamp) {
        this.transactionCreationTimestamp = transactionCreationTimestamp;
    }

    public Date getTransactionExpirationTimestamp() {
        return transactionExpirationTimestamp;
    }

    public void setTransactionExpirationTimestamp(Date transactionExpirationTimestamp) {
        this.transactionExpirationTimestamp = transactionExpirationTimestamp;
    }

    public String getEncryptedTransactionData() {
        return encryptedTransactionData;
    }

    public void setEncryptedTransactionData(String encryptedTransactionData) {
        this.encryptedTransactionData = encryptedTransactionData;
    }

}
