package pl.krejzolekpro.rawcore.obejcts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import pl.krejzolekpro.rawcore.enums.ChallengeType;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.utils.ReplaceUtil;
import pl.socketbyte.opengui.ColorUtil;

import java.util.HashMap;

public class Challenge {

    private static Challenge instance;

    public Challenge(){
        instance = this;
    }

    public static Challenge getInstance(){
        if(instance == null){
            return new Challenge();
        }
        return instance;
    }

    private ChallengeType challengeType = ChallengeType.NONE;
    private Material block;
    private HashMap<Player, Integer> amountList;
    private Integer amountWinner;
    private Player winner;

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }

    public Material getBlock() {
        return block;
    }

    public HashMap<Player, Integer> getAmountList() {
        return amountList;
    }

    public Integer getAmountWinner() {
        return amountWinner;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Boolean isOn(){
        if(this.challengeType == ChallengeType.NONE || this.challengeType == null){
            return false;
        }
        return true;
    }

    public void sendInfo(){
        Bukkit.broadcastMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &eGracz " + this.getWinner().getName() + " &ejako pierwszy wykonal wyzwanie! Gratulacje!"));
    }

    public void create(Player player, ChallengeType type, Material block, Integer amount) {
        player.closeInventory();
        this.challengeType = type;
        this.block = block;
        this.amountList = new HashMap<Player, Integer>();
        this.amountWinner = amount;
        this.winner = null;
        if (type != ChallengeType.FISH) {
            Bukkit.broadcastMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &ePojawilo sie nowe wyzwanie! Typ: " + ReplaceUtil.replaceChallenge(type.name()).toLowerCase() + ",material: " + block.toString().toLowerCase() + "&e, w ilosci: " + amount.toString() + "&e."));
        } else {
            Bukkit.broadcastMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &ePojawilo sie nowe wyzwanie! Typ: " + ReplaceUtil.replaceChallenge(type.name()).toLowerCase() + "&e. Aby wykonac to wyzwanie, nalezy wylowic jakas rzecz&e, w ilosci: " + amount.toString() + "."));
        }
    }
}
