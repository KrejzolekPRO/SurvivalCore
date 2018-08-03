package pl.krejzolekpro.rawcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import pl.krejzolekpro.rawcore.commands.normal.ChallengeCommand;
import pl.krejzolekpro.rawcore.commands.normal.GeneratorCommand;
import pl.krejzolekpro.rawcore.commands.normal.OreCommand;
import pl.krejzolekpro.rawcore.commands.normal.ShopCommand;
import pl.krejzolekpro.rawcore.commands.tools.*;
import pl.krejzolekpro.rawcore.files.Config;
import pl.krejzolekpro.rawcore.listeners.*;
import pl.krejzolekpro.rawcore.obejcts.RawBlock;
import pl.krejzolekpro.rawcore.tasks.ChallengeTask;
import pl.krejzolekpro.rawcore.tasks.RawBlockGeneratorTask;
import pl.socketbyte.opengui.OpenGUI;

public class RawCorePlugin extends JavaPlugin {

    private static RawCorePlugin instance;

    public static RawCorePlugin getInstance(){
        if(instance == null){
            return new RawCorePlugin();
        }
        return instance;
    }

    public void onEnable(){
        saveDefaultConfig();
        instance = this;
        OpenGUI.INSTANCE.register(getInstance());
        Config.load(this);
        registerCommand();
        registerListeners();
        dropsLoader();
        RawBlockGeneratorTask.start(this);
        ChallengeTask.start(this);
    }

    public void onDisable(){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
        RawBlockDebug();
    }

    private void registerCommand(){
        instance.getCommand("sklep").setExecutor(new ShopCommand());
        instance.getCommand("oregenerator").setExecutor(new GeneratorCommand());
        instance.getCommand("rudy").setExecutor(new OreCommand());
        instance.getCommand("challenge").setExecutor(new ChallengeCommand());
        instance.getCommand("vanish").setExecutor(new VanishCommand());
        instance.getCommand("god").setExecutor(new GodCommand());
        instance.getCommand("gamemode").setExecutor(new GamemodeCommand());
        instance.getCommand("tp").setExecutor(new TeleportCommand());
        instance.getCommand("fly").setExecutor(new FlyCommand());
        instance.getCommand("help").setExecutor(new HelpCommand());
        instance.getCommand("helpop").setExecutor(new HelpopCommand());
    }

    private void registerListeners(){
        instance.getServer().getPluginManager().registerEvents(new BlockBreakListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new ASyncPlayerChatListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerFishListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), instance);
    }

    private void RawBlockDebug(){
        for(RawBlock block : RawBlock.blockList){
            if(block.getActive()){
                block.getLocation().getBlock().setType(block.getBlock());
            }
        }
    }

    private void dropsLoader(){
        if(BlockBreakListener.drops.isEmpty()){
            BlockBreakListener.drops.put(Material.DIAMOND_ORE, 3.5);
            BlockBreakListener.drops.put(Material.EMERALD_ORE, 3.0);
            BlockBreakListener.drops.put(Material.GOLD_ORE, 4.0);
            BlockBreakListener.drops.put(Material.IRON_ORE, 7.0);
            BlockBreakListener.drops.put(Material.REDSTONE_ORE, 7.0);
            BlockBreakListener.drops.put(Material.LAPIS_ORE, 2.0);
            BlockBreakListener.drops.put(Material.COAL_ORE, 12.0);
        }
    }
}
