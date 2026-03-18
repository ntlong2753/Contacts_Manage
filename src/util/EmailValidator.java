package util;

public class NameValidator {
    private static final String NAME_REGEX = "^[A-Za-zÀ-ỹ]+(\\s[A-Za-zÀ-ỹ]+)+$";
    public static boolean checkName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches(NAME_REGEX);
        }
}
