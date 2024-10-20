package com.paperless.model;

import com.paperless.model.baseModal.BaseEntity;
import jakarta.persistence.*;
@Entity
@Table(name = "OUTBOUND_TARGETS")
public class OutboundTarget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "TARGET_NAME")
    private String targetName;

    @Column(name = "TARGET_CONTEXT",unique = true)
    private String targetContext;

    @Column(name = "TARGET_URL")
    private String targetUrl;

    @Column(name = "REQUEST_METHOD")
    private String requestMethod;

    @Column(name = "TARGET_AUTH_INFO")
    private String targetAuthInfo;

    @Column(name = "TARGET_TEMPLATE")
    private String targetTemplate;

    @Column(name = "TARGET_BODY")
    private String targetBody;

    @Column(name = "TARGET_PARAM")
    private String targetParam;

    // New column to store dynamic headers in JSON format
    @Column(name = "TARGET_HEADERS")
    private String targetHeaders;

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTargetName() { return targetName; }
    public void setTargetName(String targetName) { this.targetName = targetName; }

    public String getTargetContext() { return targetContext; }
    public void setTargetContext(String targetContext) { this.targetContext = targetContext; }

    public String getTargetUrl() { return targetUrl; }
    public void setTargetUrl(String targetUrl) { this.targetUrl = targetUrl; }

    public String getRequestMethod() { return requestMethod; }
    public void setRequestMethod(String requestMethod) { this.requestMethod = requestMethod; }

    public String getTargetAuthInfo() { return targetAuthInfo; }
    public void setTargetAuthInfo(String targetAuthInfo) { this.targetAuthInfo = targetAuthInfo; }

    public String getTargetTemplate() { return targetTemplate; }
    public void setTargetTemplate(String targetTemplate) { this.targetTemplate = targetTemplate; }

    public String getTargetBody() { return targetBody; }
    public void setTargetBody(String targetBody) { this.targetBody = targetBody; }

    public String getTargetParam() { return targetParam; }
    public void setTargetParam(String targetParam) { this.targetParam = targetParam; }

    public String getTargetHeaders() { return targetHeaders; }
    public void setTargetHeaders(String targetHeaders) { this.targetHeaders = targetHeaders; }
}
