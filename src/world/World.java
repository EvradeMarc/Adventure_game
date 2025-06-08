package world;

import java.util.ArrayList;
import java.util.List;

import factory.Place;
import observer.Player;

public class World {


	private static World instance;

	public int getHintPlace() {
		return hintPlace;
	}

	public void setHintPlace() {
		this.hintPlace = findPosition();
	}

	private List<Place> places = new ArrayList<>();
	private Player player;
	private int hintPlace ;

	private World() {
	}


	public static World getInstance() {
    	if(instance == null) {
    		instance = new World();
    	}

    	return instance;
    }


	public int findPosition()
	{
		for(Place pl : places) {
			if(pl.getNom().equals(player.getCurrentPlace().getNom())) {
				return places.indexOf(pl);
			}
		}
		return 0;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public boolean gameFinished() {

		for(Place pl : places) {
			if (!pl.isCompleted() ) {
				return false;
			}
		}

		return true;

	}

	public boolean canGoToNextPlace() {
		if((hintPlace + 1) == places.size()) {
			return false;
		}else if(!(places.get(hintPlace + 1).isLocked())) {
			return true;
		}
		return false;
	}

	public boolean canGoToPreviousPlace() {
			return !(hintPlace  == 0);
	}

	public  void goToNextPlace() {
		places.remove(hintPlace);
		places.add(hintPlace, player.getCurrentPlace());
		hintPlace+=1;
		player.setCurrentPlace(places.get(hintPlace));


	}
	public void goToPreviousPlace() {
		places.remove(hintPlace);
		places.add(hintPlace, player.getCurrentPlace());
		hintPlace-=1;
		player.setCurrentPlace(places.get(hintPlace));

	}

	public void openNextPlace() {

		if(!((hintPlace + 1) == places.size())) {
			places.get(hintPlace + 1).setLocked(false);
		}

	}



}
