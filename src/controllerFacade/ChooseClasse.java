package controllerFacade;

import java.io.IOException;
import java.sql.SQLException;

import dao.PlaceDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observer.GameManager;
import observer.Player;
import world.World;

public class ChooseClasse {

	private String specification;
	private static String classe;
	private PlaceDao placeDao = new PlaceDao();
	private  World gameWorld ;




    @FXML
    private Text classeSpecification;

    @FXML
    private Button validateClasseButton;


    @FXML
    public void initialize() {
        validateClasseButton.setDisable(true);
        gameWorld = World.getInstance();

    }

    @FXML
    void showFighterSpecification(ActionEvent event) {
    	classe = "Fighter";
    	specification  = "Combattant\n";
    	specification += "Dégats : 10\n";
    	specification += "Defense : 20\n";
    	specification += "Chance : 10\n";
    	specification += "Arme : Gant basique, Coup de poing, 8 dégâts\n";

    	classeSpecification.setText(specification);
    	validateClasseButton.setDisable(false);

    }

    @FXML
    void showMagicianSpecification(ActionEvent event) {
    	classe = "Magician";
    	specification  = "Mage\n";
    	specification += "Dégats : 30\n";
    	specification += "Defense : 10\n";
    	specification += "Chance : 20\n";
    	specification += "Arme : Baton basique, Sort de feu, 4 dégâts\n";

    	classeSpecification.setText(specification);
    	validateClasseButton.setDisable(false);

    }

    @FXML
    void showWarriorSpecification(ActionEvent event) {
    	classe = "Warrior";
    	specification  = "Guerrier\n";
    	specification += "Dégats : 20\n";
    	specification += "Defense : 30\n";
    	specification += "Chance : 5\n";
    	specification += "Arme : Epee basique, Coup d'épée, 9 dégâts\n";

    	classeSpecification.setText(specification);
    	validateClasseButton.setDisable(false);

    }



    @FXML
    void goToGame(ActionEvent event) {
    	try {


    		Player player = new Player(SaveName.getNameInter(),classe, placeDao.loadFirstPlace());
    		GameManager gm = GameManager.getInstance();
    		gm.registerPlayer(player);

        	gameWorld.setPlaces(placeDao.loadNewWorld());
        	gameWorld.setPlayer(player);
        	gameWorld.setHintPlace();


    		Parent gameView = FXMLLoader.load(getClass().getResource("/View/gamepart.fxml"));
			Scene gameScene = new Scene(gameView);

			 // Récupérer la fenêtre actuelle via l’événement
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	        stage.setScene(gameScene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }

}
