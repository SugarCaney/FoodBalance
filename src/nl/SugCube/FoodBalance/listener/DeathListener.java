package nl.SugCube.FoodBalance.listener;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

	public static FoodBalance plugin;
	
	public DeathListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getEntity().getLastDamageCause() != null) {
			if (event.getEntity().getLastDamageCause().getCause() == DamageCause.WITHER) {
				if (plugin.getValueManager().getValue(FoodType.PROTEIN, event.getEntity()) <= -6) {
					event.setDeathMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_LACK));
					plugin.getValueManager().resetValues(event.getEntity());
					return;
				}
			}
		}
		
		Player player = (Player) event.getEntity();
		String uuid = player.getUniqueId().toString();
		
		if (plugin.getDeathMessages().getDeathMessageBans().contains(uuid)) {
			event.setDeathMessage(null);
			plugin.getDeathMessages().getDeathMessageBans().remove(uuid);
		}
		
		plugin.getValueManager().resetValues(player);
	}
	
}
