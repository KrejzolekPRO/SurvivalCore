package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.RawCorePlugin;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.socketbyte.opengui.ColorUtil;

public class VanishCommand implements CommandExecutor {

    private RawCorePlugin plugin = RawCorePlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender instanceof Player){
            Player player = ((Player) commandSender).getPlayer();
            if(player.hasPermission("rawcore.vanish.commands")){
                User user = User.get(player.getName());
                if(user.hasVanish()){
                    player.sendMessage(ColorUtil.fixColor("&aOd teraz jestes widoczny."));
                    user.setVanish(false);
                    for(Player p : Bukkit.getOnlinePlayers()){
                        p.showPlayer(plugin, player);
                    }
                } else {
                    player.sendMessage(ColorUtil.fixColor("&aOd teraz jestes niewidoczny."));
                    user.setVanish(true);
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(!player.hasPermission("rawcore.vanish.nohide")){
                            p.hidePlayer(plugin, player);
                        }
                    }
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
