package com.example.chess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import control.Controller;
import model.Bishop;
import model.King;
import model.Knight;
import model.Pawn;
import model.Piece;
import model.Place;
import model.Player;
import model.Queen;
import model.ReplayData;
import model.Rook;
import model.totalData;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends Activity {

	// declare things
		int i = 0;
		int replayIndex = 0;
		int round = 1;
		final String movePromotion = "";
		ImageButton a1;
		ImageButton a2;
		ImageButton a3;
		ImageButton a4;
		ImageButton a5;
		ImageButton a6;
		ImageButton a7;
		ImageButton a8;
		ImageButton b1;
		ImageButton b2;
		ImageButton b3;
		ImageButton b4;
		ImageButton b5;
		ImageButton b6;
		ImageButton b7;
		ImageButton b8;
		ImageButton c1;
		ImageButton c2;
		ImageButton c3;
		ImageButton c4;
		ImageButton c5;
		ImageButton c6;
		ImageButton c7;
		ImageButton c8;
		ImageButton d1;
		ImageButton d2;
		ImageButton d3;
		ImageButton d4;
		ImageButton d5;
		ImageButton d6;
		ImageButton d7;
		ImageButton d8;
		ImageButton e1;
		ImageButton e2;
		ImageButton e3;
		ImageButton e4;
		ImageButton e5;
		ImageButton e6;
		ImageButton e7;
		ImageButton e8;
		ImageButton f1;
		ImageButton f2;
		ImageButton f3;
		ImageButton f4;
		ImageButton f5;
		ImageButton f6;
		ImageButton f7;
		ImageButton f8;
		ImageButton g1;
		ImageButton g2;
		ImageButton g3;
		ImageButton g4;
		ImageButton g5;
		ImageButton g6;
		ImageButton g7;
		ImageButton g8;
		ImageButton h1;
		ImageButton h2;
		ImageButton h3;
		ImageButton h4;
		ImageButton h5;
		ImageButton h6;
		ImageButton h7;
		ImageButton h8;
		Button Resign;
		Button Draw;
		Button Replay;
		Button AI;
		Button Undo;
		Button Next;
		TextView textview;
		Place[][] arrayBoard;
		Player tempPlayer1 = new Player("temp1", -1);
		Player tempPlayer2 = new Player("temp2", -1);
		int replayNumber =totalData.replays.size() +1;
		ReplayData replayData = new ReplayData("Game " + replayNumber);
		// now controller
		Controller controller = new Controller();
		boolean illegal = true;
		// players
		int player = 1; // -1; no one goes, 1: white, 2:black
		int prev_player = 0;
		boolean click = false; // nothing clicked, goes to true if it was clicked
								// once before
		String lastMove = ""; // stores the last move
		ImageButton prevBut;
		ImageButton nextBut;
		boolean drawclick = false;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.activity_game);

			Log.e("GAME ACTIVITY", "*************************************************************");
			textview = (TextView) findViewById(R.id.textView1);
			arrayBoard = new Place[8][8];
			// textview.setText("why");
			// setup stuff
			setup();
			initialize();
			getpics();
			settemp(arrayBoard, totalData.arrayBoard);
			initialize_Player(totalData.getPlayer1(), tempPlayer1);
			initialize_Player(totalData.getPlayer2(), tempPlayer2);
			Resign = (Button) findViewById(R.id.Resign);
			Draw = (Button) findViewById(R.id.draw);
			Replay = (Button) findViewById(R.id.replay);
			AI = (Button) findViewById(R.id.AI);
			Undo = (Button) findViewById(R.id.Undo);
			Undo.setEnabled(false);
			Next = (Button) findViewById(R.id.next);
			Next.setEnabled(false);
			
			//check if replay mode is on
			if(ReplaysActivity.replay){
				//this is a replay, turn off board functionality
				disableBoard();
				Next.setEnabled(true);
			}
			
			Next.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					if(replayIndex == totalData.replays.get(ReplaysActivity.selectedIndex).getMoves().size()){
						ReplaysActivity.replay = false;
						replayEnd();
					}
					else{
					click = true; lastMove = ""; //resetting the values
					String nextMove = totalData.replays.get(ReplaysActivity.selectedIndex).getMoves().get(replayIndex);
					nextMove = nextMove.trim();
					if(nextMove.equalsIgnoreCase("resign"))
						resign();
					else if(nextMove.equalsIgnoreCase("draw"))
						draw();
					else if(nextMove.equals("undo"))
						undo();
					else
						run(nextMove, null);
					if(illegal==true){
						textview.setText("illegal: "+nextMove);
					}
					replayIndex++;
					}
					
					
				}
			});
			
			Resign.setOnClickListener(new View.OnClickListener() {
				// TODO serialize here
				@Override
				public void onClick(View v) {
					Log.e("**** resign", "resign clicked: " + i);
					resign();
					i = 9;
					Log.e("**** resign", "after mainactivity refreshed: " + i);
				}
			});
			Draw.setOnClickListener(new View.OnClickListener() {
				// TODO serialize here???
				@Override
				public void onClick(View v) {
					draw();
				}
			});
			Replay.setOnClickListener(new View.OnClickListener() {
				// TODO serialize here???
				@Override
				public void onClick(View v) {
					// TODO: replay
					
					Intent i = new Intent(GameActivity.this,
							ReplaysActivity.class); // resets objects too, (from, to)
								// with it, cant come back to it
					startActivity(i);// starting the activity
				}
			});
			AI.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					al();
				}
			});
			Undo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					undo();
				}
			});

			a1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a1", a1);
				}
			});
			a2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a2", a2);
				}
			});
			a3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a3", a3);
				}
			});
			a4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a4", a4);
				}
			});
			a5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a5", a5);
				}
			});
			a6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a6", a6);
				}
			});
			a7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a7", a7);
				}
			});
			a8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("a8", a8);
				}
			});
			b1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b1", b1);
				}
			});
			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b2", b2);
				}
			});
			b3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b3", b3);
				}
			});
			b4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b4", b4);
				}
			});
			b5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b5", b5);
				}
			});
			b6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b6", b6);
				}
			});
			b7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b7", b7);
				}
			});
			b8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("b8", b8);
				}
			});
			c1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c1", c1);
				}
			});
			c2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c2", c2);
				}
			});
			c3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c3", c3);
				}
			});
			c4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c4", c4);
				}
			});
			c5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c5", c5);
				}
			});
			c6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c6", c6);
				}
			});
			c7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c7", c7);
				}
			});
			c8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("c8", c8);
				}
			});
			d1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d1", d1);
				}
			});
			d2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d2", d2);
				}
			});
			d3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d3", d3);
				}
			});
			d4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d4", d4);
				}
			});
			d5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d5", d5);
				}
			});
			d6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d6", d6);
				}
			});
			d7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d7", d7);
				}
			});
			d8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("d8", d8);
				}
			});
			e1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e1", e1);
				}
			});
			e2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e2", e2);
				}
			});
			e3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e3", e3);
				}
			});
			e4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e4", e4);
				}
			});
			e5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e5", e5);
				}
			});
			e6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e6", e6);
				}
			});
			e7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e7", e7);
				}
			});
			e8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("e8", e8);
				}
			});
			f1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f1", f1);
				}
			});
			f2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f2", f2);
				}
			});
			f3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f3", f3);
				}
			});
			f4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f4", f4);
				}
			});
			f5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f5", f5);
				}
			});
			f6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f6", f6);
				}
			});
			f7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f7", f7);
				}
			});
			f8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("f8", f8);
				}
			});
			g1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g1", g1);
				}
			});
			g2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g2", g2);
				}
			});
			g3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g3", g3);
				}
			});
			g4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g4", g4);
				}
			});
			g5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g5", g5);
				}
			});
			g6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g6", g6);
				}
			});
			g7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g7", g7);
				}
			});
			g8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("g8", g8);
				}
			});
			h1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h1", h1);
				}
			});
			h2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h2", h2);
				}
			});
			h3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h3", h3);
				}
			});
			h4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h4", h4);
				}
			});
			h5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h5", h5);
				}
			});
			h6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h6", h6);
				}
			});
			h7.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h7", h7);
				}
			});
			h8.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					run("h8", h8);
				}
			});
		}

		
		// to run
		private void run(String in, ImageButton img) {
			Log.e("***** run", in);
			if(ReplaysActivity.replay && in.equals("draw")){
				return;
				
			}
			if(illegal==false){
				//undo stuff
				settemp(arrayBoard, totalData.arrayBoard);
				update_player(totalData.arrayBoard,totalData.getPlayer1());
				update_player(totalData.arrayBoard,totalData.getPlayer2());
				initialize_Player(totalData.getPlayer1(), tempPlayer1);// sets the board
				initialize_Player(totalData.getPlayer2(), tempPlayer2);
				
				
			}
			
			drawclick = false;
			
			if (click == false) {
				// its the first click
				// run the controller method

				if (player == -1 || player == 1) {
					// white players turn
					click = true; // done
					textview.setText("White goes");
					lastMove = in;
					prevBut = img;
				} else {
					// its black's turn
					click = true; // done
					textview.setText("Black goes");
					lastMove = in;
					prevBut = img;
				}
			} else {
				// its the second click
				String first = lastMove;
				lastMove = lastMove.concat(" " + in);
				lastMove = lastMove.trim();
				Log.e("***** run", "move: " + lastMove);
				if(ReplaysActivity.replay == false){
					//not in replay mode
					Place start = convertInputToPlace(first);
					if(start.getPiece()!=null){
						if(start.getPiece().checkMove(in.charAt(0), in.charAt(1)) != -1){
							replayData.addMove(lastMove);
						}
					}
					
				}
				
				if(ReplaysActivity.replay == false){
					Undo.setEnabled(true);
				}
				
				if (player == 1) {
					if(!ReplaysActivity.replay){
						//do not check for reaching end with replay
						//the correct string move is already saved
						Place start = convertInputToPlace(first);
						if(start.getPiece() != null){
							if(pawnAtEnd(first, in)){
								if(start.getPiece().checkMove(in.charAt(0), in.charAt(1)) != -1){
									promotePawn(lastMove);
									return;
								}
								else{
									//illegal move
									Log.e("*** runturn ick", "ick");
									textview.setText("Illegal move");

									lastMove = ""; illegal = true;
									click = false;
									return;
								}
							}
						}
						/*
						if(pawnAtEnd(first, in) && ){
							Log.e("***promo", "black pawn at end");
							promotePawn(lastMove);
						}
						*/
					}
					
			
					if (controller.runTurn(lastMove, totalData.getPlayer1(),totalData.getPlayer2())) {
					
						// its a legal move
						player = 2; // other player goes
						prev_player = 1; // set prev player
						nextBut = img;
						lastMove = "";
						illegal = false;
						// check for the check and checkmate
						// check for checkmate
						if (isCheckmate(null, totalData.getPlayer1(),totalData.getPlayer2())) {
							textview.setText("white wins");
							if(ReplaysActivity.replay == false)
							whiteWinsAlert();
							}
						else if (isCheckmate(null, totalData.getPlayer2(),totalData.getPlayer1())) {
							textview.setText("black wins");
							if(ReplaysActivity.replay == false)
							blackWinsAlert();
							}
						
						else if (Controller.isCheck(null, totalData.getPlayer1(), totalData.getPlayer2())){ //checks for otherplayer king
							//textview.setText("Check to black!");
							textview.setText("check");
							Log.e("***run. check", "white checks black");
						}
						else if (Controller.isCheck(null, totalData.getPlayer2(), totalData.getPlayer1())){ //checks for otherplayer king
							Log.e("***run. check", "white checks black");
							textview.setText("Check");
						}
						else{
							textview.setText("black goes");
						}
				
						reset();
					} else {
						// not a legal move
						if (totalData.player1.inCheck)
							textview.setText("get out of check!");
						else
							textview.setText("Illegal move");
						lastMove = "";
						illegal = true;
					}
					click = false;
				} else {
					// its player 2
					if(!ReplaysActivity.replay){
						Place start = convertInputToPlace(first);
						if(start.getPiece() != null){
							if( pawnAtEnd(first, in)){
								if(start.getPiece().checkMove(in.charAt(0), in.charAt(1)) != -1){
									promotePawn(lastMove);
									return;
								}
								else{
									//illegal move
									Log.e("*** runturn ick", "ick");
									textview.setText("Illegal move");

									lastMove = ""; illegal = true;
									click = false;
									return;
								}
							}
							
						}
						/*
						if(pawnAtEnd(first, in) && ){
							Log.e("***promo", "black pawn at end");
							promotePawn(lastMove);
						}
						*/
					}
					
					if (controller.runTurn(lastMove, totalData.getPlayer2(),totalData.getPlayer1())) {
						// its a legal move
						player = 1; // other player goes
						prev_player = 2;
						lastMove = "";
						nextBut = img;
						illegal = false;
						if (isCheckmate(null, totalData.getPlayer1(),totalData.getPlayer2())) {
							textview.setText("white wins");
							if(ReplaysActivity.replay == false)
							whiteWinsAlert();
							}
						else if (isCheckmate(null, totalData.getPlayer2(),totalData.getPlayer1())) {
							textview.setText("black wins");
							if(ReplaysActivity.replay == false)
							blackWinsAlert();
							}
						
						else if (Controller.isCheck(null, totalData.getPlayer1(), totalData.getPlayer2())){ //checks for otherplayer king
							textview.setText("Check");
							Log.e("***run. check", "black checks white");
						}
						else if (Controller.isCheck(null, totalData.getPlayer2(), totalData.getPlayer1())){ //checks for otherplayer king
							textview.setText("check");
							Log.e("***run. check", "black checks white");
						}
						else{
							textview.setText("White goes");
						}
						// nextBut.setImageDrawable(prevBut.getDrawable());
						// prevBut.setImageResource(0);
						reset();
					} else {
						// not a legal move
						/*if(ReplaysActivity.replay ==false && replayData.getMoves().size()>0){
							//not already in replay mode
							replayData.deleteMove(replayData.getMoves().size()-1);
						}*/
						if (totalData.player2.inCheck)
							textview.setText("get out of check!");
						else
							textview.setText("Illegal move");

						lastMove = ""; illegal = true;
					}
					click = false;
				}
			}
		}
		
		private void runPromo(String lastMove){
			if(player ==1 ){
				if (controller.runTurn(lastMove, totalData.getPlayer1(),totalData.getPlayer2())) {
					
					// its a legal move
					player = 2; // other player goes
					prev_player = 1; // set prev player
					//nextBut = img;
					//lastMove = "";
					illegal = false;
					// check for the check and checkmate
					// check for checkmate
					if (isCheckmate(null, totalData.getPlayer1(),totalData.getPlayer2())) {
						textview.setText("white wins");
						if(ReplaysActivity.replay == false)
						whiteWinsAlert();
						}
					else if (isCheckmate(null, totalData.getPlayer2(),totalData.getPlayer1())) {
						textview.setText("black wins");
						if(ReplaysActivity.replay == false)
						blackWinsAlert();
						}
					
					else if (Controller.isCheck(null, totalData.getPlayer1(), totalData.getPlayer2())){ //checks for otherplayer king
						textview.setText("check");
						Log.e("***runpromo. check", "white checks black");
					}
					/*else if (Controller.isCheck(null, totalData.getPlayer2(), totalData.getPlayer1())){ //checks for otherplayer king
						Log.e("***run. check", "white checks black");
						textview.setText("Check to white!");
					}*/
					else{
						textview.setText("black goes");
					}
			
					reset();
				} else {
					// not a legal move
					if (totalData.player1.inCheck)
						textview.setText("get out of check!");
					else
						textview.setText("Illegal move");
					lastMove = "";
					illegal = true;
				}
				click = false;
			}
			else{
				if (controller.runTurn(lastMove, totalData.getPlayer2(),totalData.getPlayer1())) {
					// its a legal move
					player = 1; // other player goes
					prev_player = 2;
					//lastMove = "";
					//nextBut = img;
					illegal = false;
					if (isCheckmate(null, totalData.getPlayer1(),totalData.getPlayer2())) {
						textview.setText("white wins");
						if(ReplaysActivity.replay == false)
						whiteWinsAlert();
						}
					else if (isCheckmate(null, totalData.getPlayer2(),totalData.getPlayer1())) {
						textview.setText("black wins");
						if(ReplaysActivity.replay == false)
						blackWinsAlert();
						}
					
					else if (Controller.isCheck(null, totalData.getPlayer1(), totalData.getPlayer2())){ //checks for otherplayer king
						textview.setText("Check");
						Log.e("***run. check", "black checks white");
					}
					else if (Controller.isCheck(null, totalData.getPlayer2(), totalData.getPlayer1())){ //checks for otherplayer king
						textview.setText("check");
						Log.e("***runpromo. check", "black checks white");
					}
					else{
						textview.setText("White goes");
					}
					// nextBut.setImageDrawable(prevBut.getDrawable());
					// prevBut.setImageResource(0);
					reset();
				} else {
					// not a legal move
					/*if(ReplaysActivity.replay ==false && replayData.getMoves().size()>0){
						//not already in replay mode
						replayData.deleteMove(replayData.getMoves().size()-1);
					}*/
					if (totalData.player2.inCheck)
						textview.setText("get out of check!");
					else
						textview.setText("Illegal move");

					lastMove = ""; illegal = true;
				}
				click = false;
			}
		}
		
		private boolean pawnAtEnd(String start, String end){
			Log.e("*** pawnAtEnd", start + "|" + end);
			Place begin = convertInputToPlace(start);
			if(begin.getPiece() == null){
				return false;
			}
			if(!begin.getPiece().getType().equals("pawn")){
				return false;
			}
			if(end.charAt(1) == '1' || end.charAt(1) == '8'){
				Log.e("***pawnAtEnd", "" + end.charAt(1));
				return true;
			}
			return false;
		}
		
		/**
		 * does the promotion for pawn
		 * @param move the move that the player has chosen
		 * @return String formatted to be passed into controller with promotion piece type
		 */
		private String promotePawn(final String move){
			Log.e("**** promotpawn", move);
			final String[] pieceNames;
			final String[] pieceAbbr = {"Q", "B", "N", "R"};
	 	    pieceNames = getResources().getStringArray(R.array.piece_array);
	 	    final int i; 
			AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
		    builder.setTitle(R.string.pick_piece)
		           .setItems(R.array.piece_array, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		               // The 'which' argument contains the index position
		               // of the selected item
		            	   //i = which;
		            	   String selectedPiece = pieceAbbr[which];
		            	   selectedPiece = move + " " + selectedPiece;
		            	   Log.e("**** promotePawn dialog", selectedPiece);
		            	   runPromo(selectedPiece);
		               }
		    });
		    
		    AlertDialog dialog = builder.create();
			dialog.show();
			//TODO
			return movePromotion;
		}

		//calls findplace
			//returns pointer to a Place on the board
			//if not a place, returns null
		private Place convertInputToPlace(String input){
				//at 0 it will be x and at 1 it will be y
				if(input.charAt(0)>='a' && input.charAt(0)<='h' && input.charAt(1)>='1' && input.charAt(1)<='8')
				return findPlace(input.charAt(0), input.charAt(1));
				else
					return null;
				
			}
			
			private Place findPlace(char x, char y){
				Place place = null;
				for(int i=0; i<totalData.getArrayBoard().length; i++){
					for(int j=0; j< totalData.getArrayBoard()[i].length; j++){
						if(totalData.getArrayBoard()[i][j].getX() ==x && totalData.getArrayBoard()[i][j].getY()==y){
							place = totalData.getArrayBoard()[i][j];
						}
					}
				}
				return place;
				
			}
		// for initiliazing in the totaldata and stuff
		private void setup() {

			setupBoard();
			setupPieces();
			setupPieceXY();
			//make sure Players are initialized to correct values - check/checkmate booleans etc
			totalData.player1.inCheck = false;
			totalData.player2.inCheck = false;
			
			try {
				//totalData.readGames();
				readReplay();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("**** setup", "read games has an error");  
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// all the variables
		private void initialize() {
			a1 = (ImageButton) findViewById(R.id.a1);
			totalData.getArrayBoard()[0][0].place = a1;
			a2 = (ImageButton) findViewById(R.id.a2);
			totalData.getArrayBoard()[1][0].place = a2;
			a3 = (ImageButton) findViewById(R.id.a3);
			totalData.getArrayBoard()[2][0].place = a3;
			a4 = (ImageButton) findViewById(R.id.a4);
			totalData.getArrayBoard()[3][0].place = a4;
			a5 = (ImageButton) findViewById(R.id.a5);
			totalData.getArrayBoard()[4][0].place = a5;
			a6 = (ImageButton) findViewById(R.id.a6);
			totalData.getArrayBoard()[5][0].place = a6;
			a7 = (ImageButton) findViewById(R.id.a7);
			totalData.getArrayBoard()[6][0].place = a7;
			a8 = (ImageButton) findViewById(R.id.a8);
			totalData.getArrayBoard()[7][0].place = a8;
			b1 = (ImageButton) findViewById(R.id.b1);
			b2 = (ImageButton) findViewById(R.id.b2);
			b3 = (ImageButton) findViewById(R.id.b3);
			b4 = (ImageButton) findViewById(R.id.b4);
			b5 = (ImageButton) findViewById(R.id.b5);
			b6 = (ImageButton) findViewById(R.id.b6);
			b7 = (ImageButton) findViewById(R.id.b7);
			b8 = (ImageButton) findViewById(R.id.b8);
			totalData.getArrayBoard()[0][1].place = b1;
			totalData.getArrayBoard()[1][1].place = b2;
			totalData.getArrayBoard()[2][1].place = b3;
			totalData.getArrayBoard()[3][1].place = b4;
			totalData.getArrayBoard()[4][1].place = b5;
			totalData.getArrayBoard()[5][1].place = b6;
			totalData.getArrayBoard()[6][1].place = b7;
			totalData.getArrayBoard()[7][1].place = b8;
			c1 = (ImageButton) findViewById(R.id.c1);
			c2 = (ImageButton) findViewById(R.id.c2);
			c3 = (ImageButton) findViewById(R.id.c3);
			c4 = (ImageButton) findViewById(R.id.c4);
			c5 = (ImageButton) findViewById(R.id.c5);
			c6 = (ImageButton) findViewById(R.id.c6);
			c7 = (ImageButton) findViewById(R.id.c7);
			c8 = (ImageButton) findViewById(R.id.c8);
			totalData.getArrayBoard()[0][2].place = c1;
			totalData.getArrayBoard()[1][2].place = c2;
			totalData.getArrayBoard()[2][2].place = c3;
			totalData.getArrayBoard()[3][2].place = c4;
			totalData.getArrayBoard()[4][2].place = c5;
			totalData.getArrayBoard()[5][2].place = c6;
			totalData.getArrayBoard()[6][2].place = c7;
			totalData.getArrayBoard()[7][2].place = c8;
			d1 = (ImageButton) findViewById(R.id.d1);
			d2 = (ImageButton) findViewById(R.id.d2);
			d3 = (ImageButton) findViewById(R.id.d3);
			d4 = (ImageButton) findViewById(R.id.d4);
			d5 = (ImageButton) findViewById(R.id.d5);
			d6 = (ImageButton) findViewById(R.id.d6);
			d7 = (ImageButton) findViewById(R.id.d7);
			d8 = (ImageButton) findViewById(R.id.d8);
			totalData.getArrayBoard()[0][3].place = d1;
			totalData.getArrayBoard()[1][3].place = d2;
			totalData.getArrayBoard()[2][3].place = d3;
			totalData.getArrayBoard()[3][3].place = d4;
			totalData.getArrayBoard()[4][3].place = d5;
			totalData.getArrayBoard()[5][3].place = d6;
			totalData.getArrayBoard()[6][3].place = d7;
			totalData.getArrayBoard()[7][3].place = d8;
			e1 = (ImageButton) findViewById(R.id.e1);
			e2 = (ImageButton) findViewById(R.id.e2);
			e3 = (ImageButton) findViewById(R.id.e3);
			e4 = (ImageButton) findViewById(R.id.e4);
			e5 = (ImageButton) findViewById(R.id.e5);
			e6 = (ImageButton) findViewById(R.id.e6);
			e7 = (ImageButton) findViewById(R.id.e7);
			e8 = (ImageButton) findViewById(R.id.e8);
			totalData.getArrayBoard()[0][4].place = e1;
			totalData.getArrayBoard()[1][4].place = e2;
			totalData.getArrayBoard()[2][4].place = e3;
			totalData.getArrayBoard()[3][4].place = e4;
			totalData.getArrayBoard()[4][4].place = e5;
			totalData.getArrayBoard()[5][4].place = e6;
			totalData.getArrayBoard()[6][4].place = e7;
			totalData.getArrayBoard()[7][4].place = e8;
			f1 = (ImageButton) findViewById(R.id.f1);
			f2 = (ImageButton) findViewById(R.id.f2);
			f3 = (ImageButton) findViewById(R.id.f3);
			f4 = (ImageButton) findViewById(R.id.f4);
			f5 = (ImageButton) findViewById(R.id.f5);
			f6 = (ImageButton) findViewById(R.id.f6);
			f7 = (ImageButton) findViewById(R.id.f7);
			f8 = (ImageButton) findViewById(R.id.f8);
			totalData.getArrayBoard()[0][5].place = f1;
			totalData.getArrayBoard()[1][5].place = f2;
			totalData.getArrayBoard()[2][5].place = f3;
			totalData.getArrayBoard()[3][5].place = f4;
			totalData.getArrayBoard()[4][5].place = f5;
			totalData.getArrayBoard()[5][5].place = f6;
			totalData.getArrayBoard()[6][5].place = f7;
			totalData.getArrayBoard()[7][5].place = f8;
			g1 = (ImageButton) findViewById(R.id.g1);
			g2 = (ImageButton) findViewById(R.id.g2);
			g3 = (ImageButton) findViewById(R.id.g3);
			g4 = (ImageButton) findViewById(R.id.g4);
			g5 = (ImageButton) findViewById(R.id.g5);
			g6 = (ImageButton) findViewById(R.id.g6);
			g7 = (ImageButton) findViewById(R.id.g7);
			g8 = (ImageButton) findViewById(R.id.g8);
			totalData.getArrayBoard()[0][6].place = g1;
			totalData.getArrayBoard()[1][6].place = g2;
			totalData.getArrayBoard()[2][6].place = g3;
			totalData.getArrayBoard()[3][6].place = g4;
			totalData.getArrayBoard()[4][6].place = g5;
			totalData.getArrayBoard()[5][6].place = g6;
			totalData.getArrayBoard()[6][6].place = g7;
			totalData.getArrayBoard()[7][6].place = g8;
			h1 = (ImageButton) findViewById(R.id.h1);
			h2 = (ImageButton) findViewById(R.id.h2);
			h3 = (ImageButton) findViewById(R.id.h3);
			h4 = (ImageButton) findViewById(R.id.h4);
			h5 = (ImageButton) findViewById(R.id.h5);
			h6 = (ImageButton) findViewById(R.id.h6);
			h7 = (ImageButton) findViewById(R.id.h7);
			h8 = (ImageButton) findViewById(R.id.h8);
			totalData.getArrayBoard()[0][7].place = h1;
			totalData.getArrayBoard()[1][7].place = h2;
			totalData.getArrayBoard()[2][7].place = h3;
			totalData.getArrayBoard()[3][7].place = h4;
			totalData.getArrayBoard()[4][7].place = h5;
			totalData.getArrayBoard()[5][7].place = h6;
			totalData.getArrayBoard()[6][7].place = h7;
			totalData.getArrayBoard()[7][7].place = h8;

		}

		private static void setupPieceXY() {
			for (int i = 0; i < totalData.getArrayBoard().length; i++) {
				for (int y = 0; y < totalData.getArrayBoard()[i].length; y++) {
					if (totalData.getArrayBoard()[i][y].getPiece() != null) {
						// set that piece's x y
						char x = totalData.getArrayBoard()[i][y].getX();
						char Y = totalData.getArrayBoard()[i][y].getY();
						totalData.getArrayBoard()[i][y].getPiece().setX(x);
						totalData.getArrayBoard()[i][y].getPiece().setY(Y);
					}

				}
			}

		}

		private void setupBoard() {
			// e 6 null "  "| "##"
			String name = "error";
			char number = '1';
			for (int i = 0; i < totalData.getArrayBoard().length; i++) {
				// row

				char letter = 'a';
				for (int y = 0; y < totalData.getArrayBoard().length; y++) {
					// column
					if ((i % 2 == 0 && y % 2 == 0) || i % 2 != 0 && y % 2 != 0) {
						// even, even OR odd, odd is spaces
						name = "  ";
					} else {
						name = "##";
					}
					Place place = new Place(letter, number, null, name);
					totalData.getArrayBoard()[i][y] = place;
					Place place2 = new Place(letter, number, null, name);
					arrayBoard[i][y] = place2;
					letter++;
				}
				number++;
			}
		}

		// getting pics on
		private void getpics() {
			// white pawn
			Drawable myIcon = getResources().getDrawable(R.drawable.wp);
			a2.setImageDrawable(myIcon);
			b2.setImageDrawable(myIcon);
			c2.setImageDrawable(myIcon);
			d2.setImageDrawable(myIcon);
			e2.setImageDrawable(myIcon);
			f2.setImageDrawable(myIcon);
			g2.setImageDrawable(myIcon);
			h2.setImageDrawable(myIcon);
			// black pawn
			myIcon = getResources().getDrawable(R.drawable.bp);
			a7.setImageDrawable(myIcon);
			b7.setImageDrawable(myIcon);
			c7.setImageDrawable(myIcon);
			d7.setImageDrawable(myIcon);
			e7.setImageDrawable(myIcon);
			f7.setImageDrawable(myIcon);
			g7.setImageDrawable(myIcon);
			h7.setImageDrawable(myIcon);
			// set all the other white players
			// set rooks
			myIcon = getResources().getDrawable(R.drawable.wr);
			a1.setImageDrawable(myIcon);
			h1.setImageDrawable(myIcon);
			// set knight
			myIcon = getResources().getDrawable(R.drawable.wkn);
			b1.setImageDrawable(myIcon);
			g1.setImageDrawable(myIcon);
			// set bishop
			myIcon = getResources().getDrawable(R.drawable.wb);
			c1.setImageDrawable(myIcon);
			f1.setImageDrawable(myIcon);
			// set queen
			myIcon = getResources().getDrawable(R.drawable.wq);
			d1.setImageDrawable(myIcon);
			// set king
			myIcon = getResources().getDrawable(R.drawable.wk);
			e1.setImageDrawable(myIcon);

			// set all the black players
			// set rooks
			myIcon = getResources().getDrawable(R.drawable.br);
			a8.setImageDrawable(myIcon);
			h8.setImageDrawable(myIcon);
			// set knight
			myIcon = getResources().getDrawable(R.drawable.bkn);
			b8.setImageDrawable(myIcon);
			g8.setImageDrawable(myIcon);
			// set bishop
			myIcon = getResources().getDrawable(R.drawable.bb);
			c8.setImageDrawable(myIcon);
			f8.setImageDrawable(myIcon);
			// set queen
			myIcon = getResources().getDrawable(R.drawable.bq);
			d8.setImageDrawable(myIcon);
			// set king
			myIcon = getResources().getDrawable(R.drawable.bk);
			e8.setImageDrawable(myIcon);

		}
		
		private boolean isCheckmate(Place end, Player player, Player otherPlayer){
				//just a boolean
				boolean retur = false;
							
				for(int i=0; i<otherPlayer.getLiving().size(); i++){
					//start checking. 
					//boolean doub = otherPlayer.getLiving().get(i).isDoub();
					//int rep = otherPlayer.getLiving().get(i).getRep();
					//try and revert back to this
					//original board
					Player tempplayer1= new Player("temp", 1); //temp player created if the move is valid. 
					Player tempplayer2 = new Player("temp",0); //temp for black
					Place[][] tempBoard = new Place[8][8]; //temp board created 
					for (int io = 0; io < totalData.getArrayBoard().length; io++) {
						// row
						for (int y = 0; y < totalData.getArrayBoard().length; y++) {
							// column
							Place place = new Place(totalData.getArrayBoard()[io][y].getX(), totalData.getArrayBoard()[io][y].getY(), null, totalData.getArrayBoard()[io][y].getName());
							tempBoard[io][y] = place;
							
						}
						
					}
					settemp(tempBoard,totalData.arrayBoard); //board state is stored
					initialize_Player(totalData.getPlayer1(),tempplayer1);
					initialize_Player(totalData.getPlayer2(),tempplayer2);
					totalData.printboard();
					for(int iu=0; iu<totalData.getPlayer1().getLiving().size();iu++){
						System.out.println("white in checkm: " + totalData.getPlayer1().getLiving().get(iu).getName() + " " + totalData.getPlayer1().getLiving().get(iu).getX()+totalData.getPlayer1().getLiving().get(iu).getY());
					}
					for(int iu=0; iu<totalData.getPlayer2().getLiving().size();iu++){
						System.out.println("black: in checkm " + totalData.getPlayer2().getLiving().get(iu).getName() + " " + totalData.getPlayer2().getLiving().get(iu).getX()+totalData.getPlayer2().getLiving().get(iu).getY());
					}
					
					
					
					for(int row=0; row<8;row++){
						for(int col =0; col<8; col++){
							//check every possible move on the board and then check if its in check, also make a temp board
							if(totalData.pieceCheckMove(otherPlayer.getLiving().get(i), otherPlayer.getLiving().get(i).getX(), otherPlayer.getLiving().get(i).getY(), (char)('0'+col+49), (char)('0'+row+1)) !=-1){
								//move is valid at that spot
								//check if its in going to be check or not
								//now move the piece
								int ret = otherPlayer.getLiving().get(i).move((char)('0'+col+49), (char)('0'+row+1));
								if(Controller.isCheck(null, player, otherPlayer)){
									retur = true;
									System.out.println("in checkmate true: " + otherPlayer.getLiving().get(i).getName()+ " "+ otherPlayer.getLiving().get(i).getX() + otherPlayer.getLiving().get(i).getY() + " " +(char)('0'+col+49) + (char)('0'+row+1));							

								}
								else{
									System.out.println("in checkmate false: " + otherPlayer.getLiving().get(i).getName()+ " "+ otherPlayer.getLiving().get(i).getX() + otherPlayer.getLiving().get(i).getY() + " " +(char)('0'+col+49) + (char)('0'+row+1));							
									retur = false;
								}
								//revert to normal
								settemp(totalData.arrayBoard,tempBoard);
								initialize_Player(tempplayer1,totalData.getPlayer1());
								initialize_Player(tempplayer2,totalData.getPlayer2());
								update_player(totalData.arrayBoard,totalData.getPlayer1());
								update_player(totalData.arrayBoard,totalData.getPlayer2());
								if(retur==false){
									return false; //it is not in checkmate
								}
							}
						}
					
					}
			
			
				}
			
			return retur;
		
		}
		
		private Piece copy(Piece c){
			Piece temp = null;
			if(c==null){
				return temp;
			}
			else{
				
				if(c.getName().equalsIgnoreCase("wR")){
				temp= new Rook("wR", c.getX(), c.getY(), c.getColor());
				}
				else if(c.getName().equalsIgnoreCase("wN")){
				temp= new Knight("wN", c.getX(), c.getY(), c.getColor());
				}
				else if(c.getName().equalsIgnoreCase("wB")){
				temp= new Bishop("wB", c.getX(), c.getY(), c.getColor());
				}
				else if(c.getName().equalsIgnoreCase("wQ")){
					temp= new Queen("wQ", c.getX(), c.getY(), c.getColor());
					}
				else if(c.getName().equalsIgnoreCase("wK")){
					temp= new King("wK", c.getX(), c.getY(), c.getColor());
					}
				else if(c.getName().equalsIgnoreCase("wp")){
					temp = new Pawn("wp", c.getX(), c.getY(), c.getColor());
				}
				
				else if(c.getName().equalsIgnoreCase("bR")){
					temp= new Rook("bR", c.getX(), c.getY(), c.getColor());
					}
					else if(c.getName().equalsIgnoreCase("bN")){
					temp= new Knight("bN", c.getX(), c.getY(), c.getColor());
					}
					else if(c.getName().equalsIgnoreCase("bB")){
					temp= new Bishop("bB", c.getX(), c.getY(), c.getColor());
					}
					else if(c.getName().equalsIgnoreCase("bQ")){
						temp= new Queen("bQ", c.getX(), c.getY(), c.getColor());
						}
					else if(c.getName().equalsIgnoreCase("bK")){
						temp= new King("bK", c.getX(), c.getY(), c.getColor());
						}
					else if(c.getName().equalsIgnoreCase("bp")){
						temp = new Pawn("bp", c.getX(), c.getY(), c.getColor());
					}
		
			temp.setDoub(c.isDoub());
			temp.setRep(c.getRep());
			
			return temp;	
			}
		}

		// sets the board according to the other
		private void settemp(Place[][] arrayBoard1, Place[][] arrayBoard2) {
			
			for (int i = 0; i < arrayBoard1.length; i++) {

				for (int j = 0; j < arrayBoard1[i].length; j++) {
					arrayBoard1[i][j].place = arrayBoard2[i][j].place;
					
					//Log.e("****in settemp", "get piece:" + arrayBoard2[i][j].getPiece());
					arrayBoard1[i][j].setPiece(copy(arrayBoard2[i][j].getPiece()));
					arrayBoard1[i][j].setName(arrayBoard2[i][j].getName());

					if (arrayBoard2[i][j].getPiece() != null) {
						
						arrayBoard1[i][j].getPiece().setX(
								arrayBoard2[i][j].getPiece().getX());
						arrayBoard1[i][j].getPiece().setY(
								arrayBoard2[i][j].getPiece().getY());
					}
					arrayBoard1[i][j].setX((arrayBoard2[i][j].getX()));
					arrayBoard1[i][j].setY((arrayBoard2[i][j].getY()));
				}
			}
			
		}

		private void update_player(Place[][]arrayBoard1, Player play){
			
			//delte everything
			for(int i =play.getLiving().size()-1; i>=0;i--)
				play.getLiving().remove(i);
			
			for (int i = 0; i < arrayBoard1.length; i++) {

				for (int j = 0; j < arrayBoard1[i].length; j++) {
					if(arrayBoard1[i][j].getPiece()!=null&&arrayBoard1[i][j].getPiece().getColor()==play.getColor())
						play.getLiving().add(copy(arrayBoard1[i][j].getPiece()));
					
				}
			}
		}
		
		
		private void initialize_Player(Player play1, Player play2) { //second thing gets replaced

			for (int i = play2.getLiving().size() - 1; i >= 0; i--)
				play2.getLiving().remove(i);

			for (int i = play2.getDead().size() - 1; i >= 0; i--)
				play2.getDead().remove(i);

			for (int i = 0; i < play1.getLiving().size(); i++) {
				play2.getLiving().add(copy(play1.getLiving().get(i)));
			}

			for (int i = 0; i < play1.getDead().size(); i++) {
				play2.getDead().add(copy(play1.getDead().get(i)));
			}

			play2.setKing(copy(play1.king));

		}

		// reset the board
		private void reset() {

			for (int i = 0; i < totalData.getArrayBoard().length; i++) {
				// for columsn
				for (int j = 0; j < totalData.getArrayBoard()[i].length; j++) {
					// nextBut.setImageDrawable(prevBut.getDrawable());
					// prevBut.setImageResource(0);
					if (totalData.getArrayBoard()[i][j].getPiece() == null) {
						totalData.getArrayBoard()[i][j].place.setImageResource(0);
					} else {
						if (totalData.getArrayBoard()[i][j].getPiece() != null) {
							if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wp")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wp));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("bp")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bp));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wb")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wb));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("bb")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bb));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wN")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wkn));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("bN")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bkn));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wQ")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wq));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("bq")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bq));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wr")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wr));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("br")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.br));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("wk")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wk));
							} else if (totalData.getArrayBoard()[i][j].getPiece()
									.getName().equalsIgnoreCase("bk")) {
								totalData.getArrayBoard()[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bk));
							}
						}
					}
				}

			}

		}

		private void draw() {
			if(ReplaysActivity.replay ==false){
				//not already in replay mode
				//replayData.deleteMove(replayData.getMoves().size()-1);
				replayData.addMove("draw");
			}
			if (drawclick == false) {
				if (player == 1 || player == -1) {
					player = 2;
					drawclick = true;
					textview.setText("black draw?");
				} else {
					player = 1;
					drawclick = true;
					textview.setText("white draw?");
				}
			} else {
				if (player == 1 || player == -1) {
					
					textview.setText("black draw?");
				} else {
					
					textview.setText("white draw?");
				}
				if(ReplaysActivity.replay)
					return;
				
				AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
				
				builder.setMessage(R.string.draw_text).setTitle(R.string.game_over);
				
				final EditText input = new EditText(this);
				builder.setView(input);
				
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User clicked OK button
								Log.e("**** draw", "user clicked ok button");
								Intent i = new Intent(GameActivity.this,
										MainActivity.class); // resets objects too,
																// from, to
								finish();// whatever activity its on, you will get done
											// with it, cant come back to it
								startActivity(i);// starting the activity
							}
						});
				builder.setNeutralButton(R.string.save_replay,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User clicked OK button
								Log.e("**** draw", "user clicked save button");
								//TODO replay serialize, open replay activity
								if(!input.getText().toString().equals("")){
									replayData.setName(input.getText().toString());
								}
								
								totalData.replays.add(replayData);
								try {
									updateReplay();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								Intent i = new Intent(GameActivity.this,
										MainActivity.class); // resets objects too,
																// from, to
								finish();// whatever activity its on, you will get done
											// with it, cant come back to it
								startActivity(i);// starting the activity
							}
						});

				AlertDialog dialog = builder.create();
				dialog.show();
			}

		}

		private void blackWinsAlert() {
			AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

			builder.setMessage(R.string.black_wins).setTitle(R.string.game_over);
			
			final EditText input = new EditText(this);
			builder.setView(input);
			
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** black wins", "user clicked ok button");
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});
			
			builder.setNeutralButton(R.string.save_replay,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** black wins", "user clicked save button");
							//TODO replay serialize, open replay activity
							if(!input.getText().toString().equals("")){
								replayData.setName(input.getText().toString());
							}
							
							totalData.replays.add(replayData);
							try {
								updateReplay();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();
		}

		private void whiteWinsAlert() {
			AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

			builder.setMessage(R.string.white_wins).setTitle(R.string.game_over);
			final EditText input = new EditText(this);
			builder.setView(input);
			
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** white wins", "user clicked ok button");
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});
			builder.setNeutralButton(R.string.save_replay,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** white wins", "user clicked save button");
							//TODO replay serialize, open replay activity
							if(!input.getText().toString().equals("")){
								replayData.setName(input.getText().toString());
							}
							totalData.replays.add(replayData);
							try {
								updateReplay();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();
		}
		
		private void replayEnd(){
			AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

			builder.setMessage(R.string.ok_to_continue).setTitle(R.string.replay_end);
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** replayEnd", "user clicked ok button");
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});
			AlertDialog dialog = builder.create();
			dialog.show();
		}

		private void resign() {

			if(ReplaysActivity.replay ==false){
				//not already in replay mode
				//replayData.deleteMove(replayData.getMoves().size()-1);
				replayData.addMove("resign");
			}
			else{
				return; // we are in replay mode
			}
			
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

			builder.setMessage(R.string.resigned_text).setTitle(R.string.game_over);
			
			// Set an EditText view to get user input 
			final EditText input = new EditText(this);
			builder.setView(input);
			
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** resign", "user clicked ok button");
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});
			builder.setNeutralButton(R.string.save_replay,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
							Log.e("**** resign", "user clicked save button");
							//TODO replay serialize, open replay activity
							if(!input.getText().toString().equals("")){
								replayData.setName(input.getText().toString());
							}
							
							totalData.replays.add(replayData);
							try {
								updateReplay();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Intent i = new Intent(GameActivity.this,
									MainActivity.class); // resets objects too,
															// from, to
							finish();// whatever activity its on, you will get done
										// with it, cant come back to it
							startActivity(i);// starting the activity
						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();

		}

		private void al() {
			// showing a possible move
			Player player1 = null;
			Player player2 = null;
			if (player == 1 || player == -1) {
				player1 = totalData.getPlayer1();
				player2 = totalData.getPlayer2();
			} 
			else{
				player1 = totalData.getPlayer2();
				player2 = totalData.getPlayer1();
			}
			//System.out.println("in al: player" + player);
			for (int i = 0; i < totalData.getArrayBoard().length; i++) {

				for (int j = 0; j < totalData.getArrayBoard()[i].length; j++) {// colomsn
					for (int k = 0; k < player1.getLiving().size(); k++) {
						// check every spot
						if (player1.getLiving().get(k).checkMove(totalData.getArrayBoard()[i][j].getX(),totalData.arrayBoard[i][j].getY()) != -1) {
							
							textview.setText(player1.getLiving().get(k).getName()+ " from: " + player1.getLiving().get(k).getX()
									+ player1.getLiving().get(k).getY() + " "
									+ totalData.getArrayBoard()[i][j].getX()
									+ totalData.arrayBoard[i][j].getY());
							/*Log.e("****Al", "x:" + player1.getLiving().get(k).getX()
									+ player1.getLiving().get(k).getY() + ", y:" +totalData.getArrayBoard()[i][j].getX()
									+ totalData.arrayBoard[i][j].getY());
	*/
							click = true;
							lastMove = "" + player1.getLiving().get(k).getX()
									+ player1.getLiving().get(k).getY();
							run(""+totalData.getArrayBoard()[i][j].getX()
									+ totalData.arrayBoard[i][j].getY(), null);
							return;
						}

					}
				}
			}
			textview.setText("no possible move");
			return;
		}

		private void undo() {
			// last move
			Undo.setEnabled(false);
			
			if(!illegal){
				player = prev_player;
			}
			if(player==1)
				textview.setText("white turn");
			else
				textview.setText("black turn");
			
			//remove the last move from the replay data
			if(ReplaysActivity.replay ==false){
				replayData.addMove("undo");
			}
			
			settemp(totalData.arrayBoard, arrayBoard);
			update_player(totalData.arrayBoard,totalData.getPlayer1());
			update_player(totalData.arrayBoard,totalData.getPlayer2());
			initialize_Player(tempPlayer1, totalData.getPlayer1());
			initialize_Player(tempPlayer2, totalData.getPlayer2());
			//print board. 
			
			
			
			for (int i = 0; i < arrayBoard.length; i++) {
				// for columsn
				for (int j = 0; j < arrayBoard[i].length; j++) {
					// nextBut.setImageDrawable(prevBut.getDrawable());
					// prevBut.setImageResource(0);
					if (arrayBoard[i][j].getPiece() == null) {
						arrayBoard[i][j].place.setImageResource(0);
					} else {
						if (arrayBoard[i][j].getPiece() != null) {
							if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wp")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wp));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("bp")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bp));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wb")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wb));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("bb")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bb));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wN")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wkn));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("bN")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bkn));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wQ")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wq));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("bq")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bq));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wr")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wr));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("br")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.br));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("wk")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.wk));
							} else if (arrayBoard[i][j].getPiece().getName()
									.equalsIgnoreCase("bk")) {
								arrayBoard[i][j].place
										.setImageDrawable(getResources()
												.getDrawable(R.drawable.bk));
							}
						}
					}
				}

			}
		}

		private void setupPieces() {
			/*
			 * black = 0, white =1
			 */

			// for white

			// 8 pawns
			for (int i = 0; i < 8; i++) {
				Piece piece = new Pawn("wp", ' ', ' ', 1);
				totalData.getPlayer1().getLiving().add(piece);
				totalData.getArrayBoard()[1][i].setPiece(piece);

			}
			// ImageButton bt1 = (ImageButton)findViewById(R.id.b1);
			// bt1.setImageResource(R.drawable.king);
			// 2 rooks
			Piece rook1 = new Rook("wR", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(rook1);
			totalData.getArrayBoard()[0][0].setPiece(rook1);
			Piece rook2 = new Rook("wR", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(rook2);
			totalData.getArrayBoard()[0][7].setPiece(rook2);

			// 2 knights
			Piece knight1 = new Knight("wN", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(knight1);
			totalData.getArrayBoard()[0][1].setPiece(knight1);
			Piece knight2 = new Knight("wN", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(knight2);
			totalData.getArrayBoard()[0][6].setPiece(knight2);

			// 2 bishops
			Piece bishop1 = new Bishop("wB", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(bishop1);
			totalData.getArrayBoard()[0][2].setPiece(bishop1);
			Piece bishop2 = new Bishop("wB", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(bishop2);
			totalData.getArrayBoard()[0][5].setPiece(bishop2);

			// 1 queen
			Piece queen1 = new Queen("wQ", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(queen1);
			totalData.getArrayBoard()[0][3].setPiece(queen1);
			// 1 king
			Piece king1 = new King("wK", ' ', ' ', 1);
			totalData.getPlayer1().getLiving().add(king1);
			totalData.getPlayer1().king = king1;
			totalData.getArrayBoard()[0][4].setPiece(king1);

			// -----------------
			// for black
			// 8 pawns
			for (int i = 0; i < 8; i++) {
				Piece piece = new Pawn("bp", ' ', ' ', 0);
				totalData.getPlayer2().getLiving().add(piece);
				totalData.getArrayBoard()[6][i].setPiece(piece);
			}
			// 2 rooks
			Piece rook3 = new Rook("bR", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(rook3);
			totalData.getArrayBoard()[7][0].setPiece(rook3);
			Piece rook4 = new Rook("bR", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(rook4);
			totalData.getArrayBoard()[7][7].setPiece(rook4);

			// 2 knights
			Piece knight3 = new Knight("bN", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(knight3);
			totalData.getArrayBoard()[7][1].setPiece(knight3);
			Piece knight4 = new Knight("bN", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(knight4);
			totalData.getArrayBoard()[7][6].setPiece(knight4);

			// 2 bishops
			Piece bishop3 = new Bishop("bB", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(bishop3);
			totalData.getArrayBoard()[7][2].setPiece(bishop3);
			Piece bishop4 = new Bishop("bB", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(bishop4);
			totalData.getArrayBoard()[7][5].setPiece(bishop4);

			// 1 queen
			Piece queen2 = new Queen("bQ", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(queen2);
			totalData.getArrayBoard()[7][3].setPiece(queen2);
			// 1 king
			Piece king2 = new King("bK", ' ', ' ', 0);
			totalData.getPlayer2().getLiving().add(king2);
			totalData.getPlayer2().king = king2;
			totalData.getArrayBoard()[7][4].setPiece(king2);

			// set the king location
			totalData.setPlayerKing(king1, totalData.getPlayer1());
			totalData.setPlayerKing(king2, totalData.getPlayer2());
		}
		
		//makes board unclickable for the replay
		private void disableBoard(){
			Undo.setEnabled(false);
			Draw.setEnabled(false);
			Resign.setEnabled(false);
			AI.setEnabled(false);
			a1.setEnabled(false);
			a2.setEnabled(false);
			a3.setEnabled(false);
			a4.setEnabled(false);
			a5.setEnabled(false);
			a6.setEnabled(false);
			a7.setEnabled(false);
			a8.setEnabled(false);
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
			b7.setEnabled(false);
			b8.setEnabled(false);
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			c4.setEnabled(false);
			c5.setEnabled(false);
			c6.setEnabled(false);
			c7.setEnabled(false);
			c8.setEnabled(false);
			d1.setEnabled(false);
			d2.setEnabled(false);
			d3.setEnabled(false);
			d4.setEnabled(false);
			d5.setEnabled(false);
			d6.setEnabled(false);
			d7.setEnabled(false);
			d8.setEnabled(false);
			e1.setEnabled(false);
			e2.setEnabled(false);
			e3.setEnabled(false);
			e4.setEnabled(false);
			e5.setEnabled(false);
			e6.setEnabled(false);
			e7.setEnabled(false);
			e8.setEnabled(false);
			f1.setEnabled(false);
			f2.setEnabled(false);
			f3.setEnabled(false);
			f4.setEnabled(false);
			f5.setEnabled(false);
			f6.setEnabled(false);
			f7.setEnabled(false);
			f8.setEnabled(false);
			g1.setEnabled(false);
			g2.setEnabled(false);
			g3.setEnabled(false);
			g4.setEnabled(false);
			g5.setEnabled(false);
			g6.setEnabled(false);
			g7.setEnabled(false);
			g8.setEnabled(false);
			h1.setEnabled(false);
			h2.setEnabled(false);
			h3.setEnabled(false);
			h4.setEnabled(false);
			h5.setEnabled(false);
			h6.setEnabled(false);
			h7.setEnabled(false);
			h8.setEnabled(false);
		}
		
		private ImageButton convertMoveToButton(String place) {
			if(place.equals("a1")){
				return a1;
			}else if(place.equals("a2")){
				return a2;
			}else if(place.equals("a3")){
				return a3;
			}else if(place.equals("a4")){
				return a4;
			}else if(place.equals("a5")){
				return a5;
			}else if(place.equals("a6")){
				return a6;
			}else if(place.equals("a7")){
				return a7;
			}else if(place.equals("a8")){
				return a8;
			}else if(place.equals("b1")){
				return b1;
			}else if(place.equals("b2")){
				return b2;
			}else if(place.equals("b3")){
				return b3;
			}else if(place.equals("b4")){
				return b4;
			}else if(place.equals("b5")){
				return b5;
			}else if(place.equals("b6")){
				return b6;
			}else if(place.equals("b7")){
				return b7;
			}else if(place.equals("b8")){
				return b8;
			}else if(place.equals("b1")){
				return c1;
			}else if(place.equals("c2")){
				return c2;
			}else if(place.equals("c3")){
				return c3;
			}else if(place.equals("c4")){
				return c4;
			}else if(place.equals("c5")){
				return c5;
			}else if(place.equals("c6")){
				return c6;
			}else if(place.equals("c7")){
				return c7;
			}else if(place.equals("c8")){
				return c8;
			}else if(place.equals("d1")){
				return d1;
			}else if(place.equals("d2")){
				return d2;
			}else if(place.equals("d3")){
				return d3;
			}else if(place.equals("d4")){
				return d4;
			}else if(place.equals("d5")){
				return d5;
			}else if(place.equals("d6")){
				return d6;
			}else if(place.equals("d7")){
				return d7;
			}else if(place.equals("d8")){
				return d8;
			}else if(place.equals("e1")){
				return e1;
			}else if(place.equals("e2")){
				return e2;
			}else if(place.equals("e3")){
				return e3;
			}else if(place.equals("e4")){
				return e4;
			}else if(place.equals("e5")){
				return e5;
			}else if(place.equals("e6")){
				return e6;
			}else if(place.equals("e7")){
				return e7;
			}else if(place.equals("e8")){
				return e8;
			}else if(place.equals("f1")){
				return f1;
			}else if(place.equals("f2")){
				return f2;
			}else if(place.equals("f3")){
				return f3;
			}else if(place.equals("f4")){
				return f4;
			}else if(place.equals("f5")){
				return f5;
			}else if(place.equals("f6")){
				return f6;
			}else if(place.equals("f7")){
				return f7;
			}else if(place.equals("f8")){
				return f8;
			}else if(place.equals("h1")){
				return h1;
			}else if(place.equals("h2")){
				return h2;
			}else if(place.equals("h3")){
				return h3;
			}else if(place.equals("h4")){
				return h4;
			}else if(place.equals("h5")){
				return h5;
			}else if(place.equals("h6")){
				return h6;
			}else if(place.equals("h7")){
				return h7;
			}else if(place.equals("h8")){
				return h8;
			}
			
			return null;
		}
		
		/**
		 * takes into account the player and draws and stuff
		 * @param input the move 
		 * @return int 0 for normal and 1 for end of game
		 */
		private int play(String input){
				//WHITE IS PLAYER 1
				//this just controls the printing of white and black turns 
				//while allowing for error inputs
				//0 for black and 1 for white
				boolean play = true;
				boolean success = true;
				
				if(round%2 !=0){ //odd rounds are white
						textview.setText("White goes");
						if(input.equals("resign")){
							//System.out.println("Player2 Wins!");
							textview.setText("Game over: White resigned");
							return 1;
						}
						if(input.equals("draw") && totalData.draw == true){
							textview.setText("Game over: draw");
							return 1;
						}
						else if(totalData.draw == true){
							totalData.draw = false;
						}

						success = controller.runTurn(input, totalData.getPlayer1(), totalData.getPlayer2());
						if(!success){
							Log.e("**** replay in play()", "error in move - check legal moves");
						}else{
							round++;
							if(totalData.draw!=true)
								totalData.printboard();
							}
				}
				else{ //even rounds are black
						textview.setText("Black goes");
						if(input.equals("resign")){
							//System.out.println("Player1 Wins!");
							textview.setText("Game over: Black resigned");
							return 1;
						}
						if(input.equals("draw") && totalData.draw == true){
							textview.setText("Game over: draw");
							return 1;
						}
						else if(totalData.draw == true){
							totalData.draw = false;
						}
						System.out.println("");
						success = controller.runTurn(input, totalData.getPlayer2(), totalData.getPlayer1());
						if(!success){
							Log.e("**** replay in play()", "error in move - check legal moves");
						}else{
							round++;
							if(totalData.draw!=true)
								totalData.printboard();
							}
					
				}
				return 0;
				
		}
		
		private void updateReplay() throws FileNotFoundException, IOException{
			Log.e("*** totaldata: serialize", "updating games");
			//ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("saved_game", Context.MODE_PRIVATE));
			FileOutputStream fos = openFileOutput("saved_game", Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(totalData.replays);
			out.close();
			fos.close();
		}
		
		public void readReplay() throws IOException, ClassNotFoundException{
			Log.e("*** readgames", "reading saved games");
			
			FileInputStream fis = openFileInput("saved_game");
			ObjectInputStream in = new ObjectInputStream(fis);
			totalData.replays = (ArrayList<ReplayData>) in.readObject();
			in.close();
			fis.close();
			return;
		}
}
