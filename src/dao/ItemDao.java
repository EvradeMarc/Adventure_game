package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.DataBaseConnection;
import factory.Item;
import factory.ItemFactory;
import factory.Weapon;
import observer.Player;

public class ItemDao {

// ========================== SAUVEGARDE ===========================

	// sauvegarde des objets de l'inventaire du joueur
	public void saveInventoryItem(Player player) throws SQLException {

		clearTableInventory(); // on vide d'abord la table
		saveInventoryCurrentWeapon(player); // Sauvegarde de l'arme actuelle du joueur dans son inventaire
		String sql = "INSERT INTO inventory (type, nom, description, amount, classe)" + "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (Item i : player.getInventory()) {

				stmt.setString(1, i.getType());
				stmt.setString(2, i.getNom());
				stmt.setString(3, i.getDescription());
				stmt.setInt(4, i.getAmount());

				if (i.getType().equals("Weapon")) {
					stmt.setString(5, ((Weapon) i).getClasse().getName());
				} else {
					stmt.setString(5, "");
				}

				stmt.executeUpdate();

			}

		}

	}

	// Sauvegarde de l'arme actuelle du joueur dans son inventaire
	public void saveInventoryCurrentWeapon(Player player) throws SQLException {

		clearTableInventory(); // on vide d'abord la table
		String sql = "INSERT INTO inventory (type, nom, description, amount, classe) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, player.getCurrentWeapon().getType());
			stmt.setString(2, player.getCurrentWeapon().getNom());
			stmt.setString(3, player.getCurrentWeapon().getDescription());
			stmt.setInt(4, player.getCurrentWeapon().getAmount());
			stmt.setString(5, player.getCurrentWeapon().getClasse().getName());

			stmt.executeUpdate();

		}

	}

	// Annexe
	public void clearTableInventory() throws SQLException {
		String sql = "DELETE FROM inventory";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}

// ========================== Chargement ===========================

	public List<Item> loadPlayerInventory() throws SQLException {

		List<Item> inventory = new ArrayList<>();
		deleteCurrentWeaponInventory();// Suppression de l'arme actuelle du joueur de la table
		String sql = "SELECT * FROM inventory";

		try (Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				inventory.add(ItemFactory.getItem(rs.getString("type"), rs.getString("nom"),
						rs.getString("description"), rs.getInt("amount"), rs.getString("classe")));
			}
		}
		return inventory;
	}

	public Item loadPlayerCurrentWeapon() throws SQLException {

		Item CurrentWeapon = null;
		String sql = "SELECT * FROM inventory LIMIT 1";

		try (Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				CurrentWeapon = ItemFactory.getItem(rs.getString("type"), rs.getString("nom"),
						rs.getString("description"), rs.getInt("amount"), rs.getString("classe"));
			}
		}

		return CurrentWeapon;
	}

	// Annexe
	public void deleteCurrentWeaponInventory() throws SQLException {
		String sql = "DELETE FROM inventory LIMIT 1";
		try (Connection conn = DataBaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		}
	}

}
