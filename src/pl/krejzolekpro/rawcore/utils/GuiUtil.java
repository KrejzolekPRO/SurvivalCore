package pl.krejzolekpro.rawcore.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.enums.ChallengeType;
import pl.krejzolekpro.rawcore.listeners.BlockBreakListener;
import pl.krejzolekpro.rawcore.obejcts.Challenge;
import pl.krejzolekpro.rawcore.obejcts.User;
import pl.socketbyte.opengui.*;

import java.util.Arrays;

public class GuiUtil {

    public static void shopGUI(Player player){
        User user = User.get(player.getName());
        GUI gui = new GUI("&6Sklep z boosterami:", Rows.ONE);
        SimpleGUI simpleGUI = new SimpleGUI(gui);
        simpleGUI.getGuiSettings().setCanEnterItems(false);
        simpleGUI.getGuiSettings().setCanDrag(false);
        simpleGUI.addItem(new ItemBuilder(Material.IRON_INGOT).setName("&7Iron Booster!").setLore("&ePosiadajac ten booster,", "&ekopiac zelazo, bedzie ono", "&eod razu przetopione.", "", "&6Pamietaj! Jedwabnym dotkiem", "&6rudy wypadaja normalnie.", "", "&eKoszt: 512 sztabek zelaza.", "", (player.hasPermission("rawshop.boosters.iron") ? "&aPosiadasz ten booster." : "&cNie posiadasz tego boostera.")), event ->
                MethodUtil.check(player, MethodUtil.IRON)
                );
        simpleGUI.addItem(new ItemBuilder(Material.GOLD_INGOT).setName("&7Gold Booster!").setLore("&ePosiadajac ten booster,", "&ekopiac zloto, bedzie ono", "&eod razu przetopione.", "", "&6Pamietaj! Jedwabnym dotkiem", "&6rudy wypadaja normalnie.", "", "&eKoszt: 256 sztabek zlota.", "", (player.hasPermission("rawshop.boosters.gold") ? "&aPosiadasz ten booster." : "&cNie posiadasz tego boostera.")), event ->
                MethodUtil.check(player, MethodUtil.GOLD)
        );
        simpleGUI.addItem(new ItemBuilder(Material.WATCH).setName("&7Generator Booster!").setLore("&ePosiadajac ten booster,", "&ekopiac rudy, beda one", "&eszybciej sie regenerowac.", "", (player.hasPermission("rawshop.boosters.generator") ? "&6Aktualnie: 30 minut." : "&6Aktualnie: 45 minut."), "", "&eKoszt: 256 diamentow.", "", (player.hasPermission("rawshop.boosters.generator") ? "&aPosiadasz ten booster." : "&cNie posiadasz tego boostera.")), event ->
                MethodUtil.check(player, MethodUtil.GENERATOR)
        );
        simpleGUI.addItem(new ItemBuilder(Material.STONE).setName("&7Stone Booster!").setLore("&ePosiadajac ten booster,", "&ekopiac kamien masz sznase", "&eze pojawi sie ruda.", "&eSzanse na pojawienie sie rudy,", "&emozesz sprawdzic pod: /rudy.", "", "&6Czas trwania: 5 minut.", "", "&eKoszt: 16 diamentow, 16 zlota, 16 zelaza.", "", (user.hasTurbo() ? "&aPosiadasz ten booster." : "&cNie posiadasz tego boostera.")), event ->
                MethodUtil.check(player, MethodUtil.STONE)
        );
        simpleGUI.addItem(new ItemBuilder(Material.BOOK).setName("&7C&8o&6l&5o&6r &9B&6o&3o&2s&9t&3e&5r&4!").setLore("&ePosiadajac ten booster,", "&ebedziesz mogl pisac", "&ena czacie kolorkami.", "", "&eKoszt: 32 diamentow, 32 zlota, 32 zelaza.", "", (player.hasPermission("rawcore.boosters.color") ? "&aPosiadasz ten booster." : "&cNie posiadasz tego boostera.")), event ->
                MethodUtil.check(player, MethodUtil.COLOR)
        );
        simpleGUI.openInventory(player);
    }

    public static void oreGUI(Player player){
        User user = User.get(player.getName());
        GUI gui = new GUI("&6Drop Booster:", Rows.ONE);
        SimpleGUI simpleGUI = new SimpleGUI(gui);
        simpleGUI.getGuiSettings().setCanEnterItems(false);
        simpleGUI.getGuiSettings().setCanDrag(false);
        simpleGUI.addItem(new ItemBuilder(Material.DIAMOND_ORE).setLore(Arrays.asList("&eSzansa: 3.5%.")));
        simpleGUI.addItem(new ItemBuilder(Material.EMERALD_ORE).setLore(Arrays.asList("&eSzansa: 3.0%.")));
        simpleGUI.addItem(new ItemBuilder(Material.GOLD_ORE).setLore(Arrays.asList("&eSzansa: 4.0%.")));
        simpleGUI.addItem(new ItemBuilder(Material.IRON_ORE).setLore(Arrays.asList("&eSzansa: 7.0%.")));
        simpleGUI.addItem(new ItemBuilder(Material.REDSTONE_ORE).setLore(Arrays.asList("&eSzansa: 6.0%.")));
        simpleGUI.addItem(new ItemBuilder(Material.LAPIS_ORE).setLore(Arrays.asList("&eSzansa: 2.0%.")));
        simpleGUI.addItem(new ItemBuilder(Material.COAL_ORE).setLore(Arrays.asList("&eSzansa: 12.0%.")));
        simpleGUI.setItem(9, new ItemBuilder(Material.PAPER).setName("&eINFORMACJE").setLore(Arrays.asList("&eBooster: " + (user.hasTurbo() ? "&aaktywny&e." : "&cnieaktywny&e."))));
        simpleGUI.openInventory(player);
    }

    public static void confirmationGUI(Player player, ChallengeType type, Material block, Integer amount){
        GUI gui = new GUI("&aPotwierdzenie:", Rows.ONE);
        SimpleGUI simpleGUI = new SimpleGUI(gui);
        simpleGUI.getGuiSettings().setCanEnterItems(false);
        simpleGUI.getGuiSettings().setCanDrag(false);
        simpleGUI.setItem(4, new ItemBuilder(block).setName("&aKliknij, aby potwierdzic.").setLore(Arrays.asList("&eTyp: " + ReplaceUtil.replaceChallenge(type.name()).toLowerCase(), "&ePrzedmiot: " + block.toString().toLowerCase(), "&eIlosc: " + amount.toString())), event -> Challenge.getInstance().create(player, type, block, amount));
        simpleGUI.openInventory(player);
    }
}
