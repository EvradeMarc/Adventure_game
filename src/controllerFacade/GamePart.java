package controllerFacade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import dao.ItemDao;
import dao.PlaceDao;
import dao.PlayerDao;
import factory.Heal;
import factory.Item;
import factory.Place;
import factory.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import world.World;

public class GamePart {

	private World gameWorld;

	@FXML
	private Pane meetEnemyPane;

	@FXML
	private ListView<Item> inventoryView;

	@FXML
	private Text persoInfo;

	@FXML
	private Text placeName;

	@FXML
	private Text progressionField;

	@FXML
	private Text receiveObject;

	/*
	 * @FXML private Button validateClasseButton;
	 */
	@FXML
	private Button mainMenuButton;

	@FXML
	private Button exploreButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button nextPlace;

	@FXML
	private Button previousPlace;

	@FXML
	private Button useItemButton;

	@FXML
	public void initialize() {

		gameWorld = World.getInstance();

		mainMenuButton.setDisable(false);
		exploreButton.setDisable(false);
		saveButton.setDisable(false);
		useItemButton.setDisable(false);

		placeName.setText(gameWorld.getPlayer().getCurrentPlace().getNom());
		meetEnemyPane.setVisible(false);
		persoInfo.setText(gameWorld.getPlayer().toString());
		receiveObject.setText("");
		progressionField.setText("Progression : " + gameWorld.getPlayer().getCurrentPlace().getProgression() + "%");
		ObservableList<Item> items = FXCollections.observableArrayList(gameWorld.getPlayer().getInventory());
		inventoryView.setItems(items);

		if (gameWorld.canGoToPreviousPlace()) {
			previousPlace.setVisible(true);
		} else {
			previousPlace.setVisible(false);
		}

		if (gameWorld.canGoToNextPlace()) {
			nextPlace.setVisible(true);
		} else {
			nextPlace.setVisible(false);
		}

	}

	@FXML
	void Explore(ActionEvent event) {

		Random rand = new Random();

		if (gameWorld.getPlayer().getCurrentPlace().isCompleted()
				&& gameWorld.getPlayer().getCurrentPlace().getItems().size() == 0) {
			receiveObject.setText("Vous ne trouvez rien");
		} else if (gameWorld.getPlayer().getCurrentPlace().isCompleted()) {
			receiveObject.setText("Vous avez ramassé un objet");
			gameWorld.getPlayer().getInventory().add(gameWorld.getPlayer().getCurrentPlace().dropItem());

		} else if (gameWorld.getPlayer().getCurrentPlace().getItems().size() == 0) {

			meetEnemyPane.setVisible(true);

			mainMenuButton.setDisable(true);
			exploreButton.setDisable(true);
			saveButton.setDisable(true);
			previousPlace.setDisable(true);
			nextPlace.setDisable(true);
			useItemButton.setDisable(true);

		} else {

			if ((rand.nextDouble() <= (0.5 + (gameWorld.getPlayer().getLuck() * 1.0) / 100))) {

				receiveObject.setText("Vous avez ramassé un objet");
				gameWorld.getPlayer().getInventory().add(gameWorld.getPlayer().getCurrentPlace().dropItem());

			} else {

				meetEnemyPane.setVisible(true);

				mainMenuButton.setDisable(true);
				exploreButton.setDisable(true);
				saveButton.setDisable(true);
				previousPlace.setDisable(true);
				nextPlace.setDisable(true);
				useItemButton.setDisable(true);

			}

		}

		ObservableList<Item> items = FXCollections.observableArrayList(gameWorld.getPlayer().getInventory());
		inventoryView.setItems(items);


	}

	@FXML
	void goToFight(ActionEvent event) {
		try {

			Parent gameView = FXMLLoader.load(getClass().getResource("/View/fightscene.fxml"));
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
	void goToMainMenu(ActionEvent event) {

		try {

			Parent gameView = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
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
	void goToNextPlace(ActionEvent event) {
		try {

			gameWorld.goToNextPlace();

			Parent gameView = FXMLLoader.load(getClass().getResource("/View/gamepart.fxml"));
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
	void goToPreviousPlace(ActionEvent event) {
		try {

			gameWorld.goToPreviousPlace();

			Parent gameView = FXMLLoader.load(getClass().getResource("/View/gamepart.fxml"));
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
	void saveTheGame(ActionEvent event) {

		PlaceDao placeDao = new PlaceDao();
		PlayerDao playerDao = new PlayerDao();
		ItemDao itemDao = new ItemDao();
		try {
			itemDao.saveInventoryItem(gameWorld.getPlayer());
			playerDao.savePlayer(gameWorld.getPlayer());
			
			placeDao.saveWorld(gameWorld);
			placeDao.saveEnemiesPlace(gameWorld);
			placeDao.saveBossPlace(gameWorld);
			placeDao.saveItemsPlace(gameWorld);
			


		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@FXML
	void useItem(ActionEvent event) {

		Item selected = inventoryView.getSelectionModel().getSelectedItem();

		if (selected != null) {
			selected.useItem(gameWorld.getPlayer());
			receiveObject.setText(gameWorld.getPlayer().getNotification());

			if (selected.getType().equals("Heal")) {
				if (((Heal) selected).isHasBeenUsed()) {
					gameWorld.getPlayer().getInventory().remove(selected);
				}
			} else if (selected.getType().equals("Weapon")) {
				gameWorld.getPlayer().getInventory().add(gameWorld.getPlayer().getCurrentWeapon());
				gameWorld.getPlayer().setCurrentWeapon(((Weapon) selected));

				gameWorld.getPlayer().getInventory().remove(selected);
			} else {

				gameWorld.getPlayer().getInventory().remove(selected);

			}

		}

		ObservableList<Item> items = FXCollections.observableArrayList(gameWorld.getPlayer().getInventory());
		inventoryView.setItems(items);
		persoInfo.setText(gameWorld.getPlayer().toString());

	}

}
