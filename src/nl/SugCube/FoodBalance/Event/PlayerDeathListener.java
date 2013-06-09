package nl.SugCube.FoodBalance.Event;

import nl.SugCube.FoodBalance.Main.Const;
import nl.SugCube.FoodBalance.Main.FoodBalance;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
	public static FoodBalance plugin;
	public static String deathHydration = "%player% died of dehydration";
	public static String deathCarbohydrates = "%player% died due to a lack of carbohydrates";
	public static String deathCarbohydratesExtra = "%player% ate too much carbohydrates";
	public static String deathVitamins = "%player% didn't eat his veggies";
	public static String deathVitaminsExtra = "%player% ate too much veggies";
	public static String deathProteins = "%player% died due to a lack of proteins";
	public static String deathProteinsExtra = "%player% ate too much proteins";
	public static String deathGlucose = "%player% ate too much sugar";
	
	public PlayerDeathListener(FoodBalance i) {
		plugin = i;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();			
			if (plugin.damageCause.get(player).equalsIgnoreCase("dehydration")) {
				e.setDeathMessage(deathHydration.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("carbohydrates")) {
				e.setDeathMessage(deathCarbohydrates.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("carbohydratesExtra")) {
				e.setDeathMessage(deathCarbohydratesExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("vitamins")) {
				e.setDeathMessage(deathVitamins.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("vitaminsExtra")) {
				e.setDeathMessage(deathVitaminsExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("proteins")) {
				e.setDeathMessage(deathProteins.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("proteinsExtra")) {
				e.setDeathMessage(deathProteinsExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("glucose")) {
				e.setDeathMessage(deathGlucose.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			}
		}
		
		plugin.hydration.remove(e.getEntity());
		plugin.hydration.put(e.getEntity(), Const.HYDRATION_START);
		
		plugin.carbohydrates.remove(e.getEntity());
		plugin.carbohydrates.put(e.getEntity(), 0);
		plugin.carbohydratesCount.remove(e.getEntity());
		plugin.carbohydratesCount.put(e.getEntity(), Const.LACK_OF_COUNTDOWN);

		plugin.proteins.remove(e.getEntity());
		plugin.proteins.put(e.getEntity(), 0);
		plugin.proteinsCount.remove(e.getEntity());
		plugin.proteinsCount.put(e.getEntity(), Const.LACK_OF_COUNTDOWN);
		
		plugin.vitamins.remove(e.getEntity());
		plugin.vitamins.put(e.getEntity(), 0);
		plugin.vitaminsCount.remove(e.getEntity());
		plugin.vitaminsCount.put(e.getEntity(), Const.LACK_OF_COUNTDOWN);
		
		plugin.glucose.remove(e.getEntity());
		plugin.glucose.put(e.getEntity(), 0);
		plugin.glucoseCount.remove(e.getEntity());
		plugin.glucoseCount.put(e.getEntity(), Const.LACK_OF_COUNTDOWN);
		
		plugin.damageCause.remove(e.getEntity());
		plugin.damageCause.put(e.getEntity(), null);
	}
	
	public static void setDeathMessageHydration(String s) {
		deathHydration = s;
	}
	
	public static void setDeathMessageCarbohydrate(String s) {
		deathCarbohydrates = s;
	}
	
	public static void setDeathMessageCarbohydrateTooMuch(String s) {
		deathCarbohydratesExtra = s;
	}
	
	public static void setDeathMessageVitamins(String s) {
		deathVitamins = s;
	}
	
	public static void setDeathMessageVitaminsExtra(String s) {
		deathVitaminsExtra = s;
	}
	
	public static void setDeathMessageProteins(String s) {
		deathProteins = s;
	}
	
	public void setDeathMessageProteinsExtra(String s) {
		deathProteinsExtra = s;
	}
	
	public void setDeathMessageGlucose(String s) {
		deathGlucose = s;
	}

}