package factory;

public class ItemFactory {

	public static Item getItem(String type, String nom,String description, int amount, String classe) {

		switch(type) {

		case "Resource":
			return new Resource(nom, description, amount);
		case "Weapon":
			return new Weapon(nom, description, amount, classe);
		case "Reinforcement":
			return new Reinforcement(nom, description, amount);
		case "Heal":
			return new Heal(nom, description, amount);
		default :
				return null;

		}
	}

}
