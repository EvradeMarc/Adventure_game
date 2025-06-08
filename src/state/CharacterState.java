package state;

import observer.Player;

public abstract class CharacterState {

	protected Player player;
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CharacterState(Player player) {
		this.player = player;
	}

	public abstract void poisonedState();
	public abstract void reinforcedState();
	public abstract void normalState();
	public abstract void doAction();

}
