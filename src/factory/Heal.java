package factory;

import observer.GameManager;
import observer.Player;
import state.Poisoned;

public class Heal extends Item{

	private boolean hasBeenUsed ;


	public Heal(String nom, String description, int amount) {
		super(nom, description, amount);
		this.hasBeenUsed = false;
		this.type = "Heal";
		// TODO Auto-generated constructor stub
	}

	public boolean isHasBeenUsed() {
		return hasBeenUsed;
	}

	public void setHasBeenUsed(boolean hasBeenUsed) {
		this.hasBeenUsed = hasBeenUsed;
	}

	@Override
	public void useItem(Player player) {
		GameManager gm = GameManager.getInstance();

		if(player.getHealthPoint() == 100 && player.getState() != new Poisoned(player)) {
			gm.notifyPlayer("Le soin n'est utilisé, Etat : normal et  HP maximum");

		}else if(player.getState() == new Poisoned(player)){
			gm.notifyPlayer("Vous êtes guéri du poison");
			player.normalState();
			this.hasBeenUsed = true;
		}else {
			gm.notifyPlayer("Vous recevez " + amount + " HP");
			player.setHealthPoint(player.getHealthPoint() + amount);
			this.hasBeenUsed = true;
		}

	}

	@Override
	public String toString() {
		return this.nom + " - confère " + this.amount + " HP";
	}

}
