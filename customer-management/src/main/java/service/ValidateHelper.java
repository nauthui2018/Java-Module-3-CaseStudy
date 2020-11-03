package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {

    public ValidateHelper() {
    }

    public boolean validateFirstName(String name) {
        boolean isValid = true;
        int minLength = 1;
        int maxLengthName = 45;
        String trimmedFirstName = trimmedString(name);
        int lengthFirstName = trimmedFirstName.length();
        Pattern pattern = Pattern.compile("^(?![\\s.]+$)[a-zA-Z\\s.]*$");
        Matcher matcherFirstName = pattern.matcher(trimmedFirstName);
        if (!matcherFirstName.matches()
                || lengthFirstName < minLength
                || lengthFirstName > maxLengthName) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateLastName(String name) {
        boolean isValid = true;
        int minLength = 1;
        int maxLengthName = 45;
        String trimmedFirstName = trimmedString(name);
        int lengthFirstName = trimmedFirstName.length();
        Pattern pattern = Pattern.compile("[A-Z][a-z]*");
        Matcher matcherFirstName = pattern.matcher(trimmedFirstName);
        if (!matcherFirstName.matches()
                || lengthFirstName < minLength
                || lengthFirstName > maxLengthName) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateAddress(String address) {
        boolean isValid = true;
        int minLength = 1;
        int maxLength = 200;
        String trimmedAddress = trimmedString(address);
        int length = trimmedAddress.length();
        if (length < minLength || length > maxLength) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateEmail(String email) {
        boolean isValid = true;
        String trimmedEmail = trimmedString(email);
        int length = trimmedEmail.length();
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedEmail);
        int maxLengthEmail = 100;
        if (length > maxLengthEmail || !matcher.matches()) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateDate(String date) {
        String trimmedDate = trimmedString(date);
        String regex = "^(?:\\d{4}-(?:(?:0[13578]|1[02])-(?:0[1-9]|[1-2][0-9]|3[01])|(?:0[469]|11)-" +
                "(?:0[1-9]|[1-2][0-9]|30)|02-(?:0[1-9]|1[0-9]|2[0-8]))|\\d{2}(?:[02468][048]|[13579][26])-02-29)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedDate);
        return matcher.matches();
    }

    public boolean validateMobile(String mobile) {
        boolean isValid = true;
        String trimmedMobile = trimmedString(mobile);
        int length = trimmedMobile.length();
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?" +
                "(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedMobile);
        int maxLength = 45;
        if (!matcher.matches() || length > maxLength) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateIntegerNumber(String str) {
        boolean isValid = false;
        String trimmedStr = trimmedString(str);
        try {
            int number = Integer.parseInt(trimmedStr);
            if (number >= 0) {
                isValid = true;
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return isValid;
    }

    public boolean validateDoubleNumber(String str) {
        boolean isValid = false;
        String trimmedStr = trimmedString(str);
        try {
            double number = Double.parseDouble(trimmedStr);
            if (number >= 0) {
                isValid = true;
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return isValid;
    }

    public String trimmedString(String str) {
        String trimmedStr = str.trim().replaceAll("\\s+", " ");
        return trimmedStr;
    }

    public boolean validateGender(String str) {
        boolean isValid = false;
        String trimmedStr = trimmedString(str);
        try {
            int number = Integer.parseInt(trimmedStr);
            if (number >= 0 && number <=2) {
                isValid = true;
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return isValid;
    }
}
