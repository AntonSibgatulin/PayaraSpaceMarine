package my.compary.math;

public class MathClass {

    public static boolean isNumeric(String string) {
        int intValue;


        if(string == null || string.equals("")) {
           return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
             }
        return false;
    }


}
