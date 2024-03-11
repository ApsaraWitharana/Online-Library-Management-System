package lk.ijse.controller.member.utile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sateValidation {

    public static boolean userIdCheck(String user_id) {
        String pattern = "^U-\\d+$";  //U-001
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(user_id);
        return m.matches();

    }

    public static boolean userNameCheck(String user_name) {
        String pattern = "^[A-Za-z\\s'-]+$";  //Sachini-Apsara
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(user_name);
        return m.matches();

    }

    public static boolean AddressCheck(String address) {
        String pattern = ".{3,}";   //Galle
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(address);
        return m.matches();

    }
    public static boolean bookIdCheck(String book_id) {

        String pattern = "^B-\\d+$";  //B-001
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(book_id);
        return m.matches();

    }

    public static boolean libraryIdCheck(String library_id) {

        String pattern = "^L-\\d+$";  //L-001
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(library_id);
        return m.matches();

    }

    public static boolean contactNumberCheck(String contact ) {

        String pattern = "'^(\\+94|0)?(7\\d{2}|[1-6]\\d{1}|8[0-1])\\d{7}$";  //071-2345678
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(contact);
        return m.matches();

    }



}
