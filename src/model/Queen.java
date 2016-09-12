/***
 * 
 */
package model;

/***
 *	this is the class for queen instance
 * @author Jasmine and Risham
 *
 */
public class Queen implements Piece {
	/**
	 * x and y coordinates
	 * */
	char x;
	char y;
	/**
	 * if it is moved or not
	 * */
	int rep = 0;// number of times it moved
	
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
	String type = "queen";
	/**
	 * Contructor method 
	 * @param name name of the piece
	 * @param x location of the piece
	 * @param y location of the piece
	 * @param color color of the piece
	 * */
	public Queen(String name, char x, char y, int color) {
		// turn the living on
		living = true;
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
	 * @return int global variable
	 * */
	public int getRep() {
		return -1;
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
	 * @return boolean for the double or not
	 * */
	public boolean isDoub() {
		return false;
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

				// check if its diagonal
				int ret = bishopCheckMove(x_index, y_index, curr_x, curr_y, x, y);
				if (ret == -1) { // it not

					// check if it is rook
					ret = rookCheckMove(x_index, y_index, curr_x, curr_y, x, y);
					if (ret != -1) { // it is
						return ret;
					} else { // nothing
						return -1;
					}

				} else
					// it is bishop move
					return ret;

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
	 * @return int if its valid or not
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
	 * @param x_index
	 * @param y_index
	 * @param curr_x
	 * @param curr_y
	 * @return int
	 * */
	public int bishopCheck(int x_index, int y_index, int curr_x, int curr_y,
			char x, char y) {
		int check_x = Math.abs(curr_x - x_index);
		if (Math.abs(y_index - curr_y) == check_x) {

			// check if everything in the middle has no piece
			int error = Bishop.checkPieces(x_index - curr_x, y_index - curr_y,
					curr_x, curr_y);

			if (error != -1
					&& totalData.arrayBoard[y_index][x_index].getPiece() == null) {
				// set it up
				totalData.arrayBoard[y_index][x_index].setPiece(this);
				totalData.arrayBoard[curr_y][curr_x].setPiece(null);
				this.y = y;
				this.x = x;

				return 0;
			} else if (error != -1
					&& totalData.arrayBoard[y_index][x_index].getPiece() != null
					&& totalData.arrayBoard[y_index][x_index].getPiece()
							.getColor() != this.color) {
				// has a piece there, get rid of it
				int ret = totalData.replacePiece(this,
						totalData.arrayBoard[y_index][x_index]);
				totalData.arrayBoard[curr_y][curr_x].setPiece(null);
				this.y = y;
				this.x = x;
				return ret;
			} else {
				// its not, so there is a piece there, check if its w or black
				// your own player you cannot do that
				return -1;

			}

		}

		else {
			return -1;
		}

	}
	/**
	 * @param x_index
	 * @param y_index
	 * @param curr_x
	 * @param curr_y
	 * @return int
	 * */
	
	public int rookCheckMove(int x_index, int y_index, int curr_x, int curr_y,char x, char y) {
		
				if ((curr_x == x_index && curr_y != y_index)
						|| (curr_y == y_index && curr_x != x_index)) { // will
																		// go up
																		// and
																		// down,
																		// or
																		// sideways
					// check if there is anything in between

					if (Rook.checkPieces(x_index, y_index, curr_x, curr_y) == -1) {
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

				

		// with the input of the y
		return -1;

	}
	
	
	/**
	 * @param x_index
	 * @param y_index
	 * @param curr_x
	 * @param curr_y
	 * @param x
	 * @param y
	 * @return int
	 * */
	
	public int bishopCheckMove(int x_index, int y_index, int curr_x, int curr_y,char x, char y) {
		int check_x = Math.abs(curr_x - x_index);
		if (Math.abs(y_index - curr_y) == check_x) {

			// check if everything in the middle has no piece
			int error = Bishop.checkPieces(x_index - curr_x, y_index - curr_y,
					curr_x, curr_y);

			if (error != -1
					&& totalData.arrayBoard[y_index][x_index].getPiece() == null) {
				// set it up
				
				return 0;
			} else if (error != -1
					&& totalData.arrayBoard[y_index][x_index].getPiece() != null
					&& totalData.arrayBoard[y_index][x_index].getPiece()
							.getColor() != this.color) {
				// has a piece there, get rid of it
				int ret = totalData.replacePieceCheck(this,totalData.arrayBoard[y_index][x_index]);
				
				
				return ret;
			} else {
				// its not, so there is a piece there, check if its w or black
				// your own player you cannot do that
				return -1;

			}

		}

		else {
			return -1;
		}

	}

	
	
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	public int rookCheck(int x_index, int y_index, int curr_x, int curr_y,char x, char y) {

		if ((curr_x == x_index && curr_y != y_index)
				|| (curr_y == y_index && curr_x != x_index)) { // will go up and
																// down, or
																// sideways
			// check if there is anything in between

			if (Rook.checkPieces(x_index, y_index, curr_x, curr_y) == -1) {
				// there is a piece in the middle
				//System.out.println("Error: Queen cannot jump over pieces");
				return -1;
			}

			// its one up
			if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
				// set it up
				totalData.arrayBoard[y_index][x_index].setPiece(this);
				totalData.arrayBoard[curr_y][curr_x].setPiece(null);
				this.y = y;
				this.x = x;
				return 0;
			}
			// if its trying to go for some player's peice
			else if (totalData.arrayBoard[y_index][x_index] != null
					&& totalData.arrayBoard[y_index][x_index].getPiece()
							.getColor() != this.color) {
				// you go rid of their player
				// check if there is a player there or not

				int ret = totalData.replacePiece(this,
						totalData.arrayBoard[y_index][x_index]);
				totalData.arrayBoard[curr_y][curr_x].setPiece(null);
				this.y = y;
				this.x = x;
				return ret;

			} else {
				// its not, so there is a piece there, check if its w or black

				// your own player you cannot do that
				return -1;

			}

		}

		else {

			return -1;
		}
	}
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	@Override
	public int check(char x, char y) {
		// this is bishop (diagonal) or rook

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

				// check if its diagonal
				int ret = bishopCheck(x_index, y_index, curr_x, curr_y, x, y);
				if (ret == -1) { // it not

					// check if it is rook
					ret = rookCheck(x_index, y_index, curr_x, curr_y, x, y);
					if (ret != -1) { // it is
						return ret;
					} else { // nothing
						System.out.println("Illegal move, try again");
						return -1;
					}

				} else
					// it is bishop move
					return ret;

			}
		}

		// with the input of the y
		System.out.println("Error: Incorrect input");
		return -1;

	}

}