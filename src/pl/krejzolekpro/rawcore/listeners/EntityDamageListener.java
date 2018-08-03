package pl.krejzolekpro.rawcore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import pl.krejzolekpro.rawcore.obejcts.User;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            User user = User.get(((Player) event.getEntity()).getPlayer().getName());
            if(user.hasGod() || user.hasVanish()) {
                event.setCancelled(true);
            }
        }
    }
}
