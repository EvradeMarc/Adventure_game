package state;

import observer.Player;

public class Reinforced extends CharacterState{

	public Reinforced(Player player) {
		super(player);
		this.status="Reinforced";
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
		System.out.println("Vous êtes déjà renforcé");
	}

	@Override
	public void normalState() {
		// TODO Auto-generated method stub
		System.out.println("Vous ne pouvez pas revenir à l'etat normal");
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		System.out.println("Etat : Renforcé");
	}

}
