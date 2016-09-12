/**
 * @version 1.0
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.util.Log;

/**
 * @author Jasmine Feng and Risham Chokshi
 *
 */
public class ReplayData implements Serializable, Comparable<ReplayData>{
	private String name;
	private ArrayList<String> moves;
	private Calendar timestamp;
	
	private static final long serialVersionUID = 0L;
	
	public ReplayData(String name){
		this.name = name;
		moves = new ArrayList<String>();
		timestamp = new GregorianCalendar();
	}
	/**
	 * @return timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the moves
	 */
	public ArrayList<String> getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(ArrayList<String> moves) {
		this.moves = moves;
	}
	
	/**
	 * adds a move to the game replay
	 * @param move the string move 
	 */
	public void addMove(String move){
		moves.add(move);
	}
	
	/**
	 * remove a move from the game replay
	 * @param index the index of the move to be removed (in the case of undo, the last move)
	 */
	public void deleteMove(int index){
		moves.remove(index);
	}
	
	/**
	 * removes the last move from the game replay
	 */
	public void undoMove(){
		moves.remove(moves.size()-1);
	}
	
	public String getDate(){
		String date = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		dateFormat.setCalendar(timestamp);
		date = dateFormat.format(timestamp.getTime());
		Log.e("*** replayData: timestamp", date);
		
		return date;
	}

	@Override
	public int compareTo(ReplayData another) {
		// TODO Auto-generated method stub
		String name1 = this.getName().toLowerCase();
		String name2 = another.getName().toLowerCase();
		return name1.compareTo(name2); 
	}
}
