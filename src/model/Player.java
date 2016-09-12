/***
 * 
 */
package model;

import java.util.ArrayList;

/***
 * Player method for player class
 * @author Jasmine and Risham
 *
 */
public class Player {
	/**
	 * all the global variables
	 * */
	ArrayList <Piece> dead = new ArrayList<Piece>();
	ArrayList <Piece> living = new ArrayList<Piece>();
	int color; //0 for black and 1 for white
	String name; //name of a person
	public boolean inCheck = false; //if a player is in check, then their next move MUST be to escape check
	public Piece king; //the place where their king is at
	
	/**
	 * constructor
	 * @param name
	 * @param color
	 * */

	public Player(String name, int color){
		this.name = name;
		this.color = color;
		
	}
	
	/**
	 * @return piece for king
	 * */
	public Piece getKing() {
		return king;
	}
	/**
	 * @param king piece 
	 * */
	public void setKing(Piece king) {
		this.king = king;
	}
	/**
	 * @return ArrayList<Piece> for deadpiece of the player
	 * */	
	public ArrayList<Piece> getDead() {
		return dead;
	}
	/**
	 * @return dead for deadpiece of the player
	 * */	
	public void setDead(ArrayList<Piece> dead) {
		this.dead = dead;
	}
	/**
	 * @return ArrayList<Piece> for living piece of the player
	 * */	
	public ArrayList<Piece> getLiving() {
		return living;
	}
	/**
	 * @param living for living piece of the player
	 * */	
	public void setLiving(ArrayList<Piece> living) {
		this.living = living;
	}
	/**
	 * @return int for color of the player
	 * */	
	public int getColor() {
		return color;
	}
	/**
	 * @param color of the player
	 * */	
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * @return String name of the player
	 * */	
	public String getName() {
		return name;
	}
	/**
	 * @param name for name of the player
	 * */	
	public void setName(String name) {
		this.name = name;
	}
}