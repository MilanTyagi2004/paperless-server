package com.paperless.common.enums;

public enum FileCategory {
    PROJECT_DETAILS("PROJECT_DETAILS"),
    PROFILE_PICTURE("PROFILE_PICTURE"),
    TERMS_AND_CONDITIONS("TERMS_AND_CONDITIONS"),
    PRIVACY_POLICY("PRIVACY_POLICY"),
    OTHER("OTHER"), SUPPORT("SUPPORT");

    private final String displayName;

    FileCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FileCategory fromString(String category) {
        for (FileCategory fileCategory : FileCategory.values()) {
            if (fileCategory.name().equalsIgnoreCase(category)) {
                return fileCategory;
            }
        }
        return FileCategory.OTHER; // Default category if no match is found
    }
}
