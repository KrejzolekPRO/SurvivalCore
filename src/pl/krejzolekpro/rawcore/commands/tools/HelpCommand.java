package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.krejzolekpro.rawcore.files.Config;
import pl.socketbyte.opengui.ColorUtil;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        for(String text : Config.HELP_MESSAGE){
            commandSender.sendMessage(ColorUtil.fixColor(text));
        }
        return false;
    }
}
