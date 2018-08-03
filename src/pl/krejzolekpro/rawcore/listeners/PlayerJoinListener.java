package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.RawCorePlugin;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.socketbyte.opengui.ColorUtil;

public class PlayerJoinListener implements Listener {

    private RawCorePlugin plugin = RawCorePlugin.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
        User user = User.get(event.getPlayer().getName());
        if(user.hasVanish()){
            event.getPlayer().sendMessage(ColorUtil.fixColor("&aDolaczyles na serwer posiadajac VANISH."));
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!player.hasPermission("rawcore.vanish.nohide")){
                    player.hidePlayer(plugin, event.getPlayer());
                }
            }
        }
        if(user.hasGod()){
            event.getPlayer().sendMessage(ColorUtil.fixColor("&aDolaczyles na serwer posiadajac GOD."));
        }
    }
}
