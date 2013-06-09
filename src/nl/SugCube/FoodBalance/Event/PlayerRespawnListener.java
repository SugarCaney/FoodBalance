package nl.SugCube.FoodBalance.Event;

import nl.SugCube.FoodBalance.Main.Const;
import nl.SugCube.FoodBalance.Main.FoodBalance;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

	public static FoodBalance plugin;
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		
		Player player = e.getPlayer();
		
		plugin.hydration.remove(player);
		plugin.hydration.put(player, Const.HYDRATION_START);
		
		plugin.carbohydrates.remove(player);
		plugin.carbohydrates.put(player, 0);
		plugin.carbohydratesCount.remove(player);
		plugin.carbohydratesCount.put(player, Const.LACK_OF_COUNTDOWN);

		plugin.proteins.remove(player);
		plugin.proteins.put(player, 0);
		plugin.proteinsCount.remove(player);
		plugin.proteinsCount.put(player, Const.LACK_OF_COUNTDOWN);
		
		plugin.vitamins.remove(player);
		plugin.vitamins.put(player, 0);
		plugin.vitaminsCount.remove(player);
		plugin.vitaminsCount.put(player, Const.LACK_OF_COUNTDOWN);
		
		plugin.glucose.remove(player);
		plugin.glucose.put(player, 0);
		plugin.glucoseCount.remove(player);
		plugin.glucoseCount.put(player, Const.LACK_OF_COUNTDOWN);
		
		plugin.damageCause.remove(player);
		plugin.damageCause.put(player, null);
		
	}
	
	public PlayerRespawnListener(FoodBalance i) {
		plugin = i;
	}
	
}
