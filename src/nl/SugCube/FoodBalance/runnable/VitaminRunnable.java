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

public class VitaminRunnable implements Runnable {

	public static FoodBalance plugin;
	
	public VitaminRunnable(FoodBalance instance) {
		plugin = instance;
	}
	
	public void run() {
		
		for (int i = 0; i < Bukkit.getOnlinePlayers().length; i++) {
			Player player = Bukkit.getOnlinePlayers()[i];
			
			if (player.getGameMode() != GameMode.CREATIVE) {
				int currentVitamin = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
				currentVitamin = (currentVitamin > 7 ? 7 : currentVitamin);
				currentVitamin = (currentVitamin < -7 ? -7 : currentVitamin);
				
				Random ran = new Random();
				if (currentVitamin <= -6) {
					/*
					 * -#- TIER 1 LACK -#- 
					 * 7,5% Nausea I		(0-20s)
					 * 7,5% Poison I		(0-10s)
					 * 7,5% Weakness I		(0-20s)
					 * 7,5%	Slowness I		(0-20s)
					 * 7,5% Mining Fat. I	(0-20s)
					 * 7,5% Blindness I		(0-20s)
					 * 7,5% Hunger I		(0-20s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentVitamin == -6) {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_VITAMINS_LACK)
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
					 * 13% Nausea II		(10-20s)
					 * 13% Poison II		(0-10s)
					 * 13% Weakness II		(10-20s)
					 * 13% Slowness II		(10-20s)
					 * 13% Mining Fat. II	(10-20s)
					 * 13% Blindness II		(10-20s)
					 * 13% Hunger II		(10-20s)
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextDouble() <= 0.4) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_VITAMINS_LACK)
										.replace("%player%", player.getName()));
								plugin.getValueManager().resetValues(player);
								player.damage(9999999);
							} else {
								player.damage(1);
							}
						}
					}
				} else if (currentVitamin >= 6) {
					/*
					 * -#- TIER 1 MUCH -#- 
					 * 7,5%	Poison II 		(0-10s)
					 * 7,5% Confusion I		(0-20s)
					 * 7,5% Blindness II	(0-20s)
					 * 10%	0.5 Heart Damage
					 */
					if (currentVitamin == 6) {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(20) * 20), 0));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(20) * 20), 1));
						}
						if (ran.nextInt(10) == 0) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_VITAMINS_MUCH)
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
					 * 13% Poison III		(0-10s)
					 * 13% Confusion II 	(0-20s)
					 * 13% Blindness III	(0-20s)	
					 * 40%	0.5 Heart Damage
					 */
					else {
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(10) * 20), 2));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(10) * 20) + 200, 1));
						}
						if (ran.nextInt(15) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(10) * 20) + 200, 2));
						}
						if (ran.nextDouble() <= 0.4) {
							if (player.getHealth() <= 1) {
								plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
								Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_VITAMINS_MUCH)
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
