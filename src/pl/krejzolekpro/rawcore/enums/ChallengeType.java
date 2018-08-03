package pl.krejzolekpro.rawcore.enums;

public enum ChallengeType {
    BREAK,
    PLACE,
    FISH,
    EAT,
    NONE;

    public static ChallengeType getChallengeByText(String type){
        if(type.equals("BREAK")){
            return ChallengeType.BREAK;
        }
        if(type.equals("PLACE")){
            return ChallengeType.PLACE;
        }
        if(type.equals("FISH")){
            return ChallengeType.FISH;
        }
        if(type.equals("EAT")){
            return ChallengeType.EAT;
        }
        return ChallengeType.NONE;
    }
}
