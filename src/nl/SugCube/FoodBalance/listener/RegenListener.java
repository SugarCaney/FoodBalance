package nl.SugCube.FoodBalance.listener;

import java.util.Random;

import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class RegenListener implements Listener {

	public static FoodBalance plugin;
	
	public RegenListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		
		if (event.getRegainReason() != RegainReason.SATIATED) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		int proteinValue = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
		
		Random ran = new Random();
		if (proteinValue <= -7) {
			if (ran.nextInt(2) == 0) {
				event.setCancelled(true);
			}
		} else if (proteinValue <= -6) {
			if (ran.nextInt(4) > 0) {
				event.setCancelled(true);
			}
		}
		
	}
	
}
