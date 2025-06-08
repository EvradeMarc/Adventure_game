package factory;

import observer.Player;

public abstract class Item extends ItemType{

	int amount;

	String type;

	public Item(String nom,String description, int amount) {
		this.nom = nom;
		this.description = description;
		this.amount = amount;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public abstract void useItem(Player player);


}
