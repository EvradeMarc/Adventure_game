package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {


	 	private static final String URL = "jdbc:mysql://localhost:3306/textual_adventure_game";
	    private static final String USER = "root";
	    private static final String PASSWORD = ""; // mot de passe vide avec XAMPP


	    public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            throw new RuntimeException("Erreur de connexion à la base de données", e);
	        }
	    }

	    public static void main(String[] args) {
	        Connection conn = getConnection();
	        if (conn != null) {
	            System.out.println("Connexion réussie !");
	        }
	    }

}
