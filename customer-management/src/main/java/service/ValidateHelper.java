package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {
    public ValidateHelper() {
    }

    public static boolean validateCustomer(String lastName, String firstName, String gender, String dob, String mobile, String address, String email, String provinceID, String totalOrders, String totalAmounts, String rankID) {
        boolean isPerfect = false;
        if (validateTotalOrders(totalOrders)
                && validateName(firstName, lastName)
                && validateTotalAmounts(totalAmounts)
                && validateDate(dob)
                && validateEmail(email)
                && validateMobile(mobile)
                && validateAddress(address)
                && validateSelectedItems(gender)
                && validateSelectedItems(provinceID)
                && validateSelectedItems(rankID)) {
            isPerfect = true;
        }
        return isPerfect;
    }

    public static boolean validateName(String firstName, String lastName) {
        boolean isLastName = true;
        boolean isFirstName = true;
        int minLength = 1;
        int maxLengthName = 45;
        String trimmedFirstName = trimmedString(firstName);
        String trimmedLastName = trimmedString(lastName);
        int lengthFirstName = trimmedFirstName.length();
        int lengthLastName = trimmedLastName.length();
        Pattern pattern = Pattern.compile("[a-zA-Z]?");
        Matcher matcherFirstName = pattern.matcher(trimmedFirstName);
        Matcher matcherLastName = pattern.matcher(trimmedLastName);
        //validate firstname
        if (!matcherFirstName.matches()) {
            String message1 = "invalid firstname";
            isFirstName = false;
        }
        if (lengthFirstName < minLength) {
            String message2 = "Please input firstname";
            isFirstName = false;
        }
        if (lengthFirstName > maxLengthName) {
            String message3 = "Firstname is too long";
            isFirstName = false;
        }
        //validate lastname
        if (!matcherLastName.matches()) {
            String message1 = "invalid lastname";
            isLastName = false;
        }
        if (lengthLastName < minLength) {
            String message2 = "Please input lastname";
            isLastName = false;
        }
        if (lengthFirstName > maxLengthName) {
            String message3 = "Lastname is too long";
            isLastName = false;
        }
        if (isFirstName && isLastName) {
            return true;
        } else return false;
    }

    public static boolean validateAddress(String address) {
        boolean isAddress = true;
        int minLength = 1;
        int maxLength = 200;
        String trimmedAddress = trimmedString(address);
        int length = trimmedAddress.length();
        if (length < minLength) {
            String message2 = "Please input address";
            isAddress = false;
        }
        if (length > maxLength) {
            String message3 = "Address is too long";
            isAddress = false;
        }
        return isAddress;
    }

    public static boolean validateEmail(String email) {
        boolean isEmail = true;
        String trimmedEmail = trimmedString(email);
        int length = trimmedEmail.length();
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedEmail);
        int maxLengthEmail = 100;
        if (length > maxLengthEmail) {
            String mess = "Email is too long";
            isEmail = false;
        }
        if (!matcher.matches()) {
            String mess = "Invalid email";
            isEmail = false;
        }
        return isEmail;
    }

    public static boolean validateDate(String date) {
        String trimmedDate = trimmedString(date);
        String regex = "^(?:\\d{4}-(?:(?:0[13578]|1[02])-(?:0[1-9]|[1-2][0-9]|3[01])|(?:0[469]|11)-" +
                "(?:0[1-9]|[1-2][0-9]|30)|02-(?:0[1-9]|1[0-9]|2[0-8]))|\\d{2}(?:[02468][048]|[13579][26])-02-29)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedDate);
        return matcher.matches();
    }

    public static boolean validateMobile(String mobile) {
        boolean isMobile = true;
        String trimmedMobile = trimmedString(mobile);
        int length = trimmedMobile.length();
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?" +
                "(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedMobile);
        int maxLength = 45;
        if (!matcher.matches() || length > maxLength) {
            String mess = "Invalid mobile";
            isMobile = false;
        }
        return isMobile;
    }

    public static boolean validateTotalOrders(String totalOrders) {
        boolean isTotalOrders = false;
        String trimmedOrder = trimmedString(totalOrders);
        try {
            int number = Integer.parseInt(trimmedOrder);
            if (number >= 0) {
                isTotalOrders = true;
            } else {
                String resultTotalOrders = "";
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
            String resultTotalOrders = "";
        }
        return isTotalOrders;
    }

    public static boolean validateTotalAmounts(String totalAmounts) {
        boolean isTotalAmounts = false;
        String trimmedAmount = trimmedString(totalAmounts);
        try {
            double number = Double.parseDouble(trimmedAmount);
            if (number >= 0) {
                isTotalAmounts = true;
            } else {
                String resultTotalAmounts = "";
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
            String resultTotalAmounts = "";
        }
        return isTotalAmounts;
    }

    public static boolean validateSelectedItems(String str) {
        String trimmedStr = trimmedString(str);
        int length = trimmedStr.length();
        if (str != null && length == 0) {
            return true;
        } else return false;
    }

    public static String trimmedString(String str) {
        String trimmedStr = str.trim().replaceAll("\\s+", " ");
        return trimmedStr;
    }
}
