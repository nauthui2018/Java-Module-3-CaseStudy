package service;

import model.Customer;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {
    Customer customer = new Customer();
    public ValidateHelper() {
    }

    private int customerID;
    private String lastName;
    private String firstName;
    private boolean gender;
    private String dob;
    private String mobile;
    private String address;
    private String email;
    private int provinceID;
    private int totalOrders = 0;
    private double totalAmounts = 0;
    private int rankID;

//    private  static boolean validateCustomer(Customer customer) {
//        //validate customerID
//        try {
//            int number = Integer.parseInt(String.valueOf(customer.getCustomerID()));
//            if (number >= 0) {
//                return true;
//            } else return false;
//        } catch (NumberFormatException ex) {
//            System.out.println(ex.getLocalizedMessage());
//            return false;
//        }
//        return true;
//    }

    public static boolean validateString(String str, int maxLength, int minLength) {
        int length = str.length();
        return minLength < length && length < maxLength;
    }
    public static String standardized(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public static boolean validateIntegerNumber(String str) {
        try {
            int number = Integer.parseInt(str);
            if (number >= 0) {
                return true;
            } else return false;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean validateDoubleNumber(String str) {
        try {
            double number = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean regexName(String name, int maxLength, int minLength) {
        name = standardized(name);
        if(validateString(name,maxLength,minLength)) {
            Pattern pattern = Pattern.compile("[a-zA-Z]?");
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        }
        return false;
    }

    public static boolean regexEmail(String email) {
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean regexDate(String date) {
        String regex = "^(?:\\d{4}-(?:(?:0[13578]|1[02])-(?:0[1-9]|[1-2][0-9]|3[01])|(?:0[469]|11)-" +
                "(?:0[1-9]|[1-2][0-9]|30)|02-(?:0[1-9]|1[0-9]|2[0-8]))|\\d{2}(?:[02468][048]|[13579][26])-02-29)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean regexMobile(String mobile) {
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?" +
                "(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
