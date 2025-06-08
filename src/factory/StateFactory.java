package factory;

import observer.Player;
import state.CharacterState;
import state.Normal;
import state.Poisoned;
import state.Reinforced;

public class StateFactory {

	public static CharacterState getState(String type, Player player) {

			switch(type) {

			case "Normal":
				return new Normal(player);
			case "Poisoned":
				return new Poisoned(player);
			case "Reinforced":
				return new Reinforced(player);
			default :
					return null;

			}
		}

}
