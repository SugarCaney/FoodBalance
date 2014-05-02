package nl.SugCube.FoodBalance;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Level;

import nl.SugCube.FoodBalance.API.FoodBalanceAPI;
import nl.SugCube.FoodBalance.IO.MySQL;
import nl.SugCube.FoodBalance.IO.TableManager;
import nl.SugCube.FoodBalance.IO.ValueManager;
import nl.SugCube.FoodBalance.command.CmdFoodBalance;
import nl.SugCube.FoodBalance.command.CmdHealth;
import nl.SugCube.FoodBalance.food.FoodManager;
import nl.SugCube.FoodBalance.listener.CakeListener;
import nl.SugCube.FoodBalance.listener.DeathListener;
import nl.SugCube.FoodBalance.listener.EatListener;
import nl.SugCube.FoodBalance.listener.FightListener;
import nl.SugCube.FoodBalance.listener.JoinListener;
import nl.SugCube.FoodBalance.listener.RegenListener;
import nl.SugCube.FoodBalance.runnable.CarbohydrateRunnable;
import nl.SugCube.FoodBalance.runnable.ProteinRunnable;
import nl.SugCube.FoodBalance.runnable.VitaminRunnable;
import nl.SugCube.FoodBalance.runnable.WaterRunnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FoodBalance extends JavaPlugin {

	private MySQL mysql = null;
	private Connection connection = null;
	private ValueManager valueManager = new ValueManager(this);
	private CmdFoodBalance ceFoodBalance = new CmdFoodBalance(this);
	private CmdHealth ceHealth = new CmdHealth(this);
	private FoodManager foodManager = new FoodManager(this);
	private EatListener eatListener = new EatListener(this);
	private JoinListener joinListener = new JoinListener(this);
	private DeathListener deathListener = new DeathListener(this);
	private RegenListener regenListener = new RegenListener(this);
	private FightListener fightListener = new FightListener(this);
	private CakeListener cakeListener = new CakeListener(this);
	private Broadcast broadcast = new Broadcast(this);
	private DeathMessages deathMessages = new DeathMessages(this);
	public FoodBalanceAPI api = new FoodBalanceAPI(this);
	
	public void onEnable() {
		/*
		 * Load config.yml and data.yml
		 */
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if (!file.exists()) {
			try {
				getConfig().options().copyDefaults(true);
				saveConfig();
				this.getLogger().info("Generated config.yml succesfully!");
			} catch (Exception e) {
				this.getLogger().info("Failed to generate config.yml!");
			}
		}

		File df = new File(getDataFolder() + File.separator + "data.yml");
		if (!df.exists()) {
			try {
				reloadData();
				saveData();
				this.getLogger().info("Generated data.yml succesfully!");
			} catch (Exception e) {
				this.getLogger().info("Failed to generate data.yml!");
			}
		}
		
		/*
		 * Opens database (mysql) connection
		 */
		if (this.getConfig().getBoolean("mysql.enabled")) {
			try {
				mysql = new MySQL(this, this.getConfig().getString("mysql.host"), this.getConfig().getString("mysql.port"),
						this.getConfig().getString("mysql.database"), this.getConfig().getString("mysql.username"),
						this.getConfig().getString("mysql.password"));
				connection = mysql.openConnection();
				
				//Create foodbalance tables if they don't exist
				TableManager.createTables(mysql, connection, this);
			} catch (Exception exc) {
				this.getLogger().severe("Failed opening a MySQL conenction! Please check your configuration file " +
						"to make sure all MySQL data is correct.");
				this.getLogger().severe("FoodBalance now uses a flatfile. If you not want this to happen, please make " +
						"sure your config-data is right and then reboot the server.");
			}
		}
		
		//Register Events
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(eatListener, this);
		pluginManager.registerEvents(joinListener, this);
		pluginManager.registerEvents(deathListener, this);
		pluginManager.registerEvents(regenListener, this);
		pluginManager.registerEvents(fightListener, this);
		pluginManager.registerEvents(cakeListener, this);
		
		//Register CommandExecutors
		this.getCommand("foodbalance").setExecutor(ceFoodBalance);
		this.getCommand("health").setExecutor(ceHealth);
		
		//Register Runnables
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new WaterRunnable(this), 20L, 20L);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CarbohydrateRunnable(this), 20L, 20L);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ProteinRunnable(this), 20L, 20L);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new VitaminRunnable(this), 20L, 20L);
		
		//Register Foods
		this.foodManager.registerFoods();
		
		/*
		 * Check for updates
		 */
		if (this.getConfig().getBoolean("updates.check-for-updates")) {
			Update uc = new Update(58439 ,this.getDescription().getVersion());
			if (uc.query()) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[FoodBalance] A new version of FoodBalance is " +
						"avaiable! Get it at the BukkitDev page!");
			} else {
				this.getLogger().info("FoodBalance is up-to-date!");
			}
		}
		
		//Plugin metrics
		if (this.getConfig().getBoolean("metrics.enabled")) {
			try {
			    Metrics metrics = new Metrics(this);
			    metrics.start();
			    this.getLogger().info("Started Metrics.");
			} catch (Exception e) {
			    this.getLogger().info("Failed starting Metrics.");
			}
		}
		
		this.getLogger().info("FoodBalance v" + this.getDescription().getVersion() + " has been enabled!");
	}
	
	public void onDisable() {
		this.saveData();
		
		this.getLogger().info("FoodBalance v" + this.getDescription().getVersion() + " has been disabled!");
	}
	
	/*
	 * Use a file (data.yml) to store data
	 */
	private FileConfiguration data = null;
	private File dataFile = null;
	
	/**
	 * Reloads the data-file
	 * @param void
	 * @return void
	 */
	public void reloadData() {
	    if (dataFile == null) {
	    	dataFile = new File(getDataFolder(), "data.yml");
	    }
	    data = YamlConfiguration.loadConfiguration(dataFile);
	 
	    InputStream defStream = this.getResource("data.yml");
	    if (defStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defStream);
	        data.setDefaults(defConfig);
	    }
	}
	
	/**
	 * Gets the data.yml
	 * @param void
	 * @return (FileConfiguration) Data.yml
	 */
	public FileConfiguration getData() {
	    if (data == null) {
	        this.reloadData();
	    }
	    return data;
	}
	
	/**
	 * Saves the data.yml
	 * @param void
	 * @return void
	 */
	public void saveData() {
	    if (data == null || dataFile == null) {
	    	return;
	    }
	    try {
	        getData().save(dataFile);
	    } catch (Exception ex) {
	        this.getLogger().log(Level.SEVERE, "Could not save config to " + dataFile, ex);
	    }
	}
	
	public DeathMessages getDeathMessages() {
		return this.deathMessages;
	}
	
	public Broadcast getBroadcast() {
		return this.broadcast;
	}
	
	public FoodManager getFoodManager() {
		return this.foodManager;
	}
	
	public ValueManager getValueManager() {
		return this.valueManager;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public MySQL getMySQL() {
		return this.mysql;
	}
	
}
