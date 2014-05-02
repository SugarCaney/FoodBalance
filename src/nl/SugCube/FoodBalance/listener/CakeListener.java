package nl.SugCube.FoodBalance.listener;

import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CakeListener implements Listener {

	public static FoodBalance plugin;
	
	public CakeListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (event.getClickedBlock().getType() == Material.CAKE_BLOCK) {
			int currentCarbohydrates = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
			currentCarbohydrates = (currentCarbohydrates + 2 > 7 ? 7 : currentCarbohydrates + 2);
			plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, currentCarbohydrates);
		}
	}
	
}
