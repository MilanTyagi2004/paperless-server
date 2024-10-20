package com.paperless.model;

import com.paperless.model.baseModal.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "OUTBOUND_AUDIT")
public class OutboundAudit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TARGET_NAME")
    private String targetName;

    @Column(name = "TARGET_CONTEXT")
    private String targetContext;

    @Column(name = "REQUEST_URL")
    private String requestUrl;

    @Column(name = "REQUEST_METHOD")
    private String requestMethod;

    @Column(name = "REQUEST_HEADERS", columnDefinition = "TEXT")
    private String requestHeaders;

    @Column(name = "REQUEST_BODY", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "RESPONSE_STATUS")
    private String responseStatus;

    @Column(name = "RESPONSE_BODY", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "RESPONSE_ERROR", columnDefinition = "TEXT")
    private String responseError;


    //getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetContext() {
        return targetContext;
    }

    public void setTargetContext(String targetContext) {
        this.targetContext = targetContext;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }
}