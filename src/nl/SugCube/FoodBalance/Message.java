package nl.SugCube.FoodBalance;

public enum Message {

	ONLY_IN_GAME("messages.only-in-game"),
	CARBOHYDRATES("messages.carbohydrates"),
	CARBOHYDRATES_COLOUR("messages.carbohydrates-colour"),
	PROTEINS("messages.proteins"),
	PROTEINS_COLOUR("messages.proteins-colour"),
	VITAMINS("messages.vitamins"),
	VITAMINS_COLOUR("messages.vitamins-colour"),
	WATER("messages.water"),
	WATER_COLOUR("messages.water-colour"),
	USAGE_RESET("messages.usage-reset"),
	VALUES_RESET("messages.values-reset"),
	RELOAD_OK("messages.reload-ok"),
	RELOAD_FAIL("messages.reload-fail"),
	DEATH_CARBOHYDRATES_LACK("messages.death-carbohydrates-lack"),
	DEATH_CARBOHYDRATES_MUCH("messages.death-carbohydrates-much"),
	DEATH_PROTEINS_LACK("messages.death-proteins-lack"),
	DEATH_PROTEINS_MUCH("messages.death-proteins-much"),
	DEATH_VITAMINS_LACK("messages.death-vitamins-lack"),
	DEATH_VITAMINS_MUCH("messages.death-vitamins-much"),
	DEATH_HYDRATION("messages.death-hydration"),
	STATUS_CARBOHYDRATES_LOW("messages.status-carbohydrates-low"),
	STATUS_CARBOHYDRATES_HIGH("messages.status-carbohydrates-high"),
	STATUS_HYDRATION("messages.status-hydration"),
	STATUS_PROTEINS_LOW("messages.status-proteins-low"),
	STATUS_PROTEINS_HIGH("messages.status-proteins-high"),
	STATUS_VITAMINS_LOW("messages.status-vitamins-low"),
	STATUS_VITAMINS_HIGH("messages.status-vitamins-high"),
	;
	
	private String node;
	
	private Message(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return this.node;
	}
	
}
