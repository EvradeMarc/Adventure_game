package classe;
import strategy.MagicSpell;

public class Magician extends Classe{

	public Magician() {
		this.strategy = new MagicSpell();
		this.name = "Magician";
	}


}
