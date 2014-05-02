package nl.SugCube.FoodBalance.runnable;

import java.util.Random;

import nl.SugCube.FoodBalance.Broadcast;
import nl.SugCube.FoodBalance.FoodBalance;
import nl.SugCube.FoodBalance.Message;
import nl.SugCube.FoodBalance.food.FoodType;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CarbohydrateRunnable implements Runnable {

	public static FoodBalance plugin;
	
	public CarbohydrateRunnable(FoodBalance instance) {
		plugin = instance;
	}
	
	public void run() {
		
		for (int i = 0; i < Bukkit.getOnlinePlayers().length; i++) {
			Player player = Bukkit.getOnlinePlayers()[i];
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				int currentCarbo = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
				currentCarbo = (currentCarbo > 7 ? 7 : currentCarbo);
				currentCarbo = (currentCarbo < -7 ? -7 : currentCarbo);
				
				Random ran = new Random();
				if (currentCarbo <= -6) {
					/*
					 * -#- TIER 1 LACK -#- 
					 * 7,5%	Weakness I 		(0-20s)
					 * 7,5% Slowness I		(0-20s)
					 * 7,5% Mining Fat. I	(0-20s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentCarbo == -6) {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_CARBOHYDRATES_LACK)
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
					 * 13%	Weakness II 	(10-20s)
					 * 13% Slowness II		(10-20s)
					 * 13% Mining Fat. II	(10-20s)
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextDouble() <= 0.4) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_CARBOHYDRATES_LACK)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
				} else if (currentCarbo >= 6) {
					/*
					 * -#- TIER 1 MUCH -#- 
					 * 7,5%	Hunger I 		(0-20s)
					 * 7,5% Nausea I		(0-20s)
					 * 7,5% Jump Boost II	(0-10s)
					 * 7,5% Speed II		(0-10s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentCarbo == 6) {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(20) * 20), 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(20) * 20), 1));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_CARBOHYDRATES_MUCH)
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
					 * 13%	Hunger II 		(10-20s)
					 * 13% 	Nausea II		(10-20s)
					 * 13%  Jump Boost IV	(10-20s)
					 * 13% 	Speed IV		(10-20s)
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (ran.nextInt(20) * 20) + 200, 1));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(20) * 20) + 200, 1));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(20) * 20) + 200, 3));
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(20) * 20) + 200, 3));
						}
						if (ran.nextDouble() <= 0.4) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_CARBOHYDRATES_MUCH)
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
