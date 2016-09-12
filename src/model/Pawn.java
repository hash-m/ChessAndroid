/***
 * 
 */
package model;

import control.Controller;

/***
 *this class will have the pawn piece
 * @author Jasmine and Risham
 *
 */
public class Pawn implements Piece {
	/**
	 * x and y coordinates
	 * */
	char x;
	char y;
	/**
	 * if it is moved or not
	 * */
	int rep = 0 ;// number of times it moved
	
	/**
	 * if its doubled
	 * */
	boolean doub = false;
	/**
	 * living or not
	 * */
	boolean living;
	
	
	/***
	 * black = 0, white =1
	 */
	int color;
	
	/**
	 * name of the piece
	 * */
	String name;
	/**
	 * type
	 * */
	String type = "pawn";
	/**
	 * Contructor method 
	 * @param name name of the piece
	 * @param x location of the piece
	 * @param y location of the piece
	 * @param color color of the piece
	 * */
	public Pawn(String name, char x, char y, int color) {
		// turn the living on
		living = true;
		rep = 0;
		this.name = name;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	/**
	 * @return char which is the x location
	 * */
	public char getX() {
		return x;
	}
	/**
	 * @return string of type
	 * */
	public String getType(){
		return type;
	}
	/**
	 * @param name to set x
	 * */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * @return int global variable
	 * */
	public int getRep() {
		return rep;
	}
	/**
	 * @return boolean for the double or not
	 * */
	public boolean isDoub() {
		return doub;
	}
	/**
	 * @return if its in check
	 * */
	public boolean isCheck() {
		return false;
	}
	/**
	 * @param doub  
	 * */
	@Override
	public void setDoub(boolean doub) {
		this.doub = doub;
		
	}
	/**
	 * @param rep
	 * */
	@Override
	public void setRep(int rep) {
		this.rep = rep;
		
	}
	/**
	 * @param x to set x
	 * */
	public void setX(char x) {
		this.x = x;
	}
	
	/**
	 * @return y which is a char
	 * */
	public char getY() {
		return y;
	}
	
	/**
	 * @param y which is to set the value
	 * */
	public void setY(char y) {
		this.y = y;
	}
	/**
	 * @return living if the piece is still living
	 * */
	public boolean isLiving() {
		return living;
	}
	/**
	 * @param living to set the living
	 * */
	public void setLiving(boolean living) {
		this.living = living;
	}
	/**
	 * @return int color of the piece
	 * */
	public int getColor() {
		return color;
	}
	
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int for the error
	 * */

	public int checkMove(char x, char y) {

		if (Character.isDigit(y) && Character.isLetter(x)
				&& Character.isLowerCase(x)) {
			int y_index = y - '0';
			y_index--;
			// check if its between a to h
			// System.out.println("inside here: x " + (int)x + y_index);
			if (x >= 97 && x <= 104 && y_index >= 0 && y_index <= 7) {
				// System.out.println("inside here");
				int x_index = x - 49 - '0';
				// all the checks are done for that, now we need to
				// go into checking if the move is valid or not
				int curr_y = this.y - '0';
				curr_y--;
				int curr_x = this.x - 49 - '0';
				// 8 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if (curr_x == x_index
						&& ((curr_y - y_index == 1 && this.color == 0)
								|| (y_index - curr_y == 1 && this.color == 1) || (rep == 0 && ((curr_y
								- y_index == 2 && this.color == 0) || (y_index
								- curr_y == 2 && this.color == 1))))) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						if (rep == 0
								&& totalData.arrayBoard[(y_index + curr_y) / 2][x_index].piece != null) {
							return -1;
						}
						return 0;
					} else {
						// its not, so there is a piece there, check if its w or
						// black

						// your own player you cannot do that
						return -1;

					}

				}
				// if its trying to go for some player's peice
				else if (Math.abs(curr_x - x_index) == 1
						&& ((this.color == 0 && curr_y - y_index == 1) || (this.color == 1 && y_index
								- curr_y == 1))
						&& totalData.arrayBoard[y_index][x_index].getPiece() != null
						&& (totalData.arrayBoard[y_index][x_index].getPiece()
								.getColor() != this.color)) {
					System.out.println("Inside the pawn method");
					// you go rid of their player
					// check if there is a player there or not
					if (totalData.arrayBoard[y_index][x_index].piece != null) {
						int ret = totalData.replacePieceCheck(this,
								totalData.arrayBoard[y_index][x_index]);

						return ret;
					} else {

						return -1;
					}
				}

				else {

					return -1;
				}
			}

		}

		// with the input of the y

		return -1;

	}
	/**
	 * @param color of the piece
	 * */

	public void setColor(int color) {
		this.color = color;
	}
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	@Override
	public int move(char x, char y) {
		int ret = check(x, y);
		// check if it reached the end or not
		String s = Character.toString(y);
		int check = Integer.valueOf(s);
		//System.out.println("y is: " + s);
		//int check = (int) y;
		if (check == 8 || check == 1) {
			// activate a dead player
			rep = 0;
			return 3;
		}
		return ret;
	}
	/**
	 * @param name of the piece
	 * */
	public String getName() {
		return name;
	}
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int if its valid or not
	 * */
	@Override
	public int check(char x, char y) {
		// check for any error condition, if not any, then give a good sign and
		// return with a 0
		// if not then return -1 -> if move is not allowed
		// return 1 if there is a player of another team
		// return 0 if all normal and you can move
		// y is the row and x is colomn

		if (Character.isDigit(y) && Character.isLetter(x)
				&& Character.isLowerCase(x)) {
			int y_index = y - '0';
			y_index--;
			// check if its between a to h
			// System.out.println("inside here: x " + (int)x + y_index);
			if (x >= 97 && x <= 104 && y_index >= 0 && y_index <= 7) {
				// System.out.println("inside here");
				int x_index = x - 49 - '0';
				// all the checks are done for that, now we need to
				// go into checking if the move is valid or not
				int curr_y = this.y - '0';
				curr_y--;
				int curr_x = this.x - 49 - '0';
				// 8 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if (curr_x == x_index && ((curr_y - y_index == 1 && this.color == 0)|| (y_index - curr_y == 1 && this.color == 1) || (rep == 0 && ((curr_y- y_index == 2 && this.color == 0) || (y_index
								- curr_y == 2 && this.color == 1))))) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						if (rep == 0
								&&  Math.abs(curr_y- y_index) == 2 && totalData.arrayBoard[(y_index + curr_y) / 2][x_index].piece != null) {
							System.out
									.println("Error: Pawn cannot jump over pieces");
							return -1;
						}
						// set it up
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						rep++;
						if(Math.abs(curr_y- y_index) == 2) doub = true;
						else doub = false;
						return 0;
					} else {
						// its not, so there is a piece there, check if its w or
						// black

						// your own player you cannot do that
						System.out
								.println("Error: You cannot move where your piece already exists");
						return -1;

					}

				}
						
				// if its trying to go for some player's peice
				else if (Math.abs(curr_x - x_index) == 1 && ((this.color == 0 && curr_y - y_index == 1) || (this.color == 1 && y_index- curr_y == 1))&& totalData.arrayBoard[y_index][x_index].getPiece() != null&& (totalData.arrayBoard[y_index][x_index].getPiece().getColor() != this.color)) {
					// you go rid of their player
					// check if there is a player there or not
					if (totalData.arrayBoard[y_index][x_index].piece != null) {
						int ret = totalData.replacePiece(this,
								totalData.arrayBoard[y_index][x_index]);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						rep++; doub = false;
						return ret;
					} else {
						System.out.println("Illegal move, try again");
						return -1;
					}
				}
				
				//en passant
				//else if (Math.abs(curr_x - x_index) == 1 && Math.abs(curr_y - y_index) == 1 && totalData.arrayBoard[y_index][x_index].getPiece() == null && totalData.arrayBoard[y_index][curr_x].getPiece()!=null && totalData.arrayBoard[y_index][curr_x].getPiece().getColor() != this.color && totalData.arrayBoard[y_index][curr_x].getPiece().getRep()==1 && totalData.arrayBoard[y_index][curr_x].getPiece().isDoub()) {
				else if (Math.abs(curr_x - x_index) == 1 && Math.abs(curr_y - y_index) == 1 && totalData.arrayBoard[y_index][x_index].getPiece() == null && totalData.arrayBoard[curr_y][x_index].getPiece()!=null && totalData.arrayBoard[curr_y][x_index].getPiece().getColor() != this.color && totalData.arrayBoard[curr_y][x_index].getPiece().getRep()==1 && totalData.arrayBoard[curr_y][x_index].getPiece().isDoub()) {	
				// you go rid of their player
					// check if there is a player there or not
					//System.out.println("here");
					//if (totalData.arrayBoard[y_index][curr_x].piece != null && Controller.last_pawn && Controller.last_color!=this.color && Controller.last_x== totalData.arrayBoard[y_index][curr_x].getX() && Controller.last_y == totalData.arrayBoard[y_index][curr_x].getY()) {
					if (totalData.arrayBoard[curr_y][x_index].piece != null && Controller.last_pawn && Controller.last_color!=this.color && Controller.last_x== totalData.arrayBoard[curr_y][x_index].getX() && Controller.last_y == totalData.arrayBoard[curr_y][x_index].getY()) {
						int ret = totalData.replacePiece(null, totalData.arrayBoard[curr_y][x_index]);
								//totalData.arrayBoard[y_index][curr_x]);
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						rep++;
						return ret;
					} else {
						System.out.println("Illegal move, try again");
						return -1;
					}
				}	

				else {
					
					System.out.println("Illegal move, try again");
					return -1;
				}
			}

		}

		// with the input of the y
		System.out.println("Error: Incorrect input");
		return -1;

	}

}