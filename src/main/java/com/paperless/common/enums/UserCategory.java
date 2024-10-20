package com.paperless.common.enums;

public enum UserCategory {

    REGISTERED("REGISTERED"),
    GUEST("GUEST");

    private final String displayName;

    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
