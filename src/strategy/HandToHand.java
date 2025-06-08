package strategy;

import observer.GameManager;
import observer.Player;

public class HandToHand implements FightingStrategy {

	@Override
	public int executeStrategy(Player player) {
		// TODO Auto-generated method stub
		GameManager gm = GameManager.getInstance();
		try {

			if (player.preferredWeaponStrategy()) {
				player.getCurrentWeapon().useItem(player);
				return player.getDefaultDamage() + player.getBonusDamage();

			} else {
				gm.notifyPlayer("Arme pas Adapté - Vous faites un coup basique" );
				return player.getDefaultDamage();
			}

		} catch (NullPointerException e) {
			// TODO: handle exception
			gm.notifyPlayer("Pas d'Arme - Vous faites un coup basique" );
			return player.getDefaultDamage();
		}

	}

}
