package pl.krejzolekpro.rawcore.commands.normal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.krejzolekpro.rawcore.RawCorePlugin;
import pl.krejzolekpro.rawcore.files.Config;
import pl.krejzolekpro.rawcore.obejcts.RawBlock;
import pl.socketbyte.opengui.ColorUtil;

public class GeneratorCommand implements CommandExecutor {

    private RawCorePlugin instance = RawCorePlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender.hasPermission("rawcore.oregenerator.command")){
            if(args.length == 1 && args[0].equals("zregeneruj")){
                int amount = RawBlockDebug();
                commandSender.sendMessage(ColorUtil.fixColor("&eZregenerowano " + amount + " rud."));
                if(amount > 0){
                    if(amount == 1) {
                        instance.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eAdmin &6" + commandSender.getName() + " &ezregenerowal &6" + amount + " &erude."));
                    } else if(amount < 5) {
                        instance.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eAdmin &6" + commandSender.getName() + " &ezregenerowal &6" + amount + " &erudy."));
                    } else {
                        instance.getServer().broadcastMessage(ColorUtil.fixColor(Config.TAG + " &eAdmin &6" + commandSender.getName() + " &ezregenerowal &6" + amount + " &erud."));
                    }
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cPoprawne uzycie: /oregenerator zregeneruj."));
            }
            return true;
        }
        commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
        return false;
    }

    private Integer RawBlockDebug(){
        Integer amount = 0;
        for(RawBlock block : RawBlock.blockList){
            if(block.getActive()) {
                block.getLocation().getBlock().setType(block.getBlock());
                amount++;
                block.delete();
            }
        }
        return amount;
    }
}
