package mc.mythofy.mythofyhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent d) {
		d.setCancelled(true);
	}
}
