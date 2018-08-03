package pl.krejzolekpro.rawcore.utils;

public class ReplaceUtil {

    public static String replaceChallenge(String text){
        if(text.equals("PLACE") || text.equals("place")){
            return "Stawianie";
        }
        if(text.equals("BREAK") || text.equals("break")){
            return "Kopanie";
        }
        if(text.equals("FISH") || text.equals("fish")){
            return "Lowienie";
        }
        if(text.equals("EAT") || text.equals("eat")){
            return "Jedzenie";
        }
        return text;
    }
}
