package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.socketbyte.opengui.ColorUtil;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender instanceof Player){
            Player player = ((Player) commandSender).getPlayer();
            if(player.hasPermission("rawcore.fly.command")){
                if(player.getAllowFlight()){
                    player.setAllowFlight(false);
                    player.sendMessage(ColorUtil.fixColor("&aLatanie zostalo WYLACZONE."));
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage(ColorUtil.fixColor("&aLatanie zostalo WLACZONE."));
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
            }
        } else {
            commandSender.sendMessage(ColorUtil.fixColor("&cNie mozesz wykonac tej komendy bedac konsola!"));
        }
        return false;
    }
}
