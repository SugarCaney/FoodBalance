package nl.SugCube.FoodBalance.Main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Timer implements Runnable {

	public static FoodBalance plugin;
	Random ran = new Random();
	
	public Timer(FoodBalance i) {
		plugin = i;
	}
	
	@Override
	public void run() {
		
		try {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getGameMode().getValue() != 1) {
					
					double total = plugin.carbohydrates.get(player) + plugin.proteins.get(player) +
							plugin.vitamins.get(player);
					int value;
					
					if (total >= 9) {
						
						/*
						 * PROTEINS LACK
						 */
						if (plugin.proteins.get(player) / total < 0.1) {
							
							value = plugin.proteinsCount.get(player);
							value -= 1;
							plugin.proteinsCount.remove(player);
							plugin.proteinsCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.PROTEINS_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.PROTEINS_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(12) + 2) * 20, 3));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.PROTEINS_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(18) + 6) * 20, 4));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "proteins");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "proteins");
								player.damage(999999);
							}
							
						}
						/*
						 * PROTEINS MUCH
						 */
						else if (plugin.proteins.get(player) / total > 0.65) {
							
							value = plugin.proteinsCount.get(player);
							value -= 1;
							plugin.proteinsCount.remove(player);
							plugin.proteinsCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.PROTEINS_MUCH_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.PROTEINS_MUCH_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(12) + 2) * 20, 0));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.PROTEINS_MUCH_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (ran.nextInt(18) + 6) * 20, 0));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(6) + 2) * 20, 0));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "proteinsExtra");
								player.damage(999999);
							}
							
						} else if (plugin.proteinsCount.get(player) < Const.LACK_OF_COUNTDOWN) {
							plugin.proteinsCount.remove(player);
							plugin.proteinsCount.put(player, Const.LACK_OF_COUNTDOWN);
						}
						
						/*
						 * VITAMINS LACK
						 */
						if (plugin.vitamins.get(player) / total < 0.1) {
							
							value = plugin.vitaminsCount.get(player);
							value -= 1;
							plugin.vitaminsCount.remove(player);
							plugin.vitaminsCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.VITAMINS_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(6) + 6) * 20, 2));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.VITAMINS_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(12) + 6) * 20, 3));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(12) + 6) * 20, 1));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.VITAMINS_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(18) + 6) * 20, 4));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(2) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(12) + 6) * 20, 0));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "vitamins");
								player.damage(999999);
							}
							
						}
						/*
						 * VITAMINS MUCH
						 */
						else if (plugin.vitamins.get(player) / total > 0.65) {
							
							value = plugin.vitaminsCount.get(player);
							value -= 1;
							plugin.vitaminsCount.remove(player);
							plugin.vitaminsCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.VITAMINS_MUCH_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.VITAMINS_MUCH_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(12) + 2) * 20, 0));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.VITAMINS_MUCH_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(16) + 2) * 20, 1));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "vitaminsExtra");
								player.damage(999999);
							}
							
						} else if (plugin.vitaminsCount.get(player) < Const.LACK_OF_COUNTDOWN) {
							plugin.vitaminsCount.remove(player);
							plugin.vitaminsCount.put(player, Const.LACK_OF_COUNTDOWN);
						}
						
						/*
						 * CARBOHYDRATES LACK
						 */
						if (plugin.carbohydrates.get(player) / total < 0.1) {
							
							value = plugin.carbohydratesCount.get(player);
							value -= 1;
							plugin.carbohydratesCount.remove(player);
							plugin.carbohydratesCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.CARBOHYDRATES_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.CARBOHYDRATES_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(12) + 6) * 20, 1));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.CARBOHYDRATES_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(18) + 6) * 20, 1));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(18) + 6) * 20, 1));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "carbohydrates");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "carbohydrates");
								player.damage(999999);
							}
							
						}
						/*
						 * CARBOHYDRATES MUCH
						 */
						else if (plugin.carbohydrates.get(player) / total > 0.65) {
							
							value = plugin.carbohydratesCount.get(player);
							value -= 1;
							plugin.carbohydratesCount.remove(player);
							plugin.carbohydratesCount.put(player, value);
							
							if (value == 119) {
								player.sendMessage(Messages.CARBOHYDRATES_MUCH_STAGE_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 2));
								}
							}
							if (value == 90) {
								player.sendMessage(Messages.CARBOHYDRATES_MUCH_STAGE_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 3));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(8) + 2) * 20, 3));
								}
							}
							if (value == 60) {
								player.sendMessage(Messages.CARBOHYDRATES_MUCH_STAGE_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 5));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(8) + 2) * 20, 5));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "carbohydratesExtra");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "carbohydratesExtra");
								player.damage(999999);
							}
							
						} else if (plugin.carbohydratesCount.get(player) < Const.LACK_OF_COUNTDOWN) {
							plugin.carbohydratesCount.remove(player);
							plugin.carbohydratesCount.put(player, Const.LACK_OF_COUNTDOWN);
						}
					}
					
					/*
					 * HYDRATION
					 */
					value = plugin.hydration.get(player);
					plugin.hydration.remove(player);
					value--;
					
					if (value > 0) {
						plugin.hydration.put(player, value);
					} else {
						plugin.damageCause.remove(player);
						plugin.damageCause.put(player, "dehydration");
						player.damage(999999);
					}
					
					if (value <= 120 && value > 90) {
						if (value == 120) {
							player.sendMessage(Messages.DEHYDRATION_STAGE_2);
						}
						if (ran.nextInt(10) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(6) + 6) * 20, 0));
						}
					} else if (value <= 90 && value > 60) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 22, 0));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 22, 0));
						if (value == 90) {
							player.sendMessage(Messages.DEHYDRATION_STAGE_3);
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(12) + 6) * 20, 1));
						}
					} else  if (value <= 60) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 22, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 22, 1));
						if (value == 60) {
							player.sendMessage(Messages.DEHYDRATION_STAGE_4);
						}
						if (ran.nextInt(2) == 0) {
							plugin.damageCause.remove(player);
							plugin.damageCause.put(player, "dehydration");
							player.damage(1);
							if (!(player.isDead())) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, null);
							}
						}
						if (ran.nextInt(6) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(18) + 6) * 20, 2));
						}
						
					}
				}
			}
		} catch (Exception e) { }
		
	}

}
