package factory;

import java.util.List;

public class Place extends ItemType{


	private int progression;
	private boolean locked ;
	private boolean completed;

// ++++++++++++++++++++++++++ Classes ++++++++++++++++++++++++++
	private List<Enemy> enemies;
	private List<Enemy> copyEnemies;
	private List<Item> items;
	private Enemy boss;


// ++++++++++++++++++++++++++ Constructeurs ++++++++++++++++++++++++++

	public Place(String nom,String description, List<Enemy> enemies, List<Item> items, Enemy boss, int progression, boolean locked, boolean completed) {

		this.nom = nom;
		this.description = description;
		this.enemies = enemies;
		this.progression = progression;
		this.locked = locked;
		this.completed = completed;
		this.boss = boss;
		this.items = items;

	}


	public Enemy getBoss() {
		return boss;
	}


	public void setBoss(Enemy boss) {
		this.boss = boss;
	}


	public int getProgression() {
		return progression;
	}


	public void setProgression(int progression) {
		this.progression = progression;
	}


	public boolean isCompleted() {
		return completed;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public List<Enemy> getEnemies() {
		return enemies;
	}
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean isLocked) {
		this.locked = isLocked;
	}


	public void checkThePlaceCompletion() {
		this.progression = (int) ((((this.enemies.size() - numberEnemiesStillAlive())*1.0) / this.enemies.size())*98);

		if(!this.boss.isAlive()) {
			this.progression = 100;
			 completed = true;
		}
	}

	public Item dropItem() {
		return items.removeFirst();
	}

	public int numberEnemiesStillAlive() {

		int numberEnemies = 0;

		for(Enemy e : enemies) {
			if(e.isAlive()) {
				numberEnemies+=1;
			}
		}
		return numberEnemies;

	}

	public Enemy getEnemyAlive() {

		for(Enemy e : enemies) {
			if(e.isAlive()) {
				return e;
			}
		}
		return null;

	}

	public void setEnemyDead(Enemy enemy) {

		for(Enemy e : enemies) {
			if(e.getNom().equals(enemy.getNom())) {
				e.setAlive(false);
			}
		}
	}

}




