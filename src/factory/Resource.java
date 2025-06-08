package factory;

import observer.GameManager;
import observer.Player;

public class Resource extends Item{


	public Resource(String nom, String description, int amount) {
		super(nom, description, amount);
		this.type = "Resource";

	}

	@Override
	public void useItem(Player player) {
		GameManager gm = GameManager.getInstance();
		gm.notifyPlayer("Vous combinez votre arme avec " + nom + " elle gagne " + amount + "d'attaque");
		player.getCurrentWeapon().combineWithResource(amount);
	}


	@Override
	public String toString() {
		return this.nom + " - combinée confère " + this.amount + " dégâts";
	}
}
