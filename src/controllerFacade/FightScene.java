package controllerFacade;

import java.io.IOException;
import java.util.Random;

import factory.Enemy;
import factory.Heal;
import factory.Item;
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
import strategy.HandToHand;
import strategy.MagicSpell;
import strategy.WeaponMidLongRange;
import world.World;

public class FightScene {


	private World gameWorld;

	private Enemy enemy;

    @FXML
    private Text attackName;

    @FXML
    private Text attackEnnemy;

    @FXML
    private Text enemyInfo;
    @FXML
    private Text persoInfo;
    @FXML
    private Text placeName;


    @FXML
    private ListView<Item> inventoryView;

    @FXML
    private Pane loseFightPane;
    @FXML
    private Pane winFightPane;


    @FXML
    private Button WeaponMidLongRangeButton;
    @FXML
    private Button useItemButton;
    @FXML
    private Button dodgeButton;
    @FXML
    private Button handToHandButton;
    @FXML
    private Button magicSpellButton;



    @FXML
    public void initialize() {

    	gameWorld = World.getInstance();

    	WeaponMidLongRangeButton.setDisable(false);
    	useItemButton.setDisable(false);
    	dodgeButton.setDisable(false);
    	handToHandButton.setDisable(false);
    	magicSpellButton.setDisable(false);


    	placeName.setText(gameWorld.getPlayer().getCurrentPlace().getNom());
    	loseFightPane.setVisible(false);
    	winFightPane.setVisible(false);
    	persoInfo.setText(gameWorld.getPlayer().toString());
    	attackName.setText("");
    	attackEnnemy.setText("");

    	if(gameWorld.getPlayer().getCurrentPlace().numberEnemiesStillAlive() == 0) {
    		this.enemy = gameWorld.getPlayer().getCurrentPlace().getBoss();
    		enemyInfo.setText("Boss\n" + this.enemy.toString());
    	}else {
    		this.enemy = gameWorld.getPlayer().getCurrentPlace().getEnemyAlive();
    		enemyInfo.setText(this.enemy.toString());
    	}

    	ObservableList<Item> items = FXCollections.observableArrayList(gameWorld.getPlayer().getInventory());
		inventoryView.setItems(items);


    }

    @FXML
    void attackHandToHand(ActionEvent event) {

    	gameWorld.getPlayer().setStrategy(new HandToHand());

    	enemy.receiveDamage(gameWorld.getPlayer().applyStrategy());
    	attackName.setText(gameWorld.getPlayer().getNotification());

    	if(gameWorld.getPlayer().getCurrentPlace().numberEnemiesStillAlive() == 0) {
    		enemyInfo.setText("Boss\n" + enemy.toString());
    	}else {
    		enemyInfo.setText(enemy.toString());
    	}

    	if(enemy.isAlive()) {
    		gameWorld.getPlayer().receiveDamage(enemy.getDamage());
    		persoInfo.setText(gameWorld.getPlayer().toString());

    		attackEnnemy.setText("Coup de " + enemy.getDescription());


    		if(!gameWorld.getPlayer().isAlive()) {

    			attackEnnemy.setText("");
    			enemyInfo.setText("");

    			loseFightPane.setVisible(true);

        		WeaponMidLongRangeButton.setDisable(false);
            	useItemButton.setDisable(false);
            	dodgeButton.setDisable(false);
            	handToHandButton.setDisable(false);
            	magicSpellButton.setDisable(false);


    		}


    	}else {
    		gameWorld.getPlayer().getCurrentPlace().setEnemyDead(enemy);

    		gameWorld.getPlayer().setCurrentExperience(10);

    		gameWorld.getPlayer().getCurrentPlace().checkThePlaceCompletion();
    		if(gameWorld.getPlayer().getCurrentPlace().isCompleted()) {
    			gameWorld.openNextPlace();
    		}

    		attackEnnemy.setText("");
			enemyInfo.setText("");

    		winFightPane.setVisible(true);

    		WeaponMidLongRangeButton.setDisable(false);
        	useItemButton.setDisable(false);
        	dodgeButton.setDisable(false);
        	handToHandButton.setDisable(false);
        	magicSpellButton.setDisable(false);
    	}

    }

