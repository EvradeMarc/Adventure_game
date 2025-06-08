package factory;

import classe.Classe;
import classe.Fighter;
import classe.Magician;
import classe.Warrior;

public class ClasseFactory {

	public static Classe getClasse(String type) {

			switch(type) {

			case "Fighter":
				return new Fighter();
			case "Magician":
				return new Magician();
			case "Warrior":
				return new Warrior();
			default :
					return null;

			}
		}

}
