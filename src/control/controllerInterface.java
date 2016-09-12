package control;

import model.Place;
import model.Player;


/**
 * this is the controller interface
 * @author Jasmine and Risham
 *
 */
public interface controllerInterface {
	/**
	 * For running the program
	 * */
	public void play();
	/**
	 * @param input
	 * @param player
	 * @param otherplayer
	 * @return boolean for true or false 
	 * */
	public boolean runTurn(String input, Player player, Player otherPlayer);
	/**
	 * @param start
	 * @param end
	 * @param player
	 * @param otherPlayer
	 * @return boolean if the player is out of check or not
	 * */
	public boolean outOfCheck(Place start, Place end, Player player, Player otherPlayer);
	
	/**
	 * @param start
	 * @param end
	 * @return boolean for valid move or not
	 * */
	public boolean validateMove(Place start, Place end);
	
	}