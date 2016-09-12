/***
 * 
 */
package model;

/***
 * this class will the rook piece
 * @author Jasmine and Risham
 *
 */
public class Rook implements Piece {
	
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
	String type = "rook";
	/**
	 * Contructor method 
	 * @param name name of the piece
	 * @param x location of the piece
	 * @param y location of the piece
	 * @param color color of the piece
	 * */
	
	public Rook(String name, char x, char y, int color) {
		// turn the living on
		living = true;
		this.name = name;
		this.color = color;
		rep =0;
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
	 * @return int global variable
	 * */
	public int getRep() {
		return rep;
	}
	/**
	 * @return boolean for the double or not
	 * */
	public boolean isDoub() {
		return false;
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
	 * @return if its in check
	 * */
	public boolean isCheck() {
		return false;
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

		return check(x, y);
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
	 * @return for the error	 
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
				// 2 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if ((curr_x == x_index && curr_y != y_index)
						|| (curr_y == y_index && curr_x != x_index)) { // will
																		// go up
																		// and
																		// down,
																		// or
																		// sideways
					// check if there is anything in between

					if (checkPieces(x_index, y_index, curr_x, curr_y) == -1) {
						// there is a piece in the middle
						return -1;
					}

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up

						return 0;
					}
					// if its trying to go for some player's peice
					else if (totalData.arrayBoard[y_index][x_index] != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != this.color) {
						// you go rid of their player
						// check if there is a player there or not

						int ret = totalData.replacePieceCheck(this,
								totalData.arrayBoard[y_index][x_index]);

						return ret;

					} else {
						// its not, so there is a piece there, check if its w or
						// black

						// your own player you cannot do that
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
	 * @param x_ch moving to the spot
	 * @param y_ch moving to the spot
	 * @param curr_x current x
	 * @param curr_y current y
	 * @return int of the error
	 * */

	// passed the difference from both points
	public static int checkPieces(int x_ch, int y_ch, int curr_x, int curr_y) {

		if (x_ch == curr_x && (y_ch - curr_y) > 0) {

			for (int i = 1; i < (y_ch - curr_y); i++) {
				if (totalData.arrayBoard[curr_y + i][curr_x].piece != null) {
					return -1;
				}
			}
		} else if (x_ch == curr_x && (y_ch - curr_y) < 0) {

			for (int i = 1; i < Math.abs(y_ch - curr_y); i++) {
				if (totalData.arrayBoard[curr_y - i][curr_x].piece != null) {
					return -1;
				}
			}
		} else if ((x_ch - curr_x) > 0 && y_ch == curr_y) {
			for (int i = 1; i < Math.abs(x_ch - curr_x); i++) {
				if (totalData.arrayBoard[curr_y][curr_x + i].piece != null) {
					return -1;
				}
			}
		} else if ((x_ch - curr_x) < 0 && y_ch == curr_y) {
			for (int i = 1; i < Math.abs(x_ch - curr_x); i++) {
				if (totalData.arrayBoard[curr_y][curr_x - i].piece != null) {
					return -1;
				}
			}
		}
		return 0;
	}
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int for the error
	 * */
	@Override
	public int check(char x, char y) {

		// this would go staright either way

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
				// 2 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if ((curr_x == x_index && curr_y != y_index)
						|| (curr_y == y_index && curr_x != x_index)) { // will
																		// go up
																		// and
																		// down,
																		// or
																		// sideways
					// check if there is anything in between

					if (checkPieces(x_index, y_index, curr_x, curr_y) == -1) {
						// there is a piece in the middle
						System.out
								.println("Error: Rook cannot jump over pieces");
						return -1;
					}

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						rep++;
						return 0;
					}
					// if its trying to go for some player's peice
					else if (totalData.arrayBoard[y_index][x_index] != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != this.color) {
						// you go rid of their player
						// check if there is a player there or not

						int ret = totalData.replacePiece(this,
								totalData.arrayBoard[y_index][x_index]);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						rep++;
						return ret;

					} else {
						// its not, so there is a piece there, check if its w or
						// black

						// your own player you cannot do that
						System.out
								.println("Error: You cannot move where your piece already exists");
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