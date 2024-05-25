package id.ac.ui.cs.pustakaone.identity.enums;

import lombok.Getter;

@Getter
public enum EnumRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    private EnumRole(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        for (id.ac.ui.cs.pustakaone.identity.enums.EnumRole role : id.ac.ui.cs.pustakaone.identity.enums.EnumRole.values()) {
            if (role.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}