    @FXML
    void attackMagicSpell(ActionEvent event) {
    	gameWorld.getPlayer().setStrategy(new MagicSpell());

    	enemy.receiveDamage(gameWorld.getPlayer().applyStrategy());
    	attackName.setText(gameWorld.getPlayer().getNotification());

    	if(gameWorld.getPlayer().getCurrentPlace().numberEnemiesStillAlive() == 0) {
    		enemyInfo.setText("Boss\n" + enemy.toString());
    	}else {
    		enemyInfo.setText(enemy.toString());
    	}

    	if(enemy.isAlive()) {
    		gameWorld.getPlayer().receiveDamage(enemy.getDamage());
    		persoInfo.setText(gameWorld.getPlayer().toString());

    		attackEnnemy.setText("Coup de " + enemy.getDescription());


    		if(!gameWorld.getPlayer().isAlive()) {

    			attackEnnemy.setText("");
    			enemyInfo.setText("");

    			loseFightPane.setVisible(true);

        		WeaponMidLongRangeButton.setDisable(false);
            	useItemButton.setDisable(false);
            	dodgeButton.setDisable(false);
            	handToHandButton.setDisable(false);
            	magicSpellButton.setDisable(false);

    		}


    	}else {
    		gameWorld.getPlayer().getCurrentPlace().setEnemyDead(enemy);

    		gameWorld.getPlayer().setCurrentExperience(10);

    		gameWorld.getPlayer().getCurrentPlace().checkThePlaceCompletion();
    		if(gameWorld.getPlayer().getCurrentPlace().isCompleted()) {
    			gameWorld.openNextPlace();
    		}

    		attackEnnemy.setText("");
			enemyInfo.setText("");

    		winFightPane.setVisible(true);

    		WeaponMidLongRangeButton.setDisable(false);
        	useItemButton.setDisable(false);
        	dodgeButton.setDisable(false);
        	handToHandButton.setDisable(false);
        	magicSpellButton.setDisable(false);
    	}

    }

    @FXML
    void attackWeaponMidLongRange(ActionEvent event) {

    	gameWorld.getPlayer().setStrategy(new WeaponMidLongRange());

    	enemy.receiveDamage(gameWorld.getPlayer().applyStrategy());
    	attackName.setText(gameWorld.getPlayer().getNotification());

    	if(gameWorld.getPlayer().getCurrentPlace().numberEnemiesStillAlive() == 0) {
    		enemyInfo.setText("Boss\n" + enemy.toString());
    	}else {
    		enemyInfo.setText(enemy.toString());
    	}

    	if(enemy.isAlive()) {
    		gameWorld.getPlayer().receiveDamage(enemy.getDamage());
    		persoInfo.setText(gameWorld.getPlayer().toString());

    		attackEnnemy.setText("Coup de " + enemy.getDescription());


    		if(!gameWorld.getPlayer().isAlive()) {

    			attackEnnemy.setText("");
    			enemyInfo.setText("");

    			loseFightPane.setVisible(true);

        		WeaponMidLongRangeButton.setDisable(false);
            	useItemButton.setDisable(false);
            	dodgeButton.setDisable(false);
            	handToHandButton.setDisable(false);
            	magicSpellButton.setDisable(false);

    		}


    	}else {
    		gameWorld.getPlayer().getCurrentPlace().setEnemyDead(enemy);

    		gameWorld.getPlayer().setCurrentExperience(10);

    		gameWorld.getPlayer().getCurrentPlace().checkThePlaceCompletion();

    		if(gameWorld.getPlayer().getCurrentPlace().isCompleted()) {
    			gameWorld.openNextPlace();
    		}

    		attackEnnemy.setText("");
			enemyInfo.setText("");

    		winFightPane.setVisible(true);

    		WeaponMidLongRangeButton.setDisable(false);
        	useItemButton.setDisable(false);
        	dodgeButton.setDisable(false);
        	handToHandButton.setDisable(false);
        	magicSpellButton.setDisable(false);
    	}

    }

    @FXML
    void dodgeAttack(ActionEvent event) {

    	Random rand = new Random();
    	if(!(rand.nextDouble() <= (0.2 + (gameWorld.getPlayer().getLuck()*1.0)/100) )) {

    		attackName.setText("Vous n'arrivez pas à esquiver");
    		gameWorld.getPlayer().receiveDamage(enemy.getDamage());
    		persoInfo.setText(gameWorld.getPlayer().toString());

    		attackEnnemy.setText("Coup de " + enemy.getDescription());


    		if(!gameWorld.getPlayer().isAlive()) {

    			attackEnnemy.setText("");
    			enemyInfo.setText("");

    			loseFightPane.setVisible(true);

        		WeaponMidLongRangeButton.setDisable(false);
            	useItemButton.setDisable(false);
            	dodgeButton.setDisable(false);
            	handToHandButton.setDisable(false);
            	magicSpellButton.setDisable(false);

    		}
    	}else {
    		attackName.setText("Vous réussissez à esquiver");
    	}

    }

    @FXML
    void returnMainGame(ActionEvent event) {

    	try {

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
    void returnMainMenu(ActionEvent event) {

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
    void useItem(ActionEvent event) {

    	Item selected = inventoryView.getSelectionModel().getSelectedItem();

		if (selected != null) {
			selected.useItem(gameWorld.getPlayer());
			attackName.setText(gameWorld.getPlayer().getNotification());

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
