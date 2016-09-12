package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/***
 * this is the total data for the all the array and the board
 * @author Jasmine and Risham
 *
 */
public class totalData {
	
	
	/**
	 * these are just the data which would be kept track of throughout the game
	 * */
	public static Place [][] arrayBoard = new Place[8][8];
	public static Place king_black;
	public static Place king_white;
	public static boolean draw = false;
	public static Player player1 = new Player("White",1);//white
	public static Player player2 = new Player("Black",0);//black
	public static ArrayList<ReplayData> replays= new ArrayList<ReplayData>();
	
	/**
	 * @param king
	 * @param player
	 * */
	public static void setPlayerKing(Piece king, Player player){
		player.setKing(king);
	}
	/**
	 * @return player
	 * */
	public static Player getPlayer1() {
		return player1;
	}
	/**
	 * 
	 * @param player1
	 * */
	
	public static void setPlayer1(Player player1) {
		totalData.player1 = player1;
	}
	/**
	 * @return player
	 * */
	public static Player getPlayer2() {
		return player2;
	}
	/**
	 * 
	 * @param player2
	 * */
	public static void setPlayer2(Player player2) {
		totalData.player2 = player2;
	}
	/**
	 * @return place 2d array
	 * */
	public static Place[][] getArrayBoard() {
		return arrayBoard;
	}
	/**
	 * @param arrayBoard place 2d array
	 * */
	public static void setArrayBoard(Place[][] arrayBoard) {
		totalData.arrayBoard = arrayBoard;
	}

	/**
	 * @return place of the king
	 * 
	 * */
	public static Place getKing_black() {
		return king_black;
	}
	/**
	 * @param  king_black place of the king
	 * 
	 * */
	public static void setKing_black(Place king_black) {
		totalData.king_black = king_black;
	}
	/**
	 * @return place of the king
	 * 
	 * */
	public static Place getKing_white() {
		return king_white;
	}
	/**
	 * @param king_white of the king
	 * 
	 * */
	public static void setKing_white(Place king_white) {
		totalData.king_white = king_white;
	}
	
	/**
	 * @param piece
	 * @param start_x
	 * @param start_y
	 * @param end_x
	 * @param end_y
	 * @return int for error
	 * */
	
