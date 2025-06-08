package observer;

public class GameManager {
	private Player player;
	private static GameManager instance;

	private GameManager() {}

    public static GameManager getInstance() {
    	if(instance == null) {
    		instance = new GameManager();
    	}

    	return instance;
    }

	public void notifyPlayer(String msg) {
		player.update(msg);
	}

	public void registerPlayer(Player player) {
		this.player = player;
	}

}
