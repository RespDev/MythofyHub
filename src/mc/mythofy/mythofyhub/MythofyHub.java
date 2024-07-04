package mc.mythofy.mythofyhub;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import mc.mythofy.mythofycommands.MythofyCommands;
import mc.mythofy.mythofyhub.commands.*;
import mc.mythofy.mythofyhub.listeners.*;
import mc.mythofy.mythofyhub.managers.BarManager;
import mc.mythofy.mythofyhub.managers.SidebarManager;
import network.palace.core.command.CoreCommand;

public class MythofyHub extends JavaPlugin {
	
	private static MythofyHub plugin;
	private static FileConfiguration config;
	public String lobbyID;
	private static BarManager bossbar;
	
	@Override
	public void onEnable() {
		// Plugin Instance
		plugin = this;
		bossbar = new BarManager();
		
		// Save Config
		plugin.saveDefaultConfig();
		
		// Load-Variables
		config = plugin.getConfig();
		lobbyID = config.getString("lobby-id");
		
		// Load Plugin
		startScoreboardUpdater();
		bossbar.createBossbar();
		registerCommands();
		registerListeners();
		MythofyCommands.logMessage("Hub", "Enabled");
	}
	
	@Override
	public void onDisable() {
		// Unload Plugin
		bossbar.getBossbar().removeAll();
		MythofyCommands.logMessage("Hub", "Disabled");
	}
	
	private void registerCommands() {
		registerCommand(new LightningSticksCommand());
		registerCommand(new BuildmodeCommand());
		registerCommand(new SetSpawnCommand());
		MythofyCommands.logMessage("Hub", "Commands registered");
	}
	
	private void registerListeners() {
		registerListener(new ArrowListener());
		registerListener(new ClickListener());
		registerListener(new DropListener());
		registerListener(new HungerListener());
		registerListener(new DamageListener());
		registerListener(new ItemMoveListener());
		registerListener(new InventoryClickListener());
		registerListener(new JoinListener());
		registerListener(new BlockListener());
		MythofyCommands.logMessage("Hub", "Listeners registered");
	}
	
	private void startScoreboardUpdater() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MythofyHub.getInstance(), new Runnable() {
			@Override
			public void run() {
				Integer size = Bukkit.getOnlinePlayers().size();		
				if (!(size > 0)) return;		
				for (Player o : Bukkit.getOnlinePlayers()) {
					SidebarManager.refreshBoard(o);
				}
			}
		}, 20L, 60L);
	}
	
    public void setSpawn(Location loc) {
        config.set("spawn", loc);
        plugin.saveConfig();
    }
	
	public static BarManager bar() {
		return bossbar;
	}
	
	private void registerCommand(CoreCommand cmd) {
		MythofyCommands.getInstance().registerCommand(cmd);
	}
	
    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getInstance());
    }
	
	public static MythofyHub getInstance() {
		return plugin;
	}
}
