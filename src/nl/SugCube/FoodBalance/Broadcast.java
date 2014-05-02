package nl.SugCube.FoodBalance;

import org.bukkit.ChatColor;

import nl.SugCube.FoodBalance.SBA.SMeth;
import nl.SugCube.FoodBalance.food.FoodType;

public class Broadcast {

	public static FoodBalance plugin;
	public static final String TAG = ChatColor.GOLD + "[" + ChatColor.GREEN + "FoodBalance" + ChatColor.GOLD + "] ";
	
	public Broadcast(FoodBalance instance) {
		plugin = instance;
	}
	
	public static String getMsg(String node) {
		return SMeth.setColours(plugin.getConfig().getString("messages." + node));
	}
	
	public static String getMsg(Message msg) {
		return SMeth.setColours(plugin.getConfig().getString(msg.getNode()));
	}
	
	public static String getColour(FoodType type) {
		switch (type) {
		case CARBOHYDRATE:
			return Broadcast.getMsg(Message.CARBOHYDRATES_COLOUR);
		case PROTEIN:
			return Broadcast.getMsg(Message.PROTEINS_COLOUR);
		case VITAMIN:
			return Broadcast.getMsg(Message.VITAMINS_COLOUR);
		case WATER:
			return Broadcast.getMsg(Message.WATER_COLOUR);
		}
		return "";
	}
	
}
