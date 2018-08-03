package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;
import pl.socketbyte.opengui.ColorUtil;

public class ASyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String finalMessage = "";
        Player player = event.getPlayer();
        String message = event.getMessage();
        String prefix = ColorUtil.fixColor("");
        if(player.hasPermission("rawcore.prefix.vip")){
            prefix = ColorUtil.fixColor("&6&l[VIP]&6");
        }
        if(player.hasPermission("rawcore.prefix.gracz")){
            prefix = ColorUtil.fixColor("&7[Gracz] ");
        }
        if(player.hasPermission("rawcore.prefix.helper")){
            prefix = ColorUtil.fixColor("&3[Helper] ");
        }
        if(player.hasPermission("rawcore.prefix.moderator")){
            prefix = ColorUtil.fixColor("&9[Moderator] ");
        }
        if(player.hasPermission("rawcore.prefix.owner")){
            prefix = ColorUtil.fixColor("&4[OWNER] ");
        }
        finalMessage = ColorUtil.fixColor(prefix + player.getName() + ": &7") + (player.hasPermission("rawcore.boosters.color") ? ColorUtil.fixColor(message) : message);
        event.setFormat(finalMessage);
    }
}
