package nl.SugCube.FoodBalance.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Stats {

	public static FoodBalance plugin;
	
	public static void show(Player player) {
	
		double total = plugin.carbohydrates.get(player) + plugin.vitamins.get(player) + plugin.proteins.get(player);
		double totalGlucose = total + plugin.glucose.get(player);
		int count = 0;
		double partCarbo = 20.0, partProtein = 20.0, partVitamin = 20.0, partGlucose = 20.0;
		String hydrationStats = "", carbohydratesStats = "", proteinsStats = "", vitaminsStats = "", glucoseStats = "";
		
		partCarbo = Const.STAT_BAR_SIZE * (plugin.carbohydrates.get(player) / total);
		partProtein = Const.STAT_BAR_SIZE * (plugin.proteins.get(player) / total);
		partVitamin = Const.STAT_BAR_SIZE * (plugin.vitamins.get(player) / total);
		partGlucose = Const.STAT_BAR_SIZE * (plugin.glucose.get(player) / totalGlucose);
		
		for (int i = 1; i <= Const.STAT_BAR_SIZE; i++) {
			if (i <= partCarbo) {
				carbohydratesStats += "#";
			} else {
				carbohydratesStats += "-";
			}
		}
		for (int j = 1; j <= Const.STAT_BAR_SIZE; j++) {
			if (j <= partProtein) {
				proteinsStats += "#";
			} else {
				proteinsStats += "-";
			}
		}
		for (int k = 1; k <= Const.STAT_BAR_SIZE; k++) {
			if (k <= partVitamin) {
				vitaminsStats += "#";
			} else {
				vitaminsStats += "-";
			}
		}
		for (int l = 1; l <= Const.STAT_BAR_SIZE; l++) {
			if (l <= partGlucose) {
				glucoseStats += "#";
			} else {
				glucoseStats += "-";
			}
		}
		
		for (int m = 1; m <= plugin.hydration.get(player) / Const.STAT_BAR_HYDRATION; m++) {
			hydrationStats += "#";
			count++;
		}
		for (int n = count; n < Const.STAT_BAR_SIZE; n++) {
			hydrationStats += "-";
		}

		player.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "-----{ " + ChatColor.GREEN + "FoodBalance " +
				ChatColor.GOLD +	"v1 MrSugarCaney" + ChatColor.YELLOW + " }-----" + ChatColor.GREEN + "**");
		player.sendMessage(ChatColor.AQUA + "* [" + hydrationStats + "] Hydration");
		player.sendMessage(ChatColor.YELLOW + "* [" + carbohydratesStats + "] Carbohydrates (" + (int) (partCarbo * 5) + "%)");
		player.sendMessage(ChatColor.RED + "* [" + proteinsStats + "] Proteins (" + (int) (partProtein * 5) + "%)");
		player.sendMessage(ChatColor.GREEN + "* [" + vitaminsStats + "] Vitamins (" + (int) (partVitamin * 5) + "%)");
		player.sendMessage(ChatColor.WHITE + "* [" + glucoseStats + "] Glucose (" + (int) (partGlucose * 5) + "%)");
		player.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "-------------------" + ChatColor.GOLD + "*" + 
				ChatColor.YELLOW + "-------------------" + ChatColor.GREEN + "**");
		
	}
	
	public Stats(FoodBalance i) {
		plugin = i;
	}
	
}
