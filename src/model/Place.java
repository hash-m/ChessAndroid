package model;

import android.widget.ImageButton;

/***
 * this class is for place for the array
 * @author Jasmine and Risham
 *
 */
public class Place {
	
	/**
	 * all the global fields
	 * */
	char x, y;
	Piece piece;
	String name; 
	public ImageButton place;
	/**
	 * @param x
	 * @param y
	 * @param piece
	 * @param name
	 * this is constructor
	 * */
	public Place(char x, char y, Piece piece, String name){
		this.x = x;
		this.y = y;
		this.piece = piece;
		this.name = name;
	}
	/**
	 * @return char for x
	 * */
	public char getX() {
		return x;
	}
	/**
	 * @param x char for x
	 * */
	public void setX(char x) {
		this.x = x;
	}
	/**
	 * @return char for y
	 * */
	public char getY() {
		return y;
	}
	/**
	 * @param y char for y
	 * */
	public void setY(char y) {
		this.y = y;
	}
	/**
	 * @return piece for piece at that place
	 * */
	public Piece getPiece() {
		return piece;
	}
	/**
	 * @param piece for piece at that place
	 * */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	/**
	 * @return string for name at that place
	 * */
	public String getName() {
		return name;
	}
	/**
	 * @param name string for name at that place
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return string for printing of the piece
	 * */
	public String toString(){
		if(piece==null)
		return name + " ";
		else
		return piece.getName() + " ";
	}
}
