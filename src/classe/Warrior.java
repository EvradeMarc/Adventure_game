package classe;
import strategy.WeaponMidLongRange;

public class Warrior extends Classe{

	public Warrior() {
		this.strategy = new WeaponMidLongRange();
		this.name = "Warrior";
	}

}
