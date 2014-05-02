package nl.SugCube.FoodBalance.listener;

import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class FightListener implements Listener {

	public static FoodBalance plugin;
	
	public FightListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getDamager();
		deductOne(player);
	}
	
	@EventHandler
	public void onProjectileLaunc(ProjectileLaunchEvent event) {
		if (!(event.getEntity().getShooter() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity().getShooter();
		deductOne(player);
	}
	
	public void deductOne(Player player) {
		int currentWater = plugin.getValueManager().getValue(FoodType.WATER, player);
		
		if (currentWater <= 0) {
			return;
		}
		
		currentWater -= 1;
		plugin.getValueManager().setValue(FoodType.WATER, player, currentWater);
	}
	
}
