package controllerFacade;
import java.io.IOException;
import java.sql.SQLException;

import dao.PlaceDao;
import dao.PlayerDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import world.World;

public class StartTheGame {

		private World gameWorld ;


		@FXML
	    public void initialize() {
			PlayerDao playerDao = new PlayerDao();
			
	        gameWorld = World.getInstance();
	    }

	    @FXML
	    void loadGame(ActionEvent event) {


	    	try {

	    		 PlaceDao placeDao = new PlaceDao();
	    		 PlayerDao playerDao = new PlayerDao();

	    		 gameWorld.setPlaces(placeDao.loadWorld());
	    		 gameWorld.setPlayer(playerDao.loadPlayer());
	    		 gameWorld.setHintPlace();

				Parent gameView = FXMLLoader.load(getClass().getResource("/View/gamepart.fxml"));
				Scene gameScene = new Scene(gameView);

				 // Récupérer la fenêtre actuelle via l’événement
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		        stage.setScene(gameScene);
		        stage.show();
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	    }

	    @FXML
	    void startNewGame(ActionEvent event) {

			try {
				Parent gameView = FXMLLoader.load(getClass().getResource("/View/askname.fxml"));
				Scene gameScene = new Scene(gameView);

				 // Récupérer la fenêtre actuelle via l’événement
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		        stage.setScene(gameScene);
		        stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void quitGame(ActionEvent event) {

	    	Platform.exit();

	    }




}
