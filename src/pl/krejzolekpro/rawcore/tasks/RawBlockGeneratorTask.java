package pl.krejzolekpro.rawcore.tasks;

import org.bukkit.scheduler.BukkitScheduler;
import pl.krejzolekpro.rawcore.RawCorePlugin;
import pl.krejzolekpro.rawcore.files.Config;
import pl.krejzolekpro.rawcore.obejcts.RawBlock;
import pl.socketbyte.opengui.ColorUtil;

public class RawBlockGeneratorTask {

    public static void start(RawCorePlugin plugin){
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            int amount = 0;
            for(RawBlock rawBlock : RawBlock.blockList){
                if(rawBlock.getActive()) {
                    if (rawBlock.getTime() <= System.currentTimeMillis()) {
                        rawBlock.getLocation().getBlock().setType(rawBlock.getBlock());
                        amount++;
                        rawBlock.delete();
                    }
                }
            }
            if(amount > 0){
                if(amount == 1) {
                    plugin.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eZregenerowano &6" + amount + " &erude."));
                } else if(amount < 5) {
                    plugin.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eZregenerowano &6" + amount + " &erudy."));
                } else {
                    plugin.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eZregenerowano &6" + amount + " &erud."));
                }
            }
        }, 20*60L, 20*60L);
    }

}
