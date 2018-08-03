package pl.krejzolekpro.rawcore.commands.normal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.utils.GuiUtil;
import pl.socketbyte.opengui.ColorUtil;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender instanceof Player){
            GuiUtil.shopGUI(((Player) commandSender).getPlayer());
            return true;
        }
        commandSender.sendMessage(ColorUtil.fixColor("&cTa komenda jest przeznaczona tylko dla graczy!"));
        return false;
    }
}
