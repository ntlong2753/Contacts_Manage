package util;

public class z {
    private static final String PHONE_REGEX = "^0[0-9]{9,10}$";

    public static boolean checkPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return phone.matches(PHONE_REGEX);
    }
}
