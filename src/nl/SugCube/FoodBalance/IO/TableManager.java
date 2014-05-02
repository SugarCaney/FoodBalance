package nl.SugCube.FoodBalance.IO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.Plugin;

public class TableManager {

	public static void createTables(MySQL mysql, Connection connection, Plugin plugin) {
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS carbohydrates(" +
					"uuid VARCHAR(40)," +
					"value INT(20) );");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS vitamins(" +
					"uuid VARCHAR(40)," +
					"value INT(20) );");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS proteins(" +
					"uuid VARCHAR(40)," +
					"value INT(20) );");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS water(" +
					"uuid VARCHAR(40)," +
					"value INT(20) );");
		} catch (SQLException e) {
			plugin.getLogger().severe("Could not generate tables! Please check your mysql settings!");
			plugin.getLogger().severe("Cause: " + e.getMessage());
		}
		
	}
	
}
