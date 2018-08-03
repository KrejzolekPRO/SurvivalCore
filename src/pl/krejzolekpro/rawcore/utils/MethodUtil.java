package pl.krejzolekpro.rawcore.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.krejzolekpro.rawcore.listeners.BlockBreakListener;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.socketbyte.opengui.ColorUtil;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public enum MethodUtil {
    IRON,
    GOLD,
    GENERATOR,
    STONE,
    COLOR;


    public static boolean check(Player player, MethodUtil type){
        player.closeInventory();
        if(type == MethodUtil.IRON){
            if(player.hasPermission("rawcore.boosters.iron")){
                player.sendMessage(ColorUtil.fixColor("&cPosiadasz juz ten booster!"));
                return false;
            }
            if(!player.getInventory().contains(Material.IRON_INGOT, 512)){
                player.sendMessage(ColorUtil.fixColor("&cNie posiadasz 512 sztabek zelaza, aby kupic ten booster!"));
                return false;
            }
            player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 512));
            PermissionsEx.getUser(player).addPermission("rawcore.boosters.iron");
            player.sendMessage(ColorUtil.fixColor("&aGratulacje! &eIron Booster &azostal zakupiony!"));
            return true;
        }
        if(type == MethodUtil.GOLD){
            if(player.hasPermission("rawcore.boosters.gold")){
                player.sendMessage(ColorUtil.fixColor("&cPosiadasz juz ten booster!"));
                return false;
            }
            if(!player.getInventory().contains(Material.GOLD_INGOT, 256)){
                player.sendMessage(ColorUtil.fixColor("&cNie posiadasz 256 sztabek zlota, aby kupic ten booster!"));
                return false;
            }
            player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 256));
            PermissionsEx.getUser(player).addPermission("rawcore.boosters.gold");
            player.sendMessage(ColorUtil.fixColor("&aGratulacje! &eGold Booster &azostal zakupiony!"));
            return true;
        }
        if(type == MethodUtil.GENERATOR){
            if(player.hasPermission("rawcore.boosters.generator")){
                player.sendMessage(ColorUtil.fixColor("&cPosiadasz juz ten booster!"));
                return false;
            }
            if(!player.getInventory().contains(Material.DIAMOND, 256)){
                player.sendMessage(ColorUtil.fixColor("&cNie posiadasz 256 diamentow, aby kupic ten booster!"));
                return false;
            }
            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 256));
            PermissionsEx.getUser(player).addPermission("rawcore.boosters.generator");
            player.sendMessage(ColorUtil.fixColor("&aGratulacje! &eGenerator Booster &azostal zakupiony!"));
            return true;
        }
        if(type == MethodUtil.STONE){
            User user = User.get(player.getName());
            if(user.hasTurbo()){
                player.sendMessage(ColorUtil.fixColor("&cPosiadasz juz ten booster!"));
                return true;
            }
            if(!player.getInventory().contains(Material.DIAMOND, 16) && !player.getInventory().contains(Material.IRON_INGOT, 16) && !player.getInventory().contains(Material.GOLD_INGOT, 16)){
                player.sendMessage(ColorUtil.fixColor("&cNie posiadasz wszystkich przedmiotow, aby kupic ten booster!"));
                return false;
            }
            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 16));
            player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 16));
            player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 16));
            user.setTurboTime(System.currentTimeMillis() + 5 * 1000 * 60);
            player.sendMessage(ColorUtil.fixColor("&aGratulacje! &eStone Booster &azostal zakupiony!"));
            return true;
        }
        if(type == MethodUtil.COLOR){
            if(player.hasPermission("rawcore.boosters.color")){
                player.sendMessage(ColorUtil.fixColor("&cPosiadasz juz ten booster!"));
                return true;
            }
            if(!player.getInventory().contains(Material.DIAMOND, 32) && !player.getInventory().contains(Material.IRON_INGOT, 32) && !player.getInventory().contains(Material.GOLD_INGOT, 32)){
                player.sendMessage(ColorUtil.fixColor("&cNie posiadasz wszystkich przedmiotow, aby kupic ten booster!"));
                return false;
            }
            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 32));
            player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 32));
            player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 32));
            PermissionsEx.getUser(player).addPermission("rawcore.boosters.color");
            player.sendMessage(ColorUtil.fixColor("&aGratulacje! &7C&8o&6l&5o&6r &9B&6o&3o&2s&9t&3e&5r&4 &azostal zakupiony!"));
            return true;
        }
        return false;
    }

}
