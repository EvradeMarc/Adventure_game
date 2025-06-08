package controllerFacade;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaveName {



	    private static String nameInter;

		public static String getNameInter() {
			return nameInter;
		}


		@FXML
	    private TextField nameField;

	    @FXML
	    private Button saveNameButton;

	    @FXML
	    public void initialize() {
	        // Liaison simple : bouton activé si inputField n'est pas vide
	    	saveNameButton.disableProperty().bind(
	    			nameField.textProperty().isEmpty()
	        );
	    }

	    @FXML
	    void goToDialog(ActionEvent event) {
	    	try {

	    		nameInter = nameField.getText();

				Parent gameView = FXMLLoader.load(getClass().getResource("/View/dialogue.fxml"));
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



}
