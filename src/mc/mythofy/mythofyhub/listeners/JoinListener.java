package mc.mythofy.mythofyhub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mc.mythofy.mythofyhub.MythofyHub;

public class JoinListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent j) {
		Location spawn = (Location) MythofyHub.getInstance().getConfig().get("spawn");
		Player player = (Player) j.getPlayer();
		
		// Important Join Stuff
		player.setGameMode(GameMode.SURVIVAL);
		MythofyHub.bar().addPlayer(player);		
		if (spawn != null) player.teleport(spawn);
		
		// Join Message
		Bukkit.getScheduler().runTaskLater(MythofyHub.getInstance(), new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 150; i++) player.sendMessage(" ");
				player.sendMessage(ChatColor.AQUA + "-----------------------------------------------");
				player.sendMessage("");
				player.sendMessage(ChatColor.GRAY + "                       Welcome to " + ChatColor.GOLD + "" + ChatColor.BOLD + "Mythofy" + ChatColor.RESET + ChatColor.GRAY + "!");
				player.sendMessage(ChatColor.GRAY + "    Use the Game Selector in your hotbar to select a game.");
				player.sendMessage("");
				player.sendMessage(ChatColor.GRAY + "         Join our discord by typing /discord in chat.");
				player.sendMessage(ChatColor.GRAY + "            Purchase ranks at " + ChatColor.GOLD + "store.mythofy.net!");
				player.sendMessage("");
				player.sendMessage(ChatColor.AQUA + "-----------------------------------------------");
			}
		}, 15);
			
		// Items
		ItemStack gameSelector = new ItemStack(Material.COMPASS);
		ItemMeta meta = gameSelector.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Game Selector");
		gameSelector.setItemMeta(meta);
		
		ItemStack cosmeticSelector = new ItemStack(Material.CHEST);
		ItemMeta cosmeticMeta = cosmeticSelector.getItemMeta();
		cosmeticMeta.setDisplayName(ChatColor.GREEN + "Cosmetics");
		cosmeticSelector.setItemMeta(cosmeticMeta);
		
		ItemStack tele = new ItemStack(Material.BOW);
		ItemMeta teleMeta = tele.getItemMeta();
		teleMeta.setDisplayName(ChatColor.DARK_PURPLE + "Teleport Bow");
		teleMeta.setUnbreakable(true);
		teleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		teleMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		teleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		teleMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		tele.setItemMeta(teleMeta);
		
		ItemStack arrow = new ItemStack(Material.ARROW);
		
		// Inventory
		player.getInventory().clear();
		player.getInventory().setItem(0, gameSelector);
		player.getInventory().setItem(3, tele);
		player.getInventory().setItem(6, cosmeticSelector);
		player.getInventory().setItem(9, arrow);
	}
}
