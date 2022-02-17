package pl.mateusz.rodak.zakladybukm.utils;

import java.util.Objects;

public class Utility {

    public static boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean pinOrNameIsWrong(String name, String pin) {
        return name == null || pin == null || name.equals("") || pin.length() != 4 || name.length() > 15 || Utility.isNotNumeric(pin);

    }

    public static boolean comparePins(String userPin, String pin){

        return Objects.equals(userPin, pin) || userPin == null;
    }

    public static String cleanInt(String str) {
        try {
            Integer i = Integer.parseInt(str);
            return i.toString();
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
