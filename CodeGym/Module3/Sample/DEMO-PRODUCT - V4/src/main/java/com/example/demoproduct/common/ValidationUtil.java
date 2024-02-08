package com.example.demoproduct.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String CUSTOMER_ID_PATTERN = "^KH-\\d{4}$";
    private static final String NAME_PHONE = "^DT-\\d{4}$";


    // Validate số điện thoại định dạng 090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx
    private static final String NUMBER_PHONE = "^(090|091|\\\\(84\\\\)\\\\+90|\\\\(84\\\\)\\\\+91)[\\\\d]{7}$";
    // Validate số CCCD 9 và 12 số
    private static final String ID_CARD_NUMBER = "^([\\\\d]{9}|[\\\\d]{12})$";
    // Validate tên người dùng chỉ chứa ký tự từ a-z hoặc A-Z không phân biệt hoa thường
    private static final String NAME_USER = "^[a-zA-Z\\s]+$";
    // Validate tên người dùng không chứa k tự đặc biệt
    private static final String NAME_USER_NUMBER = "^[a-zA-Z0-9\\s]+$";
    // Validate số dương
    private static final String POSITIVE_NUMBERS = "^([1-9][\\\\d]*(\\\\.[\\\\d]*)?)|(0\\\\.[\\\\d]+)$";
    // Validate số nguyên dương
//    private static final String POSITIVE_INTEGER = "^[1-9][\\\\d]*$";
    private static final String POSITIVE_INTEGER = "^\\d{10}$";
    // Validate email
    private static final String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    // Validate ngày tháng năm
    private static final String DATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\\\d\\\\d$";
//    private static final String NUMBERS_PHONE = "";

    public static boolean isValidCustomerID(String customerID) {
        // Validate Mã Khách hàng có định dạng là KH-XXXX (X là số từ 0-9)
        Pattern pattern = Pattern.compile(CUSTOMER_ID_PATTERN);
        Matcher matcher = pattern.matcher(customerID);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Validate số điện thoại
        Pattern pattern = Pattern.compile(POSITIVE_INTEGER);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        // Validate địa chỉ email
        Pattern pattern = Pattern.compile(EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPositonInterger(String num) {
        // Validate số nguyên dương
        Pattern pattern = Pattern.compile(POSITIVE_INTEGER);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static boolean isValidDate(String dateStr) {
        // Validate định dạng ngày tháng năm
        Pattern pattern = Pattern.compile(DATE);
        Matcher matcher = pattern.matcher(dateStr);
        return matcher.matches();
    }
    public static boolean isValidNamePhone(String namePhone) {
        // Validate tên của điên thoại
        Pattern pattern = Pattern.compile(NAME_PHONE);
        Matcher matcher = pattern.matcher(namePhone);
        return matcher.matches();
    }

}