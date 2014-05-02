package nl.SugCube.FoodBalance.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.SugCube.FoodBalance.Const;
import nl.SugCube.FoodBalance.FoodBalance;

import org.bukkit.Material;

public class FoodManager {

	private List<Food> foods = new ArrayList<Food>();
	
	public static FoodBalance plugin;
	
	public FoodManager(FoodBalance instance) {
		plugin = instance;
	}
	
	public void registerFoods() {
		Food food = null;
		Material mat = null;
		Values val = null;
		HashMap<FoodType, Integer> fti = null;
		
		//Golden Carrot
		mat = Material.GOLDEN_CARROT;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.WATER, 25);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Golden Apple
		mat = Material.GOLDEN_APPLE;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.CARBOHYDRATE, 1);
		fti.put(FoodType.WATER, 50);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Cooked Porkchop
		mat = Material.GRILLED_PORK;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Cooked Beef
		mat = Material.COOKED_BEEF;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Cooked Fish
		mat = Material.COOKED_FISH;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Spider Eye
		mat = Material.SPIDER_EYE;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Baked Potato
		mat = Material.BAKED_POTATO;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.CARBOHYDRATE, 2);
		fti.put(FoodType.VITAMIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Cooked Chicken
		mat = Material.COOKED_CHICKEN;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Mushroom Soup
		mat = Material.MUSHROOM_SOUP;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.WATER, 320);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Bread
		mat = Material.BREAD;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.CARBOHYDRATE, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Carrot
		mat = Material.CARROT;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.WATER, 25);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Pumpkin Pie
		mat = Material.PUMPKIN_PIE;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.CARBOHYDRATE, 2);
		fti.put(FoodType.VITAMIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Apple
		mat = Material.APPLE;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.CARBOHYDRATE, 1);
		fti.put(FoodType.WATER, 50);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Raw Beef
		mat = Material.RAW_BEEF;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Raw Pork
		mat = Material.PORK;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Raw Chicken
		mat = Material.RAW_CHICKEN;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Raw Potato
		mat = Material.POTATO_ITEM;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 1);
		fti.put(FoodType.CARBOHYDRATE, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Poisonous Potato
		mat = Material.POISONOUS_POTATO;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.CARBOHYDRATE, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Melon
		mat = Material.MELON;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.VITAMIN, 2);
		fti.put(FoodType.CARBOHYDRATE, 1);
		fti.put(FoodType.WATER, 75);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Cookie
		mat = Material.COOKIE;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.CARBOHYDRATE, 2);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Rotten Flesh
		mat = Material.ROTTEN_FLESH;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Raw Fish
		mat = Material.RAW_FISH;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.PROTEIN, 1);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Potions
		mat = Material.POTION;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.WATER, 500);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
		
		//Milk
		mat = Material.MILK_BUCKET;
		fti = new HashMap<FoodType, Integer>();
		fti.put(FoodType.WATER, Const.HYDRATION_MAX);
		food = new Food(mat, new Values(fti, plugin), plugin);
		this.foods.add(food);
	}
	
	public List<Food> getFoods() {
		return this.foods;
	}
	
}
