package pl.krejzolekpro.rawcore.utils;

import org.bukkit.Material;
import pl.krejzolekpro.rawcore.listeners.BlockBreakListener;

import java.util.ArrayList;
import java.util.List;

public class DropUtil {

    public static List<Material> getDropsByChance(Double chance){
        List<Material> drops = new ArrayList<>();
        if(chance > 12){
            return null;
        }
        for(Material drop : BlockBreakListener.drops.keySet()){
            if(BlockBreakListener.drops.get(drop) <= chance){
                drops.add(drop);
            }
        }
        return drops;
    }
}
