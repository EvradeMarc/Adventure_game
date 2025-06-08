package factory;

public class Enemy extends ItemType{

  private int healthPoint;
  private int damage;
  private boolean isAlive;


  public Enemy(String nom, String description,int damage,boolean isAlive) {
	this.nom = nom;
	this.description = description;
	this.healthPoint = 100;
	this.damage = damage;
	this.isAlive = isAlive;
}


public boolean isAlive() {
	return isAlive;
}


public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;
}


public int getHealthPoint() {
	return healthPoint;
}


public void setHealthPoint(int healthPoint) {

	if(healthPoint > 0) {
		this.healthPoint = healthPoint;
	}else {
		this.healthPoint = 0;
		this.isAlive = false;
	}
}


public int getDamage() {
	return damage;
}


public void setDamage(int damage) {
	this.damage = damage;
}

public void receiveDamage(int damage) {
	setHealthPoint(this.healthPoint-damage);
  }


@Override
public String toString() {
	String infoEnemy = this.nom + "\n";
	infoEnemy += this.description + "\n";
	infoEnemy += "HP : "+ this.healthPoint ;
	return infoEnemy;
 }


/*
 public static void main(String[] args) {
	Enemy test = new Enemy("Mordoc","Sous-fifre d'helidia",10);
	Enemy test2 = new Enemy("Mordoze","So-fifre d'helidia",1);

}
*/


}
