package pl.krejzolekpro.rawcore.obejcts;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class RawBlock {

    public static List<RawBlock> blockList = new ArrayList<>();

    private Location location;
    private Material block;
    private Long time;
    private Boolean active;

    public RawBlock(Location location, Material block, Long time){
        this.location = location;
        this.block = block;
        this.time = time;
        this.active = true;
        add(this);
    }

    public Location getLocation() {
        return this.location;
    }

    public Material getBlock() {
        return this.block;
    }

    public Long getTime() {
        return this.time;
    }

    public Boolean getActive() { return this.active; }

    public void delete(){
        this.active = false;
    }

    public static void add(RawBlock block){
        for(RawBlock rawBlock : blockList){
            if(block.getLocation() == rawBlock.getLocation() && rawBlock.getActive()){
                return;
            }
        }
        blockList.add(block);
    }
}
