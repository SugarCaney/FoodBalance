package nl.SugCube.FoodBalance.Main;

import java.util.HashMap;
import java.util.logging.Logger;

import nl.SugCube.FoodBalance.Event.PlayerConsumeListener;
import nl.SugCube.FoodBalance.Event.PlayerDeathListener;
import nl.SugCube.FoodBalance.Event.PlayerJoinListener;
import nl.SugCube.FoodBalance.Event.PlayerRespawnListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FoodBalance extends JavaPlugin {

	public final Logger log = Logger.getLogger("Minecraft");
	
	public Messages m = new Messages(this);
	public Stats s = new Stats(this);
	public FoodBalanceAPI api = new FoodBalanceAPI(this);
	
	public PlayerJoinListener PlayerJoinListener = new PlayerJoinListener(this);
	public PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public PlayerConsumeListener PlayerConsumeListener = new PlayerConsumeListener(this);
	public PlayerRespawnListener PlayerRespawnListener = new PlayerRespawnListener(this);
	
	public HashMap<Player, Integer> hydration = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> carbohydrates = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> carbohydratesCount = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> proteins = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> proteinsCount = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> vitamins = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> vitaminsCount = new HashMap<Player, Integer>();
	public HashMap<Player, String> damageCause = new HashMap<Player, String>();
	
	@Override
	public void onEnable() {
		log.info("[FoodBalance] FoodBalance has been enabled!");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(PlayerJoinListener, this);
		pm.registerEvents(PlayerDeathListener, this);
		pm.registerEvents(PlayerConsumeListener, this);
		pm.registerEvents(PlayerRespawnListener, this);
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Timer(this), 20, 20);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.hydration.put(player, Const.HYDRATION_START);
			this.damageCause.put(player, null);
			this.carbohydrates.put(player, 0);
			this.carbohydratesCount.put(player, Const.LACK_OF_COUNTDOWN);
			this.proteins.put(player, 0);
			this.proteinsCount.put(player, Const.LACK_OF_COUNTDOWN);
			this.vitamins.put(player, 0);
			this.vitaminsCount.put(player, Const.LACK_OF_COUNTDOWN);
		}
	}
	
	@Override
	public void onDisable() {
		log.info("[FoodBalance] FoodBalance has been disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("health")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("setfood") && player.hasPermission("foodbalance.admin")) {
						player.setFoodLevel(Integer.parseInt(args[1]));
					} else if (args[0].equalsIgnoreCase("reset") && player.hasPermission("foodbalance.admin") &&
							args.length >= 2) {
						FoodBalanceAPI.reset(getServer().getPlayer(args[1]));
					} else {
						Stats.show(player);
					}
				} else {
					Stats.show(player);
				}
			}
		}
		
		return false;
	}
	
}