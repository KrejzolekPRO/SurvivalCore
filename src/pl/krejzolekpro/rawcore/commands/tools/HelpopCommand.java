package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.socketbyte.opengui.ColorUtil;

public class HelpopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(args.length > 0){
            String message = "";
            for(String text : args){
                message += text + " ";
            }
            for(Player player : Bukkit.getOnlinePlayers()){
                if(player.hasPermission("rawcore.helpop.notyfication")){
                    player.sendMessage(ColorUtil.fixColor("&e&l[HELPOP] &e" + commandSender.getName() + ": ") + message);
                }
            }
        }
        return false;
    }
}
