package nl.SugCube.FoodBalance.API;

import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.IO.ValueManager;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;

public class FoodBalanceAPI {

	public static FoodBalance plugin;
	
	public FoodBalanceAPI(FoodBalance instance) {
		plugin = instance;
	}
	
	/**
	 * Gets the class handing the input and output of data.
	 * @return (ValueManager)
	 */
	public static ValueManager getValueManager() {
		return plugin.getValueManager();
	}
	
	/**
	 * Mutates the balance when somebody ate eg. a custom food.
	 * @param player (Player) The player who ate the food.
	 * @param carbohydrates (int) CH-mutation.
	 * @param proteins (int) PR-mutation.
	 * @param vitamins (int) VT-mutation.
	 * @param water (int) HY-mutation.
	 */
	public static void eat(Player player, int carbohydrates, int proteins, int vitamins, int water) {
		int ch = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player) + carbohydrates;
		int pt = plugin.getValueManager().getValue(FoodType.PROTEIN, player) + proteins;
		int vt = plugin.getValueManager().getValue(FoodType.VITAMIN, player) + vitamins;
		int hy = plugin.getValueManager().getValue(FoodType.WATER, player) + water;
		
		ch = (ch > 7 ? 7 : (ch < -7 ? -7 : ch));
		pt = (pt > 7 ? 7 : (pt < -7 ? -7 : pt));
		vt = (vt > 7 ? 7 : (vt < -7 ? -7 : vt));
		hy = (hy > 7 ? 7 : (hy < -7 ? -7 : hy));
		
		plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, ch);
		plugin.getValueManager().setValue(FoodType.PROTEIN, player, pt);
		plugin.getValueManager().setValue(FoodType.VITAMIN, player, vt);
		plugin.getValueManager().setValue(FoodType.WATER, player, hy);
	}
	
}
