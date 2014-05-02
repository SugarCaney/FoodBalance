package nl.SugCube.FoodBalance.runnable;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.food.FoodType;

public class ProteinRunnable implements Runnable {

	public static FoodBalance plugin;
	
	public ProteinRunnable(FoodBalance instance) {
		plugin = instance;
	}
	
	public void run() {
		
		for (int i = 0; i < Bukkit.getOnlinePlayers().length; i++) {
			Player player = Bukkit.getOnlinePlayers()[i];
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				int currentProtein = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
				currentProtein = (currentProtein > 7 ? 7 : currentProtein);
				currentProtein = (currentProtein < -7 ? -7 : currentProtein);
				
				Random ran = new Random();
				if (currentProtein <= -6) {
					/*
					 * -#- TIER 1 LACK -#- 
					 * 7,5% Weakness I		(0-20s)
					 * 7,5% Wither I		(0-10s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentProtein == -6) {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (ran.nextInt(10) * 20), 0));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_LACK)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
					/*
					 * -#- TIER 2 LACK -#- 
					 * 7,5% Weakness II		(0-20s)
					 * 7,5% Wither II		(0-10s)
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (ran.nextInt(10) * 20), 1));
						}
						if (ran.nextDouble() <= 0.4) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_LACK)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
				} else if (currentProtein >= 6) {
					/*
					 * -#- TIER 1 MUCH -#- 
					 * 10%	Poison I 		(0-10s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentProtein == 6) {
						if (ran.nextInt(10) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 0));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_MUCH)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
					/*
					 * -#- TIER 2 MUCH -#- 
					 * 20% Poison II		(0-10s)	
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(5) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 1));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_MUCH)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
				}
			}
		}
	}
	
}
