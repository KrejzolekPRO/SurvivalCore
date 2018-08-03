package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.krejzolekpro.rawcore.obejcts.User;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();
            User user = User.get(player.getName());
            if(user.hasGod()){
                event.setCancelled(true);
            }
            if(user.hasVanish()){
                event.setCancelled(true);
            }
        }
    }
}
