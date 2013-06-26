package nl.SugCube.FoodBalance.Event;

import nl.SugCube.FoodBalance.Main.Const;
import nl.SugCube.FoodBalance.Main.FoodBalance;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	public static FoodBalance plugin;
	
	public PlayerJoinListener(FoodBalance i) {
		plugin = i;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		plugin.hydration.put(player, Const.HYDRATION_START);
		plugin.damageCause.put(player, null);
		plugin.carbohydrates.put(player, 0);
		plugin.carbohydratesCount.put(player, Const.LACK_OF_COUNTDOWN);
		plugin.proteins.put(player, 0);
		plugin.proteinsCount.put(player, Const.LACK_OF_COUNTDOWN);
		plugin.vitamins.put(player, 0);
		plugin.vitaminsCount.put(player, Const.LACK_OF_COUNTDOWN);
	}
	
}
