package nl.SugCube.FoodBalance.Main;

import org.bukkit.ChatColor;

public class Messages {

	public static FoodBalance plugin;
	
	final public static String DEHYDRATION_STAGE_2 = ChatColor.AQUA + "I'm a little dizzy, I wonder why...";
	final public static String DEHYDRATION_STAGE_3 = ChatColor.AQUA + "I'm very exhausted, I need water!";
	final public static String DEHYDRATION_STAGE_4 = ChatColor.AQUA + "Water... Water...";
	final public static String CARBOHYDRATES_STAGE_1 = ChatColor.YELLOW + "I'm feeling a bit low.";
	final public static String CARBOHYDRATES_STAGE_2 = ChatColor.YELLOW + "My energy is draining drasticly!";
	final public static String CARBOHYDRATES_STAGE_3 = ChatColor.YELLOW + "Need... Carbohydrates... Need...";
	final public static String CARBOHYDRATES_MUCH_STAGE_1 = ChatColor.YELLOW + "Wow, I've got much energy.";
	final public static String CARBOHYDRATES_MUCH_STAGE_2 = ChatColor.YELLOW + "Help, I'm spazzing out!";
	final public static String CARBOHYDRATES_MUCH_STAGE_3 = ChatColor.YELLOW + "Too much carbohydrates!!!";
	final public static String VITAMINS_STAGE_1 = ChatColor.GREEN + "I'm feeling a bit low.";
	final public static String VITAMINS_STAGE_2 = ChatColor.GREEN + "I feel a bit ill.";
	final public static String VITAMINS_STAGE_3 = ChatColor.GREEN + "I wish I ate my veggies...";
	final public static String VITAMINS_MUCH_STAGE_1 = ChatColor.GREEN + "I'm feeling top fit!";
	final public static String VITAMINS_MUCH_STAGE_2 = ChatColor.GREEN + "Argh... Feeling a bit dizzy.";
	final public static String VITAMINS_MUCH_STAGE_3 = ChatColor.GREEN + "I'm feeling a little ill. Too many veggies...";
	final public static String PROTEINS_STAGE_1 = ChatColor.RED + "I'm feeling a bit weak";
	final public static String PROTEINS_STAGE_2 = ChatColor.RED + "I can't even move a small boulder!";
	final public static String PROTEINS_STAGE_3 = ChatColor.RED + "I need some proteins really badly!";
	final public static String PROTEINS_MUCH_STAGE_1 = ChatColor.RED + "I'm feeling strong!";
	final public static String PROTEINS_MUCH_STAGE_2 = ChatColor.RED + "My strength is becoming enourmous. This can't be good...";
	final public static String PROTEINS_MUCH_STAGE_3 = ChatColor.RED + "Ouch! I ate too much proteins...";
	final public static String GLUCOSE_STAGE_1 = ChatColor.WHITE + "Gonna get hyper!";
	final public static String GLUCOSE_STAGE_2 = ChatColor.WHITE + "Too much energy, way to much energy!";
	final public static String GLUCOSE_STAGE_3 = ChatColor.WHITE + "I can't say the word sugar once more!";
	
	public Messages(FoodBalance i) {
		plugin = i;
	}
	
}
