package com.paperless.common.dto;

import java.util.Map;

public class ThirdPartyRequestDetailsDTO {
    private Map<String, String> paramMap;       // For query parameters
    private Map<String, Object> bodyMap;        // For request body (POST/PUT)
    private Map<String, String> pathVariableMap; // For path variables (if any)


    private String targetName;
    private String targetContext;

    // Getters and setters
    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getBodyMap() {
        return bodyMap;
    }

    public void setBodyMap(Map<String, Object> bodyMap) {
        this.bodyMap = bodyMap;
    }

    public Map<String, String> getPathVariableMap() {
        return pathVariableMap;
    }

    public void setPathVariableMap(Map<String, String> pathVariableMap) {
        this.pathVariableMap = pathVariableMap;
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

    @Override
    public String toString() {
        return "ThirdPartyRequestDetailsDTO{" +
                "paramMap=" + paramMap +
                ", bodyMap=" + bodyMap +
                ", pathVariableMap=" + pathVariableMap +
                ", targetName='" + targetName + '\'' +
                ", targetContext='" + targetContext + '\'' +
                '}';
    }
}
