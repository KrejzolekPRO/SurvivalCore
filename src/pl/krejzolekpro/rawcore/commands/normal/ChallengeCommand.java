package pl.krejzolekpro.rawcore.commands.normal;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.krejzolekpro.rawcore.enums.ChallengeType;
import pl.krejzolekpro.rawcore.utils.GuiUtil;
import pl.krejzolekpro.rawcore.utils.IntegerUtil;
import pl.socketbyte.opengui.ColorUtil;

public class ChallengeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender instanceof Player) {
            Player player = ((Player) commandSender).getPlayer();
            if (commandSender.hasPermission("rawcore.challenge.command")) {
                if (args.length == 4){
                    if (args[1].equals("PLACE") || args[1].equals("BREAK")){
                        if (IntegerUtil.isInteger(args[3])) {
                            if (Material.matchMaterial(args[2]) != null) {
                                Material block = Material.matchMaterial(args[2]);
                                if (block.isBlock()) {
                                    ChallengeType type = ChallengeType.getChallengeByText(args[1]);
                                    GuiUtil.confirmationGUI(player, type, block, Integer.parseInt(args[3]));
                                } else {
                                    commandSender.sendMessage(ColorUtil.fixColor("&cArgument trzeci nie jest pelnym blokiem!"));
                                }
                            } else {
                                commandSender.sendMessage(ColorUtil.fixColor("&cArgument trzeci nie jest pelnym blokiem!"));
                            }
                        } else {
                            commandSender.sendMessage(ColorUtil.fixColor("&cArgument czwarty nie jest liczba!"));
                        }
                    } else if(args[1].equals("FISH") || args[1].equals("EAT")){
                        if (IntegerUtil.isInteger(args[3])){
                            if (Material.matchMaterial(args[2]) != null){
                                Material item = Material.matchMaterial(args[2]);
                                if (!item.isBlock()) {
                                    if(ChallengeType.getChallengeByText(args[1]) == ChallengeType.FISH){
                                        GuiUtil.confirmationGUI(player, ChallengeType.FISH, Material.RAW_FISH, Integer.parseInt(args[3]));
                                    } else {
                                        GuiUtil.confirmationGUI(player, ChallengeType.EAT, item, Integer.parseInt(args[3]));
                                    }
                                } else {
                                    commandSender.sendMessage(ColorUtil.fixColor("&cArgument trzeci nie moze byc pelnym blokiem!"));
                                }
                            } else {
                                commandSender.sendMessage(ColorUtil.fixColor("&cArgument trzeci nie jest przedmiotem!"));
                            }
                        } else {
                            commandSender.sendMessage(ColorUtil.fixColor("&c&cArgument czwarty nie jest liczba!"));
                        }
                    } else {
                        commandSender.sendMessage(ColorUtil.fixColor("&cPoprawne uzycie: /challenge create [PLACE|BREAK|FISH|EAT] [PRZEDMIOT/BLOK, np. STONE, APPLE], ILOSC"));
                    }
                } else {
                    commandSender.sendMessage(ColorUtil.fixColor("&cPoprawne uzycie: /challenge create [PLACE|BREAK|FISH|EAT] [PRZEDMIOT/BLOK, np. STONE, APPLE], ILOSC"));
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
