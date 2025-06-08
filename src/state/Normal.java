package state;

import observer.Player;

public class Normal extends CharacterState{

	public Normal(Player player) {
		super(player);
		this.status = "Normal";
	}

	@Override
	public void poisonedState() {
		// TODO Auto-generated method stub
		System.out.println("Vous êtes empoisonné");
		player.setState(new Poisoned(player));
	}

	@Override
	public void reinforcedState() {
		// TODO Auto-generated method stub
		System.out.println("Vous êtes renforcé");
		player.setState(new Reinforced(player));

	}

	@Override
	public void normalState() {
		// TODO Auto-generated method stub
		System.out.println("Vous êtes déjà à l'etat normal");
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		System.out.println("Etat : Normal");
	}

}