	public static int pieceCheckMove(Piece piece, char start_x, char start_y, char end_x, char end_y){
		
		if(piece == null)
			return -1;
		else if(piece instanceof Bishop)
			return bishopCheckMove((Bishop)piece, start_x, start_y, end_x, end_y);
		else if(piece instanceof King)
			return kingCheckMove((King) piece,start_x, start_y, end_x, end_y);
		else if(piece instanceof Knight)
			return knightCheckMove((Knight) piece, start_x, start_y, end_x, end_y);
		else if(piece instanceof Pawn)
			return pawnCheckMove((Pawn) piece, start_x, start_y, end_x, end_y);
		else if(piece instanceof Queen)
			return queenCheckMove((Queen) piece,start_x, start_y, end_x, end_y);
		else if(piece instanceof Rook)
			return rookCheckMove((Rook)piece,start_x, start_y, end_x, end_y);
		return -1; // error
		
		
		
	}
	
	
	private static int rookCheckMove(Rook piece, char start_x, char start_y, char x, char y) {
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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';
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
									.getPiece().getColor() != piece.color) {
						// you go rid of their player
						// check if there is a player there or not

						int ret = totalData.replacePieceCheck(piece,
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
	
	
	
	
	
	
	
	private static int queenCheckMove(Queen piece, char start_x, char start_y, char x, char y) {
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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';

				// check if its diagonal
				int ret = piece.bishopCheckMove(x_index, y_index, curr_x, curr_y, x, y);
				if (ret == -1) { // it not

					// check if it is rook
					ret = piece.rookCheckMove(x_index, y_index, curr_x, curr_y, x, y);
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
	
	
	
	
	
	private static int pawnCheckMove(Pawn piece, char start_x, char start_y, char x, char y) {

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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';
				// 8 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if (curr_x == x_index
						&& ((curr_y - y_index == 1 && piece.color == 0)
								|| (y_index - curr_y == 1 && piece.color == 1) || (piece.getRep() == 0 && ((curr_y
								- y_index == 2 && piece.color == 0) || (y_index
								- curr_y == 2 && piece.color == 1))))) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						if (piece.getRep() == 0
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
						&& ((piece.color == 0 && curr_y - y_index == 1) || (piece.color == 1 && y_index
								- curr_y == 1))
						&& totalData.arrayBoard[y_index][x_index].getPiece() != null
						&& (totalData.arrayBoard[y_index][x_index].getPiece()
								.getColor() != piece.color)) {
					// you go rid of their player
					// check if there is a player there or not
					if (totalData.arrayBoard[y_index][x_index].piece != null) {
						int ret = totalData.replacePieceCheck(piece,
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
	
	private static int knightCheckMove(Knight piece, char start_x, char start_y, char x, char y) {
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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';
				// 8 posibitity
				// System.out.println("Cur x: " + curr_x + "Cur y: " + curr_y +
				// "Passed x: " + x_index + "Passed y: " + y_index);
				if ((Math.abs(curr_x - x_index) == 1 && Math.abs(curr_y
						- y_index) == 2)
						|| (Math.abs(y_index - curr_y) == 1 && Math.abs(curr_x
								- x_index) == 2)) {

					// its one up
					if (totalData.arrayBoard[y_index][x_index].getPiece() == null) {

						// set it up

						return 0;
					}

					else if (totalData.arrayBoard[y_index][x_index].getPiece() != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != piece.color) {
						// get rid of their player
						int ret = totalData.replacePieceCheck(piece,
								totalData.arrayBoard[y_index][x_index]);
						return ret;
					}

					else {
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
	
	
	
	private static int kingCheckMove(King piece,char start_x, char start_y,char x, char y) {

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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';
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
								.getColor() != piece.color) {
							// you go rid of their player
							int ret = totalData.replacePieceCheck(piece,
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
	
	
	
	
	private static int bishopCheckMove(Bishop piece, char start_x, char start_y, char x, char y) {
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
				int curr_y = start_y - '0';
				curr_y--;
				int curr_x = start_x - 49 - '0';
				int check_x = Math.abs(curr_x - x_index);

				// check if its dialgonal

				if (Math.abs(y_index - curr_y) == check_x) {

					// check if everything in the middle has no piece
					int error = Bishop.checkPieces(x_index - curr_x, y_index - curr_y,
							curr_x, curr_y);

					if (error != -1 && totalData.arrayBoard[y_index][x_index].getPiece() == null) {
						// set it up
						

						return 0;
					} else if (error != -1
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece() != null
							&& totalData.arrayBoard[y_index][x_index]
									.getPiece().getColor() != piece.color) {
						// has a piece there, get rid of it
						int ret = totalData.replacePieceCheck(piece,
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
	 * @param current piece
	 * @param replace piece
	 * @return int of the error
	 * */
	public static int replacePiece(Piece current, Place replace){
		//return 2 if its other person's king
		//return 1 if its ok.
		//set current piece into the replace location. if location is filled with piece, it needs to be in the dead section
		if(replace!=null){
			if(replace.getPiece()!=null && current!=null&& (replace.getPiece() instanceof King)){
								
				Piece temp = replace.getPiece();
				replace.setPiece(current);
				if(temp.getColor()==0){
					//player 2 , black
					player2.getDead().add(temp);
					player2.getLiving().remove(temp);
				}
				else{
					player1.getDead().add(temp);
					player2.getLiving().remove(temp);
				}
				return 2;
			}
			else if(replace.getPiece()!=null){
				Piece temp = replace.getPiece();
				replace.setPiece(current);
				if(temp.getColor()==0){
					//player 2 , black
					player2.getDead().add(temp);
					player2.getLiving().remove(temp);
				}
				else{
					player1.getDead().add(temp);
					player1.getLiving().remove(temp);
				}
				return 1;
			}
		}
		System.out.println("Error: Cannot replace peices");
		return -1;
	}
	/**
	 * @param current piece
	 * @param replace piece
	 * @return int of the error
	 * */
	public static int replacePieceCheck(Piece current, Place replace){
		//return 2 if its other person's king
		//return 1 if its ok.
		if(replace!=null){
			if(replace.getPiece()!=null && (replace.getPiece() instanceof King)){
				//then the other person won
				return 2;
			}
			else if(replace.getPiece()!=null){
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * you print the board
	 * */
	public static void printboard(){
		System.out.println("");
		if(arrayBoard!=null){
			for(int i =arrayBoard.length-1; i >=0; i--){
				//this will be the colomn
				for(int j = 0; j<arrayBoard[i].length;j++){
					if(arrayBoard[i][j]!=null)
						System.out.print(arrayBoard[i][j]);
					else
						System.out.print("   ");
				}
				System.out.println(i+1);
			}
			
				
			System.out.println(" a" + " " + " b" + " " + " c" + " " + " d" + " " + " e" + " " + " f" + " " + " g" + " " + " h");
				
			
			
		}
		System.out.println("");
	}
	
	private static void printXY(){
		if(arrayBoard!=null){
			for(int i =arrayBoard.length-1; i >=0; i--){
				//this will be the colomn
				for(int j = 0; j<arrayBoard[i].length;j++){
					if(arrayBoard[i][j]!=null)
						System.out.print(arrayBoard[i][j].getX()+""+arrayBoard[i][j].getY()+ " ");
					else
						System.out.print("   ");
				}
				System.out.println(i+1);
			}
			
				
			System.out.println(" a" + " " + " b" + " " + " c" + " " + " d" + " " + " e" + " " + " f" + " " + " g" + " " + " h");
				
			
			
		}
	}
	
	/**
	 * sorts the replay games by date
	 */
	public static void replayDateSort(){
		CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
		//now sort it
		Collections.sort(replays, c);
	}
	
	/**
	 * sorts the replay games by name
	 */
	public static void replayNameSort(){
		Collections.sort(replays);
	}
	//serialization stuff: http://www.dreamincode.net/forums/topic/248522-serialization-in-android/
	/**
	 * saves the games arraylist to a file
	 * this is now in mainactivity
	 * @throws IOException 
	 */
	public static void updateGames() throws IOException{
		/*
		Log.e("*** totaldata: serialize", "updating games");
		ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("saved_game", Context.MODE_PRIVATE));
		oos.writeObject(replays);
		oos.close();
		*/
		
		//String path = System.getProperty("user.dir");
		//path = path.substring(0, path.length());
		//path += "\\data\\replay.ser";
		/*String path = "replay.ser";
		File myFile = new File("/sdcard/" + path);
		myFile.createNewFile();
		Log.e("*** totaldata: serialize", "path of ser file: " + path);
		try{
			//FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE); ??????
			FileOutputStream fileOut = new FileOutputStream(myFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(replays);
			out.flush();
			out.close();
			fileOut.close();
			
		}catch(IOException i){
			i.printStackTrace();
		    Log.e("*** totaldata: serialize", "error");
		}
		*/
	}
	
	/**
	 * reads the games replay info from the file into the arraylist 
	 * @throws IOException
	 */
	public static void readGames() throws IOException{
		Log.e("*** readgames", "reading saved games");
		
		//FileInputStream fis = openFileInput("saved_game");
		
		/*
		ObjectInputStream ois;
		String path = "android.resource://com.example.chess/saved_games";
		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			ArrayList<ReplayData> data = (ArrayList<ReplayData>)ois.readObject();
			// TODO is savedGames null here if the file is blank? need to test
			if (data != null)	
				replays = data;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("***readgames", "read game, error");
		}
		*/
		//String path = System.getProperty("user.dir");
		//System.out.println(path);
		//path = path.substring(0, path.length());
		//path += "\\data\\replay.ser";
		/*
		Log.e("*** readgames", "reading saved games");
		String path = "replay.ser";
		//System.out.println(path);
		File file = new File("/sdcard/replay.ser");
		BufferedReader br = new BufferedReader(new FileReader(file));     
		if (br.readLine() == null) {
		    //System.out.println("empty file, no data to be read in");
		    Log.e("**** serialization", "empty file, no data to be read in");
		    br.close();
		    return;
		}
		
		try{
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			replays = (ArrayList<ReplayData>) in.readObject();
			in.close();
			fileIn.close();
			return;
		}catch(IOException exception){
			exception.printStackTrace();
			//System.out.println("file does not exist or is blank");
		    Log.e("**** serialization", "file does not exist or is blank");
			return;
		}catch(ClassNotFoundException exception){
		    Log.e("**** serialization", "class does not exist");
			return;
		}
		*/
	}
}