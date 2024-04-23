package mc.mythofy.mythofyhub.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import mc.mythofy.mythofyhub.managers.GuiManager;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent click) {
		if (!click.getInventory().equals(GuiManager.getServerSelector()))
			return;
		if (click.getCurrentItem() == null)
			return;
		if (click.getCurrentItem().getItemMeta() == null)
			return;

		click.setCancelled(true);
		Player p = (Player) click.getWhoClicked();

		// Click Functions
		if (click.getSlot() == 13) {
			p.closeInventory();
			p.sendMessage(ChatColor.GREEN + "Currently in development.");
			return;
		}
	}
}
