package pl.krejzolekpro.rawcore.commands.tools;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.socketbyte.opengui.ColorUtil;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if(commandSender.hasPermission("rawcore.gamemode.command")){
            if(args.length > 0 && args.length < 3){
                if(args.length == 1){
                    if(commandSender instanceof Player) {
                        Player player = ((Player) commandSender).getPlayer();
                        if (args[0].equals("1") || args[0].equals("creative") || args[0].equals("c") || args[0].equals("k") || args[0].equals("kreatywny")) {
                            player.sendMessage(ColorUtil.fixColor("&aTryb gry zostal ustawiony na KREATYWNY."));
                            player.setGameMode(GameMode.CREATIVE);
                        }
                        if (args[0].equals("0") || args[0].equals("survival") || args[0].equals("s") || args[0].equals("p") || args[0].equals("przetrwanie")) {
                            player.sendMessage(ColorUtil.fixColor("&aTryb gry zostal ustawiony na PRZETRWANIE."));
                            player.setGameMode(GameMode.SURVIVAL);
                        }
                    } else {
                        commandSender.sendMessage(ColorUtil.fixColor("&cAby zmienic tryb gry, nie mozesz byc konsola."));
                    }
                } else {
                    if(Bukkit.getPlayer(args[1]) != null){
                        Player player = Bukkit.getPlayer(args[1]);
                        if(player.hasPermission("rawcore.gamemode.change")){
                            if (args[0].equals("1") || args[0].equals("creative") || args[0].equals("c") || args[0].equals("k") || args[0].equals("kreatywny")) {
                                player.sendMessage(ColorUtil.fixColor("&aTryb gry zostal ustawiony na KREATYWNY."));
                                commandSender.sendMessage(ColorUtil.fixColor("&aUstawiles tryb gry gracza " + args[1] + " &ana KREATYWNY."));
                                player.setGameMode(GameMode.CREATIVE);
                            }
                            if (args[0].equals("0") || args[0].equals("survival") || args[0].equals("s") || args[0].equals("p") || args[0].equals("przetrwanie")) {
                                player.sendMessage(ColorUtil.fixColor("&aTryb gry zostal ustawiony na PRZETRWANIE."));
                                commandSender.sendMessage(ColorUtil.fixColor("&aUstawiles tryb gry gracza " + args[1] + " &ana PRZETRWANIE."));
                                player.setGameMode(GameMode.SURVIVAL);
                            }
                        } else {
                            commandSender.sendMessage(ColorUtil.fixColor("&cTen gracz nie posiada uprawnien, aby moc mu zmienic tryb gry!"));
                        }
                    } else {
                        commandSender.sendMessage(ColorUtil.fixColor("&cGracz " + args[1] + " &cnie jest na serwerze!"));
                    }
                }
            } else {
                commandSender.sendMessage(ColorUtil.fixColor("&cPoprawne uzycie: /gamemode <typ> *<gracz>*."));
            }
        } else {
            commandSender.sendMessage(ColorUtil.fixColor("&cNie posiadasz uprawnien!"));
        }
        return false;
    }
}
