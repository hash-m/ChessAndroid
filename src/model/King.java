/***
 * 
 */
package model;

/***
 *	This class will be the piece for king
 * @author Jasmine and Risham
 *
 */
public class King implements Piece {
	/**
	 * x and y coordinates
	 * */
	
	char x;
	char y;

	boolean living;
	/***
	 * black = 0, white =1
	 */
	int color;
	/**
	 * type name
	 * */
	String type = "king";
	/**
	 * to see if this king is under check or not
	 * */
	boolean isCheck;
	/**
	 * name of the king
	 * */
	String name;
	/**
	 * if it is moved or not
	 * */
	
	int rep = 0;
	boolean doub = false;
	
	/**
	 * Contructor method 
	 * @param name name of the king
	 * @param x location of the king
	 * @param y location of the king
	 * @param color color of the king
	 * */
	public King(String name, char x, char y, int color) {
		this.name = name;
		// turn the living on
		living = true;
		isCheck = false;
		this.color = color;
		rep =0;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return string of  type
	 * */
	public String getType(){
		return type;
	}
	/**
	 * @param living to set the living
	 * */
	public void setLiving(boolean living) {
		this.living = living;
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
	 * @return char which is the x location
	 * */
	public char getX() {
		return x;
	}

	/**
	 * @return if its in check
	 * */
	public boolean isCheck() {
		return isCheck;
	}
	/**
	 * @param name of the king
	 * */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * @param x to set x
	 * */
	public void setX(char x) {
		this.x = x;
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
	 * @param x coordinate
	 * @param y coordinate
	 * @return int if its valid or not
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
				if (Math.abs(curr_x - x_index) <= 1
						&& Math.abs(curr_y - y_index) <= 1) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up
						return 0;
					} else {
						// its not, so there is a piece there, check if its w or
						// black
						if (totalData.arrayBoard[y_index][x_index].getPiece()
								.getColor() != this.color) {
							// you go rid of their player
							int ret = totalData.replacePieceCheck(this,
									totalData.arrayBoard[y_index][x_index]);
							return ret;
						} else {
							// your own player you cannot do that

							return -1;
						}
					}

				} else {

					return -1;
				}
			}

		}

		// with the input of the y

		return -1;
	}
	
	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return for the error	 * 
	 * */
	
	@Override
	public int move(char x, char y) {
		// now given a move you have to see if you can move or not
		int ch = check(x, y);
		if (ch == 2) {
			// king is gone,
			return 2;
		}
		return ch;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return int for the error
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
			int curr_y = this.y - '0';
			curr_y--;
			int curr_x = this.x - 49 - '0';
			int x_index = x - 49 - '0';
			// check if its between a to h
			// System.out.println("inside here: x " + (int)x + y_index);
			if (x >= 97 && x <= 104 && y_index >= 0 && y_index <= 7) {
				// System.out.println("inside here");
				
				// all the checks are done for that, now we need to
				// go into checking if the move is valid or not
				
				// 8 posibitity
				if (Math.abs(curr_x - x_index) <= 1
						&& Math.abs(curr_y - y_index) <= 1) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						if(this.color==1){
							totalData.getPlayer1().king.setX(x);
							totalData.getPlayer1().king.setY(y);
						}
						else{
							totalData.getPlayer2().king.setX(x);
							totalData.getPlayer2().king.setY(y);
						}
						this.y = y;
						rep++;
						this.x = x;
						return 0;
					} else {
						// its not, so there is a piece there, check if its w or
						// black
						if (totalData.arrayBoard[y_index][x_index].getPiece()
								.getColor() != this.color) {
							// you go rid of their player
							int ret = totalData.replacePiece(this,
									totalData.arrayBoard[y_index][x_index]);
							totalData.arrayBoard[curr_y][curr_x].setPiece(null);
							this.y = y;
							this.x = x;
							if(this.color==1){
								totalData.getPlayer1().king.setX(x);
								totalData.getPlayer1().king.setY(y);
							}
							else{
								totalData.getPlayer2().king.setX(x);
								totalData.getPlayer2().king.setY(y);
							}
							rep++;
							return ret;
						} else {
							// your own player you cannot do that
							System.out
									.println("Error: You cannot move where your piece already exists");
							return -1;
						}
					}

				} 
				//check for castling
				else if(rep==0 && y_index == curr_y && Math.abs(x_index-curr_x)==2){
					if(x_index<curr_x && totalData.arrayBoard[y_index][0].getPiece()!=null && totalData.arrayBoard[y_index][0].getPiece().getRep()==0){
						//at this point, and king and rook are both good
							//check if there is anything in the middle
							for(int i=1; i<4; i++){
								if(totalData.arrayBoard[y_index][i].getPiece()!=null){
									System.out.println("Error: Castling is not possible");
									return -1;
								}
							}
							for(int i =0; i<3; i++){ //do from middle to 2
								int re = castlingCheck((char)((int)this.x - i),this.y);
								if(re==-1){
									//not possible
									System.out.println("Error: Castling is not possible");
									return -1;
								}
							}
							
						//move rook next to king(right hand side, 2->place)
							totalData.arrayBoard[y_index][x_index].setPiece(this);
							totalData.arrayBoard[curr_y][curr_x].setPiece(null);
							
							totalData.arrayBoard[y_index][x_index+1].setPiece(totalData.arrayBoard[y_index][0].getPiece());
							totalData.arrayBoard[y_index][0].setPiece(null);
							
							this.y = y;
							totalData.arrayBoard[y_index][x_index+1].getPiece().setY(y);
							rep++;
							this.x = x;
							if(this.color==1){
								totalData.getPlayer1().king.setX(x);
								totalData.getPlayer1().king.setY(y);
							}
							else{
								totalData.getPlayer2().king.setX(x);
								totalData.getPlayer2().king.setY(y);
							}
							totalData.arrayBoard[y_index][x_index+1].getPiece().setX((char)((int)(this.x+1)));
							return 0;
					}
					else if(x_index>curr_x && totalData.arrayBoard[y_index][7].getPiece()!=null && totalData.arrayBoard[y_index][7].getPiece().getRep()==0){
						//at this point, and king and rook are both good
						//check if the king and rook and everything in the middle are in attack for king.
						//check from middle to 6
						
						for(int i=5; i<7; i++){
							if(totalData.arrayBoard[y_index][i].getPiece()!=null){
								System.out.println("Error: Castling is not possible");
								return -1;
							}
						}
						for(int i =0; i<3; i++){ //do from middle to 6
							int re = castlingCheck((char)((int)this.x + i),this.y);
							if(re==-1){
								//not possible
								System.out.println("Error: Castling is not possible");
								return -1;
							}
						}
						//move it to next of king, (left hand side, 6->place)
						
						totalData.arrayBoard[y_index][x_index].setPiece(this);
						totalData.arrayBoard[curr_y][curr_x].setPiece(null);
						
						totalData.arrayBoard[y_index][x_index-1].setPiece(totalData.arrayBoard[y_index][7].getPiece());
						totalData.arrayBoard[y_index][7].setPiece(null);
						
						this.y = y;
						totalData.arrayBoard[y_index][x_index-1].getPiece().setY(y);
						rep++;
						this.x = x;
						if(this.color==1){
							totalData.getPlayer1().king.setX(x);
							totalData.getPlayer1().king.setY(y);
						}
						else{
							totalData.getPlayer2().king.setX(x);
							totalData.getPlayer2().king.setY(y);
						}
						totalData.arrayBoard[y_index][x_index-1].getPiece().setX((char)((int)(this.x-1)));
						return 0;
						
					}
					else{
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
	
	
	private int castlingCheck(char x, char y){
		
		//for other player. 
		if(this.color==0){
			//black player, so player2
			for(int i=0; i<totalData.getPlayer1().getLiving().size(); i++){
				//check if the piece can move to the king spot
				if(totalData.getPlayer1().getLiving().get(i).checkMove(x, y) !=-1 ){
					return -1; // there is an error and it is possible to be attacked
					
				}
			
			}
		}
		else{
			for(int i=0; i<totalData.getPlayer2().getLiving().size(); i++){
				//check if the piece can move to the king spot
				if(totalData.getPlayer2().getLiving().get(i).checkMove(x, y) !=-1 ){
					return -1; // there is an error and it is possible to be attacked
					
				}
			
			}
		}
				
	
	return 0; //if its all good.
	}
}