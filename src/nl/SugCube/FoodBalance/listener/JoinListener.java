package nl.SugCube.FoodBalance.listener;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	public static FoodBalance plugin;
	
	public JoinListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (plugin.getValueManager().getValue(FoodType.WATER, player) == 0) {
			plugin.getValueManager().resetValues(player);
		}
		
		if (!player.hasPermission("foodbalance")) {
			return;
		}
		
		if (!plugin.getConfig().getBoolean("updates.notify-admin")) {
			return;
		}
		
		player.sendMessage(Broadcast.TAG + " A new update is available! Get it here: " +
				"http://dev.bukkit.org/bukkit-plugins/foodbalance/");
	}
	
}
