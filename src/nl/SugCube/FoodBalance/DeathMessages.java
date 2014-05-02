package nl.SugCube.FoodBalance;

import java.util.ArrayList;
import java.util.List;

public class DeathMessages {

	public static FoodBalance plugin;
	private List<String> deathMessageBans = new ArrayList<String>();
	
	public DeathMessages(FoodBalance instance) {
		plugin = instance;
	}
	
	public List<String> getDeathMessageBans() {
		return this.deathMessageBans;
	}
	
}
