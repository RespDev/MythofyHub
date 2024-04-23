package mc.mythofy.mythofyhub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemMoveListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent c) {
		if (c.getSlot() == 0) c.setCancelled(true);
		if (c.getSlot() == 3) c.setCancelled(true);
		if (c.getSlot() == 6) c.setCancelled(true);
		if (c.getSlot() == 9) c.setCancelled(true);
		return;
	}
}