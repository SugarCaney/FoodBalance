package nl.SugCube.FoodBalance.IO;

import java.sql.ResultSet;
import java.sql.Statement;

import nl.SugCube.FoodBalance.Const;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.entity.Player;

public class ValueManager {

	public static FoodBalance plugin;
	
	public ValueManager(FoodBalance instance) {
		plugin = instance;
	}
	
	public void resetValues(Player player) {
		String playerUuid = player.getUniqueId().toString();
		
		setValue(FoodType.CARBOHYDRATE, player, 0);
		setValue(FoodType.PROTEIN, player, 0);
		setValue(FoodType.VITAMIN, player, 0);
		setValue(FoodType.WATER, player, Const.HYDRATION_MAX);
	}
	
	public void setValue(FoodType type, Player player, int value) {
		String playerUuid = player.getUniqueId().toString();
		String foodType = type.toString();
		
		if (plugin.getConfig().getBoolean("mysql.enabled")) {
			/*
			 * Get data from mysql connection
			 */
			try {
				Statement statement = plugin.getConnection().createStatement();
				statement.executeUpdate("INSERT INTO " + foodType + "(`uuid`,`value`)" +
						"VALUES ('" + playerUuid + "','" + value + "');");
				return;
			} catch (Exception exc) {
				plugin.getLogger().severe("Failed getting data from the database. Using flatfile instead.");
			}
		}
		
		/*
		 * Write data to flatfile (data.yml) if sql doesnt work.
		 */
		plugin.getData().set("values." + foodType + "." + playerUuid, value);
	}
	
	public int getValue(FoodType type, Player player) {
		String playerUuid = player.getUniqueId().toString();
		String foodType = type.toString();
		
		if (plugin.getConfig().getBoolean("mysql.enabled")) {
			/*
			 * Get data from mysql connection
			 */
			try {
				Statement statement = plugin.getConnection().createStatement();
				ResultSet set = statement.executeQuery("SELECT * FROM " + foodType + " WHERE uuid='" + playerUuid +
						"';");
				set.next();
				
				if (set.getString(playerUuid) == null) {
					if (type == FoodType.WATER) {
						setValue(type, player, Const.HYDRATION_MAX);
						return Const.HYDRATION_MAX;
					} else {					
						setValue(type, player, 0);
						return 0;
					}
				} else {
					return set.getInt(foodType);
				}
			} catch (Exception exc) {
				plugin.getLogger().severe("Failed getting data from the database. Using flatfile instead.");
			}
		}
		
		/*
		 * Get data from flatfile (data.yml) if sql doesnt work.
		 */
		if (plugin.getData().isSet("values." + foodType + "." + playerUuid)) {
			return plugin.getData().getInt("values." + foodType + "." + playerUuid);
		} else {
			if (type == FoodType.WATER) {
				plugin.getData().set("values." + foodType + "." + playerUuid, Const.HYDRATION_MAX);
				return Const.HYDRATION_MAX;
			} else {
				plugin.getData().set("values." + foodType + "." + playerUuid, 0);
				return 0;
			}
		}
	}
	
}