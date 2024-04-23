package mc.mythofy.mythofyhub.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BlockListener implements Listener {

	private static ArrayList<UUID> allowBuild = new ArrayList<UUID>();
	
	// Anti-Griefing
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent event) {
		if (allowBuild.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent event) {
		if (allowBuild.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		if (allowBuild.contains(event.getPlayer().getUniqueId())) allowBuild.remove(event.getPlayer().getUniqueId());
	}
	
	public static void toggleBuild(Player p) {
		UUID uuid = p.getUniqueId();
		if (allowBuild.contains(uuid)) {
			allowBuild.remove(uuid);
			p.sendMessage(ChatColor.RED + "Buildmode disabled.");
		}
		else {	
			allowBuild.add(uuid);
			p.sendMessage(ChatColor.GREEN + "Buildmode enabled.");
		}
		return;
	}
}
