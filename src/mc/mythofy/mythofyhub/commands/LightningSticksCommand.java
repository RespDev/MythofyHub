package mc.mythofy.mythofyhub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mc.mythofy.mythofycommands.rank.Rank;
import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;

@CommandMeta(rank = Rank.DEVELOPER)
public class LightningSticksCommand extends CoreCommand {
	
    public LightningSticksCommand() {
        super("lightningsticks");
    }

    ItemStack ls;
    
    @Override
    protected void handleCommand(Player player, String[] args) throws CommandException {
    	if (args.length > 0) {
			createLightningStick();
			if (args[0].equalsIgnoreCase("all")) {
				Bukkit.getOnlinePlayers().forEach(p -> player.getInventory().addItem(ls));
				player.sendMessage(ChatColor.GREEN + "Everyone has been given a lightning stick!");
				return;
			}
			if (args[0].equalsIgnoreCase("me")) {
				player.getInventory().addItem(ls);
				player.sendMessage(ChatColor.GREEN + "You have been given a lightning stick!");
				return;
			}
			player.sendMessage(ChatColor.RED + "Usage:");
			player.sendMessage(ChatColor.RED + "/lightningstick all");
			player.sendMessage(ChatColor.RED + "/lightningstick me");
		} else {
			player.sendMessage(ChatColor.RED + "Usage:");
			player.sendMessage(ChatColor.RED + "/lightningstick all");
			player.sendMessage(ChatColor.RED + "/lightningstick me");
		}
    }
    
	private void createLightningStick() {
		ItemStack lightningStick = new ItemStack(Material.STICK);
		ItemMeta meta = lightningStick.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Lightning Stick");
		lightningStick.setItemMeta(meta);

		ls = lightningStick;
	}
}
