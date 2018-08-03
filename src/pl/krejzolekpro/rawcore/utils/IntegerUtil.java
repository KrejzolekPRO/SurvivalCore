package pl.krejzolekpro.rawcore.utils;

public class IntegerUtil {

    public static Boolean isInteger(String number){
        try{
            Integer.parseInt(number);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
