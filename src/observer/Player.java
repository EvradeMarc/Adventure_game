package observer;

import java.util.ArrayList;
import java.util.List;

import classe.Classe;
import factory.ClasseFactory;
import factory.Item;
import factory.Place;
import factory.StateFactory;
import factory.Weapon;
import state.CharacterState;
import state.Normal;
import strategy.FightingStrategy;

public class Player implements Observer {

	private int healthPoint;
	private String name;
	private int defaultDamage;
	private int bonusDamage;
	private int level;
	private int defense;
	private int luck;
	private boolean isAlive;
	private int currentExperience;
	private int nextLevelExperience;

	private String notification;

// ++++++++++++++++++++++++++ Classes ++++++++++++++++++++++++++
	protected FightingStrategy strategy;
	private Classe classe;
	private List<Item> inventory;
	private Weapon currentWeapon;
	private CharacterState state;
	private Place currentPlace;

// ++++++++++++++++++++++++++ Constructeurs ++++++++++++++++++++++++++

	public Player(int healthPoint, String name, int initialDamage, int bonusDamage, int level, int defense, int luck,int experience, String classe, List<Item> inventory, Weapon currentWeapon, String state, Place currentPlace) {

		setHealthPoint(healthPoint);
		this.name = name;
		this.defaultDamage = initialDamage;
		this.bonusDamage = bonusDamage;
		this.level = level;
		this.defense = defense;
		this.luck = luck;
		this.currentExperience = experience;

		this.classe = ClasseFactory.getClasse(classe);
		this.inventory = inventory; // Dans une table qu'on va parcourir
		this.state = StateFactory.getState(state, this);
		this.currentWeapon = currentWeapon;
		this.currentPlace = currentPlace;

		this.isAlive = true;
		this.nextLevelExperience = level*30;
	}

	// NOUVELLE PARTIE - DEBUT DU JEU

	// Définition des attributs qui sont différents en début de jeu selon les classes
	// et aussi definir les arme du début
	public void initChangingAttributes(Classe classe) {

		switch (classe.getName()) {

		case "Fighter":
			setDefaultDamage(10);
			setDefense(20);
			setLuck(10);
			setCurrentWeapon(new Weapon("Gant basique","Coup de poing",8,classe.getName()));
			break;

		case "Magician":
			setDefaultDamage(30);
			setDefense(10);
			setLuck(20);
			setCurrentWeapon(new Weapon("Baton basique","Sort de feu",4,classe.getName()));


			break;

		case "Warrior":
			setDefaultDamage(20);
			setDefense(30);
			setLuck(5);
			setCurrentWeapon(new Weapon("Epee basique","Coup d'épée",9,classe.getName()));

			break;

		}

	}

	public Player(String name, String classe, Place currentPlace) {

		this.name = name;
		this.classe = ClasseFactory.getClasse(classe);

		this.currentPlace = currentPlace;
		this.healthPoint = 100;
		this.level = 1;
		this.bonusDamage = 0;
		this.isAlive = true;
		this.currentExperience = 0;
		this.nextLevelExperience = 30;

		initChangingAttributes(this.classe);

		this.state = new Normal(this);
		this.strategy = ClasseFactory.getClasse(classe).getStrategy();
		this.inventory = new ArrayList<>(); // Dans une table qu'on va parcourir

	}

// ++++++++++++++++++++++++++ Getters & Setters ++++++++++++++++++++++++++

	// notification
	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	// currentExperience
	public int getCurrentExperience() {
		return currentExperience;
	}

	public void setCurrentExperience(int experience) {
		if ((this.currentExperience + experience) >= nextLevelExperience) {
			this.currentExperience = 0;
			nextLevel();

		} else {
			this.currentExperience += experience;
		}

	}

