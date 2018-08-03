package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.enums.ChallengeType;
import pl.krejzolekpro.rawcore.obejcts.Challenge;
import pl.krejzolekpro.rawcore.obejcts.RawBlock;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.krejzolekpro.rawcore.utils.DropUtil;
import pl.socketbyte.opengui.ColorUtil;
import pl.socketbyte.opengui.ItemBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BlockBreakListener implements Listener {

    public static HashMap<Material, Double> drops = new HashMap<Material, Double>();
    private Random random = new Random();
    private Challenge challenge = Challenge.getInstance();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(challenge.isOn()){
            if(challenge.getChallengeType() == ChallengeType.BREAK){
                Material block = event.getBlock().getType();
                if(challenge.getBlock() == block){
                    Player player = event.getPlayer();
                    int amount = 0;
                    if(challenge.getAmountList().get(player) == null){
                        amount = 0 +1;
                    } else {
                        amount = challenge.getAmountList().get(player) +1;
                    }
                    challenge.getAmountList().put(player, amount);
                    player.sendMessage(ColorUtil.fixColor("&e&l[Wyzwanie] &eStatus wyzwania: " + amount + "&e/" + challenge.getAmountWinner() + "."));
                    if(amount++ >= challenge.getAmountWinner()){
                        if(challenge.getWinner() == null){
                            challenge.setWinner(player);
                            challenge.setChallengeType(ChallengeType.NONE);
                            challenge.getAmountList().clear();
                            challenge.sendInfo();
                        }
                    }
                }
            }
        }
        if(event.getBlock().getType() == Material.IRON_ORE || event.getBlock().getType() == Material.GOLD_ORE || event.getBlock().getType() == Material.DIAMOND_ORE || event.getBlock().getType() == Material.EMERALD_ORE || event.getBlock().getType() == Material.LAPIS_ORE || event.getBlock().getType() == Material.REDSTONE_ORE || event.getBlock().getType() == Material.COAL_ORE){
            Player player = event.getPlayer();
            long time = player.hasPermission("rawcore.boosters.generator") ? 20*60*30*1000 : 20*60*45*1000;
            new RawBlock(event.getBlock().getLocation(), event.getBlock().getType(), System.currentTimeMillis() + time);
            if (event.getBlock().getType() == Material.IRON_ORE) {
                if (!player.hasPermission("rawcore.boosters.iron")) {
                    return;
                }
                if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                    return;
                }
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(Material.IRON_INGOT, 1).getItem());
                return;
            }
            if (event.getBlock().getType() == Material.GOLD_ORE) {
                if (!player.hasPermission("rawcore.boosters.gold")) {
                    return;
                }
                if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                    return;
                }
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event. getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(Material.GOLD_INGOT, 1).getItem());
                return;
            }
        }
        if(event.getBlock().getType() == Material.STONE){
            User user = User.get(event.getPlayer().getName());
            if(user.hasTurbo()){
                Double chance = random.nextDouble() * 100;
                List<Material> drops = DropUtil.getDropsByChance(chance);
                if(drops != null && drops.size() > 0) {
                    int amount = drops.size();
                    Material rolled = amount == 1 ? drops.get(0) : drops.get(random.nextInt(amount -1));
                    setBlock(event.getBlock().getLocation(), rolled);
                    event.getPlayer().sendMessage(ColorUtil.fixColor("&e&l[DROP] &eZnaleziono rude " + rolled.toString().replace("MATERIAL_", "").replace("DIAMOND_ORE", "diamentu").replace("EMERALD_ORE", "szmaragdu").replace("GOLD_ORE", "zlota").replace("IRON_ORE", "zelaza").replace("REDSTONE_ORE", "czerwonego proszku").replace("LAPIS_ORE", "lazurytu").replace("COAL_ORE", "wegla") + ", wystala ona wykopana automatycznie."));
                    return;
                }
            }
        }
    }

    private void setBlock(Location loc, Material block){
        loc.getWorld().getBlockAt(loc).setType(block);
    }
}
