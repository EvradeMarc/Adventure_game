package factory;

import observer.GameManager;
import observer.Player;

public class Reinforcement extends Item{



	public Reinforcement(String nom, String description, int amount) {
		super(nom, description, amount);
		this.type = "Reinforcement";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useItem(Player player) {
		GameManager gm = GameManager.getInstance();
		player.reinforcedState();
		gm.notifyPlayer("Vous gagnez " + amount + " point de defense ");
		player.setDefense(player.getDefense() + amount);


	}

	@Override
	public String toString() {
		return this.nom + " - confère " + this.amount + " defense";
	}



}
