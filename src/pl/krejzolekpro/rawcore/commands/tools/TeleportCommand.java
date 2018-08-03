package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.utils.IntegerUtil;
import pl.socketbyte.opengui.ColorUtil;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(args.length == 1) {
            if(commandSender instanceof Player) {
                Player player = ((Player) commandSender).getPlayer();
                if(player.hasPermission("rawcore.teleport.command-toplayer")) {
                    if(Bukkit.getPlayer(args[0]) != null){
                        Player target = Bukkit.getPlayer(args[0]);
                        player.teleport(target.getLocation());
                        player.sendMessage(ColorUtil.fixColor("&aZostales przetelportowany do " + target.getName() + "&a."));
                    }
                } else {
                    commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cNie jestes graczem!"));
            }
        }
        else if(args.length == 2){
            if(commandSender.hasPermission("rawcore.teleport.command-playertoplayer")){
                if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null){
                    Player player = Bukkit.getPlayer(args[0]);
                    Player target = Bukkit.getPlayer(args[1]);
                    player.teleport(target.getLocation());
                    player.sendMessage(ColorUtil.fixColor("&aZostales przeteleportowany do " + target.getName() + "&a."));
                } else {
                    commandSender.sendMessage(ColorUtil.fixColor("&cGracze nie sa online!"));
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
            }
        }
        else if(args.length == 3){
            if(commandSender.hasPermission("rawcore.teleport.command-tocoordinates")){
                if(commandSender instanceof Player) {
                    Player player = ((Player) commandSender).getPlayer();
                    if(IntegerUtil.isInteger(args[0]) && IntegerUtil.isInteger(args[1]) && IntegerUtil.isInteger(args[2])) {
                        player.teleport(new Location(player.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                        player.sendMessage(ColorUtil.fixColor("&aZostales przeteleportowany na: X: " + args[0] + " Y: " + args[1] + " Z: " + args[2] + "&a."));
                    } else {
                        commandSender.sendMessage(ColorUtil.fixColor("&cPodane koordynaty nie sa poprawne!"));
                    }
                } else {
                    commandSender.sendMessage(ColorUtil.fixColor("&cNie jestes graczem!"));
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
            }
        }
        else {
            commandSender.sendMessage(ColorUtil.fixColor("&c/tp <gracz> - teleportacja do gracza"));
            commandSender.sendMessage(ColorUtil.fixColor("&c/tp <kto> <dokogo> - teleportacja gracza do gracza"));
            commandSender.sendMessage(ColorUtil.fixColor("&c/tp <x> <y> <z> - teleportacja do podanych koordynat"));
        }
        return false;
    }
}
