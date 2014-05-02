package nl.SugCube.FoodBalance.command;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.Update;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFoodBalance implements CommandExecutor {

	public static FoodBalance plugin;
	
	public CmdFoodBalance(FoodBalance instance) {
		plugin = instance;
	}
	
	/*
	 * /foodbalance, /fb, /foodb, /fbalance
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender.hasPermission("foodbalance")) {
			
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("reset")) {
					if (args.length > 1) {
						try {
							Player player = Bukkit.getPlayer(args[1]);
							plugin.getValueManager().resetValues(player);							
							sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.VALUES_RESET).replace("%player%",
									player.getName()));
						} catch (Exception e) {
							sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
						}
					} else {
						sender.sendMessage(Broadcast.getMsg(Message.USAGE_RESET));
					}
				} else if (args[0].equalsIgnoreCase("reload")) {
					try {
						plugin.reloadConfig();
						plugin.reloadData();
						sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.RELOAD_OK).replace("%version%",
								plugin.getDescription().getVersion()));
					} catch (Exception e) {
						sender.sendMessage(Broadcast.TAG + Broadcast.getMsg(Message.RELOAD_FAIL).replace("%version%",
								plugin.getDescription().getVersion()));
					}
				}
			} else {
				showAbout(sender, true);
			}
			
		} else {
			showAbout(sender, false);
		}
		
		return false;
	}
	
	public void showAbout(CommandSender sender, boolean perms) {
		Update uc = new Update(58439, plugin.getDescription().getVersion());
		String update = "";
		
		if (uc.query()) {
			update = "(New version avaiable!)";
		} else {
			update = "(Up-to-date)";
		}
		
		sender.sendMessage(Broadcast.TAG + "Plugin made by " + ChatColor.GREEN + plugin.getDescription()
				.getAuthors().toString());
		sender.sendMessage(Broadcast.TAG + "Current version: " + plugin.getDescription().getVersion() + " " + update);
		if (perms) {
			sender.sendMessage(Broadcast.TAG + "Use " + ChatColor.GREEN + "/fb help " + 
					ChatColor.GOLD + "to get a list of commands.");
		}
	}
	
}
