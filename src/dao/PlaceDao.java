package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.DataBaseConnection;
import factory.Enemy;
import factory.Item;
import factory.ItemFactory;
import factory.Place;
import factory.Weapon;
import world.World;

public class PlaceDao {

// ========================== SAUVEGARDE ===========================

	// Sauvegarde du monde
	public void saveWorld(World world) throws SQLException {
		
		clearTableWorldSave();
		String sql = "INSERT INTO world_save (id_place, nom, description, progression, locked, completed, boss) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			
			try (Connection conn = DataBaseConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
				
					for (Place pl : world.getPlaces()) {
							stmt.setInt(1, world.getPlaces().indexOf(pl));
							stmt.setString(2, pl.getNom());
							stmt.setString(3, pl.getDescription());
							stmt.setInt(4, pl.getProgression());
							stmt.setBoolean(5, pl.isLocked());
							stmt.setBoolean(6, pl.isCompleted());
							stmt.setInt(7, world.getPlaces().indexOf(pl));
							stmt.executeUpdate();

					}
		}

	}


	// Annexe
	public void saveEnemiesPlace(World world) throws SQLException {

		clearTableEnemySave(); // on vide d'abord la table
		String sql = "INSERT INTO enemy_save (nom, description, damage, isAlive, id_place) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (Place pl : world.getPlaces()) {
				for (Enemy e : pl.getEnemies()) {
					stmt.setString(1, e.getNom());
					stmt.setString(2, e.getDescription());
					stmt.setInt(3, e.getDamage());
					stmt.setBoolean(4, e.isAlive());
					stmt.setInt(5, world.getPlaces().indexOf(pl));
	
					stmt.executeUpdate();
	
				}
			}

		}

	}

	// Annexe
	public void saveBossPlace(World world) throws SQLException {

		clearTableBossSave(); // on vide d'abord la table
		String sql = "INSERT INTO boss_save (id_boss, nom, description, damage, isAlive) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (Place pl : world.getPlaces()) {
				stmt.setInt(1, world.getPlaces().indexOf(pl));
				stmt.setString(2, pl.getBoss().getNom());
				stmt.setString(3, pl.getBoss().getDescription());
				stmt.setInt(4, pl.getBoss().getDamage());
				stmt.setBoolean(5, pl.getBoss().isAlive());
	
				stmt.executeUpdate();
			}
		}

	}

	// Annexe
	public void saveItemsPlace(World world) throws SQLException {

		clearTableItemSave(); // on vide d'abord la table
		String sql = "INSERT INTO item_save (type, nom, description, amount, classe, id_place) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (Place pl : world.getPlaces()) {
				for (Item it : pl.getItems()) {
	
					stmt.setString(1, it.getType());
					stmt.setString(2, it.getNom());
					stmt.setString(3, it.getDescription());
					stmt.setInt(4, it.getAmount());
	
					if (it.getType().equals("Weapon")) {
						stmt.setString(5, ((Weapon) it).getClasse().getName());
					} else {
						stmt.setString(5, "");
					}
	
					stmt.setInt(6, world.getPlaces().indexOf(pl));
	
					stmt.executeUpdate();
	
				}
			}

		}

	}

	
	// Annexe
		public void clearTableWorldSave() throws SQLException {
			String sql = "DELETE FROM world_save";
			try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
				stmt.executeUpdate(sql);
			}
		}
	
	// Annexe
	public void clearTableEnemySave() throws SQLException {
		String sql = "DELETE FROM enemy_save";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}

	// Annexe
	public void clearTableBossSave() throws SQLException {
		String sql = "DELETE FROM boss_save";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}

	// Annexe
	public void clearTableItemSave() throws SQLException {
		String sql = "DELETE FROM item_save";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}




