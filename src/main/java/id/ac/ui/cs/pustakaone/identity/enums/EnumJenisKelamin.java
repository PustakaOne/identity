package id.ac.ui.cs.pustakaone.identity.enums;

import lombok.Getter;

@Getter
public enum EnumJenisKelamin {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private final String value;

    private EnumJenisKelamin(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        for (EnumJenisKelamin jenisKelamin : EnumJenisKelamin.values()) {
            if (jenisKelamin.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}