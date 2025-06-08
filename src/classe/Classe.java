package classe;
import strategy.FightingStrategy;

public abstract class Classe {
   FightingStrategy strategy;
   String name;

	public FightingStrategy getStrategy() {
		return strategy;
	}

	public String getName() {
		return name;
	}

}
