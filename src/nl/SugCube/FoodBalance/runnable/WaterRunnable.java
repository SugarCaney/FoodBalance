package nl.SugCube.FoodBalance.runnable;

import java.util.Random;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.Const;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterRunnable implements Runnable {

	public static FoodBalance plugin;
	
	public WaterRunnable(FoodBalance instance) {
		plugin = instance;
	}
	
	public void run() {
		
		for (int i = 0; i < Bukkit.getOnlinePlayers().length; i++) {
			Player player = Bukkit.getOnlinePlayers()[i];
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				int currentWater = plugin.getValueManager().getValue(FoodType.WATER, player) - 1;
				currentWater = (currentWater < 0 ? 0 : currentWater);
				currentWater = (currentWater > Const.HYDRATION_MAX ? Const.HYDRATION_MAX : currentWater);
				
				//Extra dehydration when in a hot biome
				Biome biome = player.getLocation().getBlock().getBiome();
				if (biome == Biome.DESERT || biome == Biome.DESERT_HILLS || biome == Biome.DESERT_MOUNTAINS || biome == Biome.HELL) {
					currentWater -= 2;
				} else if (biome == Biome.SAVANNA || biome == Biome.SAVANNA_MOUNTAINS || biome == Biome.SAVANNA_PLATEAU ||
						biome == Biome.SAVANNA_PLATEAU_MOUNTAINS || biome == Biome.JUNGLE || biome == Biome.JUNGLE_EDGE ||
						biome == Biome.JUNGLE_EDGE_MOUNTAINS || biome == Biome.JUNGLE_HILLS || biome == Biome.JUNGLE_MOUNTAINS ||
						biome == Biome.MESA || biome == Biome.MESA_BRYCE || biome == Biome.MESA_PLATEAU ||
						biome == Biome.MESA_PLATEAU_FOREST || biome == Biome.MESA_PLATEAU_FOREST_MOUNTAINS ||
						biome == Biome.MESA_PLATEAU_MOUNTAINS) {
					currentWater -= 1;
				}
				
				//Extra dehydration when sprinting
				if (player.isSprinting()) {
					currentWater -= 1;
				}
				
				Random ran = new Random();
				/*
				 * -#- 10% water left -#- 
				 * 10%	Nausea II 		(10-20s)
				 * 10% 	Slowness II		(10-20s)
				 * 10% 	Mining Fat. II	(10-20s)
				 * 33%	0.5 Heart Damage
				 */
				if (currentWater <= Const.HYDRATION_MAX * 0.1) {
					if (ran.nextInt(10) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(10) + 10) * 20, 1));
					}
					if (ran.nextInt(10) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(10) + 10) * 20, 1));
					}
					if (ran.nextInt(10) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(10) + 10) * 20, 1));
					}
					if (ran.nextInt(3) == 0) {
						if (player.getHealth() <= 1) {
							plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
							Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_HYDRATION).replace("%player%", player.getName()));
							plugin.getValueManager().resetValues(player);
							player.damage(9999999);
						} else {
							player.damage(1);
						}
					}
				}
				/*
				 * -#- 20% water left -#- 
				 * 7,5%	Nausea I 		(1-20s)
				 * 7,5% Slowness I		(1-20s)
				 * 7,5% Mining Fat. I	(1-20s)
				 */
				else if (currentWater <= Const.HYDRATION_MAX * 0.2) {
					if (ran.nextInt(15) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(20) * 20), 0));
					}
					if (ran.nextInt(15) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(20) * 20), 0));
					}
					if (ran.nextInt(15) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(20) * 20), 0));
					}
				}
				
				//If water is completely run out
				if (currentWater == 0) {
					plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
					Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_HYDRATION).replace("%player%", player.getName()));
					plugin.getValueManager().resetValues(player);
					player.damage(99999999);
				} else {
					plugin.getValueManager().setValue(FoodType.WATER, player, currentWater);
				}
				
			}
		}
	}
	
}
