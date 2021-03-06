package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import pl.krejzolekpro.rawcore.enums.ChallengeType;
import pl.krejzolekpro.rawcore.obejcts.Challenge;
import pl.socketbyte.opengui.ColorUtil;

public class PlayerItemConsumeListener implements Listener {

    private Challenge challenge = Challenge.getInstance();

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        if(challenge.isOn()) {
            if(challenge.getChallengeType() == ChallengeType.EAT) {
                Material item = event.getItem().getType();
                if(challenge.getBlock() == item) {
                    Player player = event.getPlayer();
                    int amount = 0;
                    if(challenge.getAmountList().get(player) == null){
                        amount = 0 +1;
                    } else {
                        amount = challenge.getAmountList().get(player) +1;
                    }
                    challenge.getAmountList().put(player, amount);
                    player.sendMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &eStatus wyzwania: " + amount + "&e/" + challenge.getAmountWinner() + "."));
                    if(amount++ >= challenge.getAmountWinner()) {
                        if(challenge.getWinner() == null) {
                            challenge.setWinner(player);
                            challenge.setChallengeType(ChallengeType.NONE);
                            challenge.getAmountList().clear();
                            challenge.sendInfo();
                        }
                    }
                }
            }
        }
    }
}
