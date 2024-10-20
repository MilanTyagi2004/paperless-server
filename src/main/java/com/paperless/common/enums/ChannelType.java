package com.paperless.common.enums;

public enum ChannelType {
    MOBILE("MOBILE"),
    EMAIL("EMAIL");

    private final String displayName;

    ChannelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
