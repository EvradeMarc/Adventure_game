package state;

import observer.Player;

public class Poisoned extends CharacterState{

	public Poisoned(Player player) {
		super(player);
		this.status="Poisoned";
	}

	@Override
	public void poisonedState() {
		// TODO Auto-generated method stub
		System.out.println("Vous êtes déjà empoisonné");

	}

	@Override
	public void reinforcedState() {
		// TODO Auto-generated method stub
		System.out.println("Vous ne pouvez pas être reinforcé");

	}

	@Override
	public void normalState() {
		// TODO Auto-generated method stub
		System.out.println("Vous revenez à l'etat normal");
		player.setState(new Normal(player));
	}


	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		player.setHealthPoint(player.getHealthPoint()-1);
		System.out.println("Etat : Empoisonné, Vous perdez 1 HP");
	}

}
