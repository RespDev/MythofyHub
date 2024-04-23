package mc.mythofy.mythofyhub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {

	  @EventHandler
	  public void onFoodLevelChange(FoodLevelChangeEvent e)
	  {
		  Player p = (Player) e.getEntity();
		  e.setCancelled(true);
		  p.setFoodLevel(20);
	  }
}
