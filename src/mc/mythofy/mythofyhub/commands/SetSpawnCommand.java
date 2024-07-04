package mc.mythofy.mythofyhub.commands;

import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import mc.mythofy.mythofycommands.rank.Rank;
import mc.mythofy.mythofyhub.MythofyHub;

@CommandMeta(rank = Rank.DEVELOPER, description = "Set the world spawn for player login")
public class SetSpawnCommand extends CoreCommand {

    public SetSpawnCommand() {
        super("setspawn");
    }

    @Override
    protected void handleCommand(Player player, String[] args) throws CommandException {
        MythofyHub.getInstance().setSpawn(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Spawn set to your position");
    }
}