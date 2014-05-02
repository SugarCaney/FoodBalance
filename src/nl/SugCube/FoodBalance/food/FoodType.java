package nl.SugCube.FoodBalance.food;

public enum FoodType {

	CARBOHYDRATE("carbohydrate"),
	VITAMIN("vitamin"),
	PROTEIN("protein"),
	WATER("water");
	
	private String storageName;
	
	private FoodType(String storageName) {
		this.storageName = storageName;
	}
	
	public String toString() {
		return this.storageName;
	}
	
}
