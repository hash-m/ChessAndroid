/****
 * 
 */
package model;

/****
 * this instance is for bishop
 * @author Jasmine and Risham
 *
 */
public class Bishop implements Piece {

	/****
	 * x and y coordinates
	 * */
	char x;
	char y;
	/***
	 * if it is moved or not
	 * */
	int rep = 0;// number of times it moved
	
	/****
	 * if its doubled
	 * */
	boolean doub = false;
	/****
	 * living or not
	 * */
	boolean living;
	
	
	/****
	 * black = 0, white =1
	 */
	int color;
	
	/****
	 * name of the piece
	 * */
	String name;
	/****
	 * type
	 * */
	String type = "bishop";
	/****
	 * Contructor method 
	 * @param name name of the piece
	 * @param x location of the piece
	 * @param y location of the piece
	 * @param color color of the piece
	 * */

	public Bishop(String name, char x, char y, int color) {
		// turn the living on
		living = true;
		this.name = name;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	/****
	 * @return string of type
	 * */
	public String getType(){
		return type;
	}
	/****
	 * @return char which is the x location
	 * */
	
	public char getX() {
		return x;
	}
	/****
	 * @param x to set x
	 * */
	public void setX(char x) {
		this.x = x;
	}
	/****
	 * @return int global variable
	 * */
	public int getRep() {
		return -1;
	}
	/****
	 * @return boolean for the double or not
	 * */
	public boolean isDoub() {
		return false;
	}
	/****
	 * @return y which is a char
	 * */
	public char getY() {
		return y;
	}
	/****
	 * @param y which is to set the value
	 * */
	public void setY(char y) {
		this.y = y;
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
	
	/****
	 * @param name of the piece
	 * */
	public String getName() {
		return name;
	}
	/****
	 * @return living if the piece is still living
	 * */
	public boolean isLiving() {
		return living;
	}
	/****
	 * @param living to set the living
	 * */
	public void setLiving(boolean living) {
		this.living = living;
	}
	/****
	 * @return int color of the piece
	 * */
	public int getColor() {
		return color;
	}
	/****
	 * @param name to set name
	 * */
	public void setname(String name) {
		this.name = name;
	}
	/****
	 * @return if its in check
	 * */
	public boolean isCheck() {
		return false;
	}
	/****
	 * @param color of the piece
	 * */
	public void setColor(int color) {
		this.color = color;
	}
	
	/** @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	public int checkMove(char x, char y) {
		// goes diagonal
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
				int check_x = Math.abs(curr_x - x_index);

				// check if its dialgonal

				if (Math.abs(y_index - curr_y) == check_x) {

					// check if everything in the middle has no piece
					int error = checkPieces(x_index - curr_x, y_index - curr_y,
							curr_x, curr_y);

					if (error != -1 && totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up
						

						return 0;
					} else if (error != -1
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece() != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != this.color) {
						// has a piece there, get rid of it
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
	/***
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	@Override
	public int move(char x, char y){
		
		return check(x, y);
	}
	/***
	 * @param x_ch
	 * @param y_ch
	 * @param curr_x
	 * @param curr_y
	 * @return int of the error
	 * 
	 * */
	// passed the difference from both points
	public static int checkPieces(int x_ch, int y_ch, int curr_x, int curr_y) {

		if (x_ch < 0 && y_ch > 0) {

			for (int i = 1; i < y_ch; i++) {
				if(curr_y+i>7||curr_x-i<0)
					return -1;
				if (totalData.arrayBoard[curr_y + i][curr_x - i].getPiece() != null) {
					return -1;
				}
			}
		} else if (x_ch < 0 && y_ch < 0) {

			for (int i = 1; i < Math.abs(y_ch); i++) {
				if(curr_y-i<0||curr_x-i<0)
					return -1;
				if (totalData.arrayBoard[curr_y - i][curr_x - i].getPiece() != null) {
					return -1;
				}
			}
		} else if (x_ch > 0 && y_ch > 0) {
			for (int i = 1; i < y_ch; i++) {
				if(curr_y+i>7||curr_x+i>7)
					return -1;
				if (totalData.arrayBoard[curr_y + i][curr_x + i].getPiece() != null) {
					return -1;
				}
			}
		} else if (x_ch > 0 && y_ch < 0) {
			for (int i = 1; i < x_ch; i++) {
				if(curr_y-i<0||curr_x+i>7)
					return -1;
				if (totalData.arrayBoard[curr_y - i][curr_x + i].getPiece() != null) {
					return -1;
				}
			}
		}
		return 0;
	}
	/***
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 
	 * */
	@Override
	public int check(char x, char y) {

		// goes diagonal
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
				int check_x = Math.abs(curr_x - x_index);

				// check if its dialgonal

				if (Math.abs(y_index - curr_y) == check_x) {

					// check if everything in the middle has no piece
					int error = checkPieces(x_index - curr_x, y_index - curr_y,
							curr_x, curr_y);

					if (error != -1
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece() == null) {
						// set it up
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;

						return 0;
					} else if (error != -1
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece() != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != this.color) {
						// has a piece there, get rid of it
						int ret = totalData.replacePiece(this,
								totalData.arrayBoard[y_index][x_index]);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						this.y = y;
						this.x = x;
						return ret;
					} else {
						// its not, so there is a piece there, check if its w or
						// black
						
						// your own player you cannot do that
						if(totalData.arrayBoard[y_index][x_index]!=null && totalData.arrayBoard[y_index][x_index].getPiece().getColor()==this.color)
							System.out.println("Error: you cannot move where your piece already exists");
						else
							System.out.println("Error: Bishop cannot jump over pieces");
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