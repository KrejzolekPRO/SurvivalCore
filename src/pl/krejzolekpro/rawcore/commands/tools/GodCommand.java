package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.socketbyte.opengui.ColorUtil;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender instanceof Player){
            Player player = ((Player) commandSender).getPlayer();
            if(player.hasPermission("rawcore.god.command")){
                User user = User.get(player.getName());
                if(user.hasGod()){
                    player.sendMessage(ColorUtil.fixColor("&aOd teraz jestes smiertelny."));
                    user.setGod(false);
                } else {
                    player.sendMessage(ColorUtil.fixColor("&aOd teraz jestes niesmiertelny."));
                    user.setGod(true);
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
            }
        } else {
            commandSender.sendMessage(ColorUtil.fixColor("&cTa komenda jest przeznaczona tylko dla graczy!"));
        }
        return false;
    }
}