// ========================== Chargement nouvelle partie =========================

	public List<Place> loadNewWorld() throws SQLException {

		String sql = "SELECT * FROM world";
		List<Place> places = new ArrayList<>();
		int idPlace;
		int idBoss;

		try (Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				idPlace = rs.getInt("id_place");
				idBoss = rs.getInt("id_boss");
				places.add(new Place(rs.getString("nom"), rs.getString("description"), loadNewEnemiesPlace(idPlace),loadNewItemsPlace(idPlace), loadNewBossPlace(idBoss), rs.getInt("progression"), rs.getBoolean("locked"), rs.getBoolean("completed")));
			}
		}
		return places;
	}

	public Place loadFirstPlace() throws SQLException {

		String sql = "SELECT * FROM world LIMIT 1";

		int idPlace;
		int idBoss;

		try (Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				idPlace = rs.getInt("id_place");
				idBoss = rs.getInt("id_boss");
				return new Place(rs.getString("nom"), rs.getString("description"), loadNewEnemiesPlace(idPlace),loadNewItemsPlace(idPlace), loadNewBossPlace(idBoss), rs.getInt("progression"), rs.getBoolean("locked"), rs.getBoolean("completed"));
			}
		}
		return null;
	}


	public List<Enemy> loadNewEnemiesPlace(int i) throws SQLException {

		List<Enemy> enemies = new ArrayList<>();
		String sql = "SELECT * FROM enemy WHERE id_place = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				enemies.add(new Enemy(rs.getString("nom"), rs.getString("description"), rs.getInt("damage"),rs.getBoolean("isAlive")));

			}
		}

		return enemies;
	}

	public List<Item> loadNewItemsPlace(int i) throws SQLException {

		List<Item> items = new ArrayList<>();
		String sql = "SELECT * FROM item WHERE id_place = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				items.add(ItemFactory.getItem(rs.getString("type"), rs.getString("nom"), rs.getString("description"), rs.getInt("amount"), rs.getString("classe")));

			}
		}

		return items;
	}

	public Enemy loadNewBossPlace(int i) throws SQLException {

		String sql = "SELECT * FROM boss WHERE id_boss = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return new Enemy(rs.getString("nom"), rs.getString("description"), rs.getInt("damage"),rs.getBoolean("isAlive"));
			}
		}

		return null;
	}




// ========================== Chargement après sauvegarde ===========================

	public List<Place> loadWorld() throws SQLException {

		String sql = "SELECT * FROM world_save";
		List<Place> places = new ArrayList<>();
		int idPlace;

		try (Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				idPlace = rs.getInt("id_place");
				places.add(new Place(rs.getString("nom"), rs.getString("description"), loadEnemiesPlace(idPlace),loadItemsPlace(idPlace), loadBossPlace(idPlace), rs.getInt("progression"), rs.getBoolean("locked"), rs.getBoolean("completed")));
			}
		}
		return places;
	}


	public Place loadPlaceWithName(String nom) throws SQLException {

		String sql = "SELECT * FROM world_save WHERE nom = ?";
		int idPlace;

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, nom);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				idPlace = rs.getInt("id_place");
				return new Place(rs.getString("nom"), rs.getString("description"), loadEnemiesPlace(idPlace),loadItemsPlace(idPlace), loadBossPlace(idPlace), rs.getInt("progression"), rs.getBoolean("locked"), rs.getBoolean("completed"));
			}
		}
		return null;
	}

	public List<Enemy> loadEnemiesPlace(int i) throws SQLException {

		List<Enemy> enemies = new ArrayList<>();
		String sql = "SELECT * FROM enemy_save WHERE id_place = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				enemies.add(new Enemy(rs.getString("nom"), rs.getString("description"), rs.getInt("damage"),rs.getBoolean("isAlive")));

			}
		}

		return enemies;
	}

	public List<Item> loadItemsPlace(int i) throws SQLException {

		List<Item> items = new ArrayList<>();
		String sql = "SELECT * FROM item_save WHERE id_place = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				items.add(ItemFactory.getItem(rs.getString("type"), rs.getString("nom"), rs.getString("description"), rs.getInt("amount"), rs.getString("classe")));

			}
		}

		return items;
	}

	public Enemy loadBossPlace(int i) throws SQLException {

		String sql = "SELECT * FROM boss_save WHERE id_boss = ?";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return new Enemy(rs.getString("nom"), rs.getString("description"), rs.getInt("damage"),rs.getBoolean("isAlive"));
			}
		}

		return null;
	}

}
