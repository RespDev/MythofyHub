package mc.mythofy.mythofyhub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import mc.mythofy.mythofyhub.managers.GuiManager;

public class ClickListener implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent c) {
		Player p = c.getPlayer();
		
		if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
			GuiManager.createServerSelector(p);
			p.openInventory(GuiManager.getServerSelector());
			return;
		}
		else if (p.getInventory().getItemInMainHand().getType() == Material.STICK) {
			for (Player o : Bukkit.getOnlinePlayers()) {
				o.getWorld().strikeLightningEffect(o.getLocation());
			}
			return;
		}
		return;
	}
}
