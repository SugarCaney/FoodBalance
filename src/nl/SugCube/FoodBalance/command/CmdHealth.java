package nl.SugCube.FoodBalance.command;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.Const;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHealth implements CommandExecutor {

	public static FoodBalance plugin;
	
	public CmdHealth(FoodBalance instance) {
		plugin = instance;
	}
	
	/*
	 * /health, /food, /foodstats, /fbstats, /fbalancestats, /foodbalancestats
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			//Get current values
			int carbohydrates = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
			int proteins = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
			int vitamins = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
			int water = plugin.getValueManager().getValue(FoodType.WATER, player);
			
			//Correct to 7/-7 when they get outside the boundaries
			carbohydrates = (carbohydrates > 7 ? 7 : carbohydrates);
			carbohydrates = (carbohydrates < -7 ? -7 : carbohydrates);
			proteins = (proteins > 7 ? 7 : proteins);
			proteins = (proteins < -7 ? -7 : proteins);
			vitamins = (vitamins > 7 ? 7 : vitamins);
			vitamins = (vitamins < -7 ? -7 : vitamins);
			water = (water < 0 ? 0 : water);
			
			//Send player the carbohydrate-bar
			String msg = Broadcast.getColour(FoodType.CARBOHYDRATE) + "[";
			for (int c = -7; c <= 7; c++) {
				if (carbohydrates >= c) {
					msg += "#";
				} else {
					msg += "-";
				}
			}
			msg += "] " + Broadcast.getMsg(Message.CARBOHYDRATES);
			player.sendMessage(msg);
			
			//Send player the proteins-bar
			msg = Broadcast.getColour(FoodType.PROTEIN) + "[";
			for (int p = -7; p <= 7; p++) {
				if (proteins >= p) {
					msg += "#";
				} else {
					msg += "-";
				}
			}
			msg += "] " + Broadcast.getMsg(Message.PROTEINS);
			player.sendMessage(msg);
			
			//Send player the vitamins-bar
			msg = Broadcast.getColour(FoodType.VITAMIN) + "[";
			for (int v = -7; v <= 7; v++) {
				if (vitamins >= v) {
					msg += "#";
				} else {
					msg += "-";
				}
			}
			msg += "] " + Broadcast.getMsg(Message.VITAMINS);
			player.sendMessage(msg);
			
			//Send player the hydration-bar
			int times = (int) Math.ceil((double) water / (double) (Const.HYDRATION_MAX / 15));
			msg = Broadcast.getColour(FoodType.WATER) + "[";
			for (int w = 0; w < times; w++) {
				msg += "#";
			}
			for (int w = times; w < 15; w++) {
				msg += "-";
			}
			msg += "] " + Broadcast.getMsg(Message.WATER);
			player.sendMessage(msg);
		} else {
			sender.sendMessage(Broadcast.getMsg(Message.ONLY_IN_GAME));
		}
		
		return false;
	}

}
