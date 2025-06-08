package classe;
import strategy.HandToHand;

public class Fighter extends Classe{

	public Fighter() {
		this.strategy = new HandToHand();
		this.name = "Fighter";
	}


}
