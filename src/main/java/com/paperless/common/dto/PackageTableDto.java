package com.paperless.common.dto;

public class PackageTableDto {
    private Long id;

    private String packageName;

    private Long allowDrawing;
    private String duration;            //datatype of duration
    private Boolean freeTrial;
    private String description;
    private Long allowedUser;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getAllowDrawing() {
        return allowDrawing;
    }

    public void setAllowDrawing(Long allowDrawing) {
        this.allowDrawing = allowDrawing;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean getFreeTrial() {
        return freeTrial;
    }

    public void setFreeTrial(Boolean freeTrial) {
        this.freeTrial = freeTrial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAllowedUser() {
        return allowedUser;
    }

    public void setAllowedUser(Long allowedUser) {
        this.allowedUser = allowedUser;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
