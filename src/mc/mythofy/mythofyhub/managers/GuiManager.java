package mc.mythofy.mythofyhub.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.clip.placeholderapi.PlaceholderAPI;

public class GuiManager {

	private static Inventory selector;

	public static void createServerSelector(Player p) {
		selector = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Server Selector");

		// Lifesteal Gamemode
		ItemStack lifesteal = new ItemStack(Material.GRASS);
		ItemMeta meta = lifesteal.getItemMeta();

		String lifestealPlayers = PlaceholderAPI.setPlaceholders(p, "%bungee_lifesteal%");

		meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Lifesteal");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "");
		lore.add(ChatColor.GRAY + "Our unique take on the Lifesteal gamemode!");
		lore.add(ChatColor.GRAY + "Fun SMP with hearts, events, and much more.");
		lore.add(ChatColor.GRAY + "");
		lore.add(ChatColor.GRAY + "Players: " + ChatColor.AQUA + lifestealPlayers);
		lore.add(ChatColor.GRAY + "Version: " + ChatColor.AQUA + "1.7-1.20.4");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lifesteal.setItemMeta(meta);

		// Set menu items
		selector.setItem(13, lifesteal);
	}

	public static Inventory getServerSelector() {
		return selector;
	}
}
