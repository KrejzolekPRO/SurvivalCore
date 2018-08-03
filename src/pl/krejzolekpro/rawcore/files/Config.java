package pl.krejzolekpro.rawcore.files;

import org.bukkit.configuration.file.FileConfiguration;
import pl.krejzolekpro.rawcore.RawCorePlugin;

import java.util.Arrays;
import java.util.List;

public class Config {

    public static String TAG = "CONFIG.YML";
    public static List<String> HELP_MESSAGE = Arrays.asList("Zmien w CONFIG.YML.");

    public static void load(RawCorePlugin plugin){
        FileConfiguration config = plugin.getConfig();
        TAG = config.getString("tag");
        HELP_MESSAGE = config.getStringList("help-message");
    }
}
