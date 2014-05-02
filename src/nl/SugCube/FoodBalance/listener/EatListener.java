package nl.SugCube.FoodBalance.listener;

import java.util.List;

import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.Food;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EatListener implements Listener {

	public static FoodBalance plugin;
	
	public EatListener(FoodBalance instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		//Initialize variables to work with for easy reference
		ItemStack itemEaten = event.getItem();
		Player player = event.getPlayer();
		List<Food> foods = plugin.getFoodManager().getFoods();
		
		//Getting which food is eaten
		Food food = null; 
		for (int i = 0; i < foods.size(); i++) {
			food = foods.get(i);
			if (itemEaten.getType() == food.getItem()) {
				break;
			}
		}
		
		if (food.getItem() != Material.GOLDEN_APPLE) {
			//Gets the current values of the player's balance
			int carbohydratesCurrent = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
			int proteinsCurrent = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
			int vitaminsCurrent = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
			int waterCurrent = plugin.getValueManager().getValue(FoodType.WATER, player);
			
			//Initialize add-values
			int carbohydrates = 0;
			int proteins = 0;
			int vitamins = 0;
			int water = 0;
			
			//Getting how much all values will increase
			if (food.getValues().getTypes().containsKey(FoodType.CARBOHYDRATE)) {
				carbohydrates = food.getValues().getTypes().get(FoodType.CARBOHYDRATE);
			}
			if (food.getValues().getTypes().containsKey(FoodType.PROTEIN)) {
				proteins = food.getValues().getTypes().get(FoodType.PROTEIN);
			}
			if (food.getValues().getTypes().containsKey(FoodType.VITAMIN)) {
				vitamins = food.getValues().getTypes().get(FoodType.VITAMIN);
			}
			if (food.getValues().getTypes().containsKey(FoodType.WATER)) {
				water = food.getValues().getTypes().get(FoodType.WATER);
			}
			
			//Increase/Decrease current values
			if (carbohydrates != 0) {
				carbohydratesCurrent += carbohydrates;
			} else {
				if (proteins != 0 && vitamins != 0) {
					carbohydratesCurrent -= proteins + vitamins;
				} else {
					carbohydratesCurrent -= (proteins == 0 ? vitamins : proteins);
				}
			}
			
			if (proteins != 0) {
				proteinsCurrent += proteins;
			} else {
				if (carbohydrates != 0 && vitamins != 0) {
					proteinsCurrent -= carbohydrates + vitamins;
				} else {
					proteinsCurrent -= (carbohydrates == 0 ? vitamins : carbohydrates);
				}
			}
			
			if (vitamins != 0) {
				vitaminsCurrent += vitamins;
			} else {
				if (proteins != 0 && carbohydrates != 0) {
					vitaminsCurrent -= proteins + carbohydrates;
				} else {
					vitaminsCurrent -= (proteins == 0 ? carbohydrates : proteins);
				}
			}
			
			waterCurrent += water;
			
			//Save new values
			plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, carbohydratesCurrent);
			plugin.getValueManager().setValue(FoodType.PROTEIN, player, proteinsCurrent);
			plugin.getValueManager().setValue(FoodType.VITAMIN, player, vitaminsCurrent);
			plugin.getValueManager().setValue(FoodType.WATER, player, waterCurrent);
		} else {
			plugin.getValueManager().resetValues(player);
		}
		
	}
	
}
