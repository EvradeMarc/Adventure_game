package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.DataBaseConnection;
import factory.Item;
import factory.Weapon;
import observer.Player;

public class PlayerDao {

	public void savePlayer(Player player) throws SQLException {

		clearTablePlayer();
		String sql = "INSERT INTO player(health_point, name, default_damage, bonus_damage, level, defense, luck, experience, classe, state, currentPlace)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, player.getHealthPoint());
			stmt.setString(2, player.getName());
			stmt.setInt(3, player.getDefaultDamage());
			stmt.setInt(4, player.getBonusDamage());
			stmt.setInt(5, player.getLevel());
			stmt.setInt(6, player.getDefense());
			stmt.setInt(7, player.getLuck());
			stmt.setInt(8, player.getCurrentExperience());
			stmt.setString(9, player.getClasse().getName());
			stmt.setString(10, player.getState().getStatus());
			stmt.setString(11, player.getCurrentPlace().getNom());

			stmt.executeUpdate();
		}

	}

	public Player loadPlayer() throws SQLException{

		String sql = "SELECT * FROM player";

		ItemDao itemDao = new ItemDao();
		PlaceDao placeDao = new PlaceDao();
		Item currentWeapon = itemDao.loadPlayerCurrentWeapon();

		try (Connection conn = DataBaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	              return new Player(rs.getInt("health_point"), rs.getString("name"), rs.getInt("default_damage"), rs.getInt("bonus_damage"), rs.getInt("level"), rs.getInt("defense"), rs.getInt("luck"), rs.getInt("experience"), rs.getString("classe"), itemDao.loadPlayerInventory(), ((Weapon) currentWeapon), rs.getString("state"), placeDao.loadPlaceWithName(rs.getString("currentPlace")));
	            }
	        }
	        return null ;
	    }



//Annexe
	public void clearTablePlayer() throws SQLException {
		String sql = "DELETE FROM Player";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}
	
	
	public int playerCount() throws SQLException{

		String sql = "SELECT COUNT(*) as player FROM Player";
		

		try (Connection conn = DataBaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	            	return rs.getInt("player");
	            }
	        }
	        return 0 ;
	    }
	

}
