package mc.mythofy.mythofyhub.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowListener implements Listener {

	@EventHandler
	public void onArrowLand(ProjectileHitEvent land) {
		if (!(land.getEntity().getShooter() instanceof Player)) return;
		if (!(land.getEntity().getType() == EntityType.ARROW)) return;
		
		Player p = (Player) land.getEntity().getShooter();
		
		Location arrowLoc = land.getEntity().getLocation();
		p.teleport(arrowLoc);
		land.getEntity().remove();
	}
}
