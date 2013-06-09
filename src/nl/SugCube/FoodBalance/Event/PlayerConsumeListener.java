package nl.SugCube.FoodBalance.Event;

import nl.SugCube.FoodBalance.Main.FoodBalance;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerConsumeListener implements Listener {
	
	public static FoodBalance plugin;
	int value;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getTypeId() == 92) {
				value = plugin.glucose.get(e.getPlayer());
				value += 1;
				plugin.glucose.remove(e.getPlayer());
				plugin.glucose.put(e.getPlayer(), value);
				value = plugin.carbohydrates.get(e.getPlayer());
				value += 1;
				plugin.carbohydrates.remove(e.getPlayer());
				plugin.carbohydrates.put(e.getPlayer(), value);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent e) {
		
		Player player = e.getPlayer();
		
		if (e.getItem().getType() == Material.GOLDEN_CARROT || e.getItem().getType() == Material.GOLDEN_APPLE) {
			value = 0;
			plugin.glucose.remove(e.getPlayer());
			plugin.glucose.put(e.getPlayer(), value);
			plugin.proteins.remove(e.getPlayer());
			plugin.proteins.put(e.getPlayer(), value);
			plugin.vitamins.remove(e.getPlayer());
			plugin.vitamins.put(e.getPlayer(), value);
			plugin.carbohydrates.remove(e.getPlayer());
			plugin.carbohydrates.put(e.getPlayer(), value);
		}

		if (e.getItem().getType() == Material.APPLE || e.getItem().getType() == Material.MELON) {
			value = plugin.glucose.get(e.getPlayer());
			value += 1;
			plugin.glucose.remove(e.getPlayer());
			plugin.glucose.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.PUMPKIN_PIE || e.getItem().getType() == Material.COOKIE) {
			value = plugin.glucose.get(e.getPlayer());
			value += 2;
			plugin.glucose.remove(e.getPlayer());
			plugin.glucose.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.PUMPKIN_PIE) {
			value = plugin.vitamins.get(e.getPlayer());
			value += 1;
			plugin.vitamins.remove(e.getPlayer());
			plugin.vitamins.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.CARROT_ITEM || e.getItem().getType() == Material.APPLE ||
				e.getItem().getType() == Material.MUSHROOM_SOUP || e.getItem().getType() == Material.MELON) {
			value = plugin.vitamins.get(e.getPlayer());
			value += 2;
			plugin.vitamins.remove(e.getPlayer());
			plugin.vitamins.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.ROTTEN_FLESH || e.getItem().getType() == Material.SPIDER_EYE) {
			value = plugin.proteins.get(e.getPlayer());
			value += 1;
			plugin.proteins.remove(e.getPlayer());
			plugin.proteins.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.COOKED_BEEF || e.getItem().getType() == Material.COOKED_CHICKEN ||
				e.getItem().getType() == Material.COOKED_FISH || e.getItem().getType() == Material.GRILLED_PORK ||
				e.getItem().getType() == Material.RAW_BEEF || e.getItem().getType() == Material.RAW_CHICKEN ||
				e.getItem().getType() == Material.RAW_FISH || e.getItem().getType() == Material.PORK) {
			value = plugin.proteins.get(e.getPlayer());
			value += 2;
			plugin.proteins.remove(e.getPlayer());
			plugin.proteins.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.COOKIE || e.getItem().getType() == Material.POISONOUS_POTATO ||
				e.getItem().getType() == Material.PUMPKIN_PIE) {
			value = plugin.carbohydrates.get(e.getPlayer());
			value += 1;
			plugin.carbohydrates.remove(e.getPlayer());
			plugin.carbohydrates.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.BAKED_POTATO || e.getItem().getType() == Material.BREAD ||
				e.getItem().getType() == Material.POTATO) {
			value = plugin.carbohydrates.get(e.getPlayer());
			value += 2;
			plugin.carbohydrates.remove(e.getPlayer());
			plugin.carbohydrates.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.MILK_BUCKET) {
			plugin.hydration.remove(player);
			plugin.hydration.put(player, 615);
			e.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);
		}
		
		if (e.getItem().getType() == Material.POTION) {
			int value = plugin.hydration.get(player);
			plugin.hydration.remove(player);
			if (value < 120) {
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			}
			value += 400;
			if (value >= 615) {
				value = 615;
			}
			plugin.hydration.put(player, value);
		}
		
		if (e.getItem().getType() == Material.MUSHROOM_SOUP) {
			int value = plugin.hydration.get(player);
			plugin.hydration.remove(player);
			if (value < 120) {
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			}
			value += 200;
			if (value >= 615) {
				value = 615;
			}
			plugin.hydration.put(player, value);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
		}
		
	}
	
	public PlayerConsumeListener(FoodBalance i) {
		plugin = i;
	}

}
