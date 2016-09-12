/***
 * 
 */
package model;

/***
 * this will be the interface class for piece
 * @author Jasmine and Risham
 *
 */
public interface Piece {
	/**
	 * @param doub  
	 * */
	
	public void setDoub(boolean doub) ;
	/**
	 * @param rep
	 * */
	
	public void setRep(int rep) ;
	
	
	
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	public int move(char x, char y);
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int for the error
	 * */
	public int check(char x, char y);
	/**
	 * @param name of the piece
	 * */
	public String getName();
	/**
	 * @return string of type
	 * */
	public String getType();
	/**
	 * @return int global variable
	 * */
	public int getRep();
	/**
	 * @return boolean for the double or not
	 * */
	public boolean isDoub();
	/**
	 * @return int color of the piece
	 * */
	public int getColor();
	/**
	 * @return char which is the x location
	 * */
	public char getX();
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int if its valid or not
	 * */
	public int checkMove(char x, char y);
	/**
	 * @return if its in check
	 * */
	public boolean isCheck();
	/**
	 * @param name to set name
	 * */
	public void setname(String name);
	/**
	 * @param x to set x
	 * */
	public void setX(char x);
	/**
	 * @param color of the piece
	 * */
	public void setColor(int color);
	/**
	 * @return y which is a char
	 * */
	public char getY();
	/**
	 * @param y which is to set the value
	 * */
	public void setY(char y);
	/**
	 * @return living if the piece is still living
	 * */
	public boolean isLiving();
	/**
	 * @param living to set the living
	 * */
	public void setLiving(boolean living);

}