	// isAlive
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// healthPoint
	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		if (healthPoint > 100) {
			this.healthPoint = 100;
		} else if (healthPoint > 0) {
			this.healthPoint = healthPoint;
		} else {
			this.healthPoint = 0;
			this.isAlive = false;
		}
	}

	// defaultDamage
	public int getDefaultDamage() {
		return defaultDamage;
	}

	public void setDefaultDamage(int initialDamage) {
		this.defaultDamage = initialDamage;
	}

	// level
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// defense
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	// luck
	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	// bonusDamage
	public int getBonusDamage() {
		return bonusDamage;
	}

	public void setBonusDamage(int bonusDamage) {
		this.bonusDamage = bonusDamage;
	}

	// nextLevelExperience
	public int getNextLevelExperience() {
		return nextLevelExperience;
	}

	public void setNextLevelExperience(int nextLevelExperience) {
		this.nextLevelExperience = nextLevelExperience;
	}

	// ++++++++++++++++++++++++++ Classes ++++++++++++++++++++++++++

	// currentPlace
	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	// state
	public CharacterState getState() {
		return state;
	}

	public void setState(CharacterState state) {
		this.state = state;
	}

	// inventory
	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	// curretWeapon
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	// classe
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	// strategy
	public FightingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(FightingStrategy strategy) {
		this.strategy = strategy;
	}

// ++++++++++++++++++++++++++ Informations du joueur ++++++++++++++++++++++++++

	/*
	 * //En cours de combat Kun - Mage NIV : 20 | EXP : 203/500 HP : 100 | Attaque
	 * |Defense : 30 | Luck : 50 Arme équipée : Sabre,1000 étoiles | Degats : 10
	 * Statut : Empoisonné
	 *
	 * Que voulez-vous faire ? 1- ATTAQUER 2- ESQUIVER 3- UTILISER UN OBJET 4- FUIR
	 *
	 */

// ++++++++++++++++++++++++++ Méthodes STATE ++++++++++++++++++++++++++

	public void poisonedState() {
		state.poisonedState();
	}

	public void reinforcedState() {
		state.reinforcedState();
	}

	public void normalState() {
		state.normalState();
	}

	public void doAction() {
		state.doAction();
	}

// ++++++++++++++++++++++++++ Méthodes En jeu ++++++++++++++++++++++++++

	// ++++++ AUSSI STRATEGY +++++++
	// représente mon "attaque" sera certainement dans une méthode "attack" dans un
	// facade

	public int applyStrategy() {
		return strategy.executeStrategy(this);
	}

	// ++++++++++++++++++++++++++++++

	public void receiveDamage(int damage) {
		damage = damage - (defense / 100) * damage;
		setHealthPoint(this.healthPoint - damage);
	}

	public void nextLevel() {
		setLevel(this.level + 1);
		setDefense(this.defense + 10);
		setDefaultDamage(this.defaultDamage + 10);
		setLuck(this.luck + 5);
		setNextLevelExperience(this.level*30);
	}



	public void receiveItem(Item item) {
		inventory.add(item);
	}

// ++++++++++++++++++++++++++ Méthodes OBSERVER ++++++++++++++++++++++++++

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		setNotification(msg);
	}

// ++++++++++++++++++++++++++ Autres Méthodes ++++++++++++++++++++++++++

	/*
	 * Vérification si la classe actuelle du joueur correspond à la stratégie de
	 * combat (approprié)
	 *
	 */
	public boolean preferredClasseStrategy() {
		return classe.getStrategy().getClass() == strategy.getClass();
	}

	/*
	 * Vérification si l'arme actuelle du joueur est adapté/correspond à la
	 * stratégie de combat (approprié)
	 *
	 * DONC IL PEUT UTILISER L'ARME
	 */
	public boolean preferredWeaponStrategy() {
		return currentWeapon.getClasse().getStrategy().getClass() == strategy.getClass();
	}

	/*
	 * Vérification si la classe de l'arme actuelle du joueur est adapté/correspond
	 * à sa classe (approprié)
	 *
	 */
	public boolean preferredWeaponPlayer() {
		return currentWeapon.getClasse().getClass() == classe.getClass();
	}

	@Override
	public String toString() {
		String infoPlayer = this.name + " | " + this.classe.getName()+ "\n";
		infoPlayer += "Statut : " + this.state.getStatus() + "\n";
		infoPlayer += "Level : " + this.level + " | exp : " + this.currentExperience + "/" + this.nextLevelExperience + "\n" ;
		infoPlayer += "HP : " + this.healthPoint + " | Attaque : " + this.defaultDamage + " | Bonus d'attaque : " + this.bonusDamage + "\n";
		infoPlayer += "Defense : " + this.defense + " | Chance : " + this.luck + " \n ";
		infoPlayer += "Arme : " + this.getCurrentWeapon().getNom() + ", " + this.getCurrentWeapon().getDescription() + ", " + this.getCurrentWeapon().getAmount() + " dégâts\n";
		return infoPlayer;
	}

}
