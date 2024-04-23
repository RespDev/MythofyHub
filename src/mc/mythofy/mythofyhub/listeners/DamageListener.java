package mc.mythofy.mythofyhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

	// Anti-Damage
	@EventHandler
	public void onDamage(EntityDamageEvent d) {
		d.setCancelled(true);
	}
}
