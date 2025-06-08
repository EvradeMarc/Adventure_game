package factory;

import classe.Classe;
import observer.GameManager;
import observer.Player;

public class Weapon extends Item{

	private Classe classe;

	public Weapon(String nom, String description, int amount,String classe) {
		super(nom, description, amount);
		this.type = "Weapon";
		this.classe = ClasseFactory.getClasse(classe);
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
		GameManager gm = GameManager.getInstance();

		if(player.getCurrentWeapon() == this) {
			gm.notifyPlayer("ATTAQUE : " + description);
			player.setBonusDamage(bonusamount(player));
		}else {
			gm.notifyPlayer("Changement d'arme");
		}

	}

	public int bonusamount(Player player) {

		if(player.preferredWeaponPlayer()) {
			return 10 + amount;
		}else {
			return 10;
		}

	}

	public void combineWithResource(int amount) {
		this.amount += amount;
	}

	@Override
	public String toString() {
		return this.nom + " - " + this.amount + " dégâts";
	}




}
