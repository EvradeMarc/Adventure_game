package controllerFacade;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dialogue {

	@FXML
    private Button answerBrotherButton;

    @FXML
    private Button answerWhoAreYouButton;

    @FXML
    private Button dialogFinish;

    @FXML
    private Text firstNPCResponse;

    @FXML
    private Text firstResponse;

    @FXML
    private Text nameField;

    @FXML
    private Text secondNPCResponse;

    @FXML
    private Text secondResponse;


    @FXML
    public void initialize() {
        // Liaison simple : bouton activé si inputField n'est pas vide
    	dialogFinish.setDisable(true);
    	secondResponse.setVisible(false);
    	firstResponse.setVisible(false);

    	nameField.setText(SaveName.getNameInter());
    }


    @FXML
    void answerBrother(ActionEvent event) {
    	if(firstNPCResponse.getText().isBlank()) {
    		firstNPCResponse.setText("J'ai laissé ton frère au village contre la horde, je ne sais pas ce qu'il est advenu de lui");
    		firstResponse.setText("Vous : Et mon frère ?");
        	firstResponse.setVisible(true);

    	}else {
    		secondNPCResponse.setText("J'ai laissé ton frère au village contre la horde, je ne sais pas ce qu'il est advenu de lui");
    		secondResponse.setText("Vous : Et mon frère ?");
    		secondResponse.setVisible(true);
    		dialogFinish.setDisable(false);
    	}

    	answerBrotherButton.setVisible(false);
		answerBrotherButton.setManaged(false);

    }

    @FXML
    void answerWhoAreYou(ActionEvent event) {

    	if(firstNPCResponse.getText().isBlank()) {
    		firstNPCResponse.setText("Je suis un Aventurier");
    		firstResponse.setText("Vous : Qui êtes vous ?");
        	firstResponse.setVisible(true);


    	}else {
    		secondNPCResponse.setText("Je suis un Aventurier");
    		secondResponse.setText("Vous : Qui êtes vous ?");
    		secondResponse.setVisible(true);
    		dialogFinish.setDisable(false);
    	}

    	answerWhoAreYouButton.setVisible(false);
    	answerWhoAreYouButton.setManaged(false);

    }

    @FXML
    void goToChooseClasse(ActionEvent event) {

    	try {


			Parent gameView = FXMLLoader.load(getClass().getResource("/View/chooseclasse.fxml"));
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
