package pl.krejzolekpro.rawcore.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import pl.krejzolekpro.rawcore.RawCorePlugin;
import pl.krejzolekpro.rawcore.enums.ChallengeType;
import pl.krejzolekpro.rawcore.obejcts.Challenge;
import pl.krejzolekpro.rawcore.utils.ReplaceUtil;
import pl.socketbyte.opengui.ColorUtil;

public class ChallengeTask {

    private static Challenge challenge = Challenge.getInstance();

    public static void start(RawCorePlugin plugin){
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if(challenge.isOn()){
                if (challenge.getChallengeType() != ChallengeType.FISH) {
                    Bukkit.broadcastMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &eAktualnie trwa wyzwanie! Typ: " + ReplaceUtil.replaceChallenge(challenge.getChallengeType().toString().toLowerCase()) + "&e, material: " + challenge.getBlock().toString().toLowerCase() + "&e, w ilosci: " + challenge.getAmountWinner().toString() + "&e."));
                } else {
                    Bukkit.broadcastMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &eAktualnie trwa wyzwanie! Typ: " + ReplaceUtil.replaceChallenge(challenge.getChallengeType().toString().toLowerCase()) + "&e. Aby wykonac to wyzwanie, nalezy wylowic jakas rzecz&e, w ilosci: " + challenge.getAmountWinner().toString() + "."));
                }
            }
        }, 20*90L, 20*90L);
    }
}
