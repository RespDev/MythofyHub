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

		// Factions item
		ItemStack factions = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta = factions.getItemMeta();

		String players = PlaceholderAPI.setPlaceholders(p, "%bungee_factions%");

		meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Factions");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "");
		lore.add(ChatColor.GRAY + "Our unique take on the Factions gamemode!");
		lore.add(ChatColor.GRAY + "");
		lore.add(ChatColor.GRAY + "Players: " + ChatColor.AQUA + players);
		lore.add(ChatColor.GRAY + "Version: " + ChatColor.AQUA + "1.7-1.20.4");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		factions.setItemMeta(meta);

		// Set menu items
		selector.setItem(13, factions);
	}

	public static Inventory getServerSelector() {
		return selector;
	}
}
