package com.paperless.common.enums;

public enum Roles {
    SUPER_ADMIN("Super Admin"),
    SUB_ADMIN("Sub Admin"),
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    Roles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static Roles fromString(String roleName) {
        for (Roles role : Roles.values()) {
            if (role.displayName.equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role with name " + roleName + " found.");
    }
}
