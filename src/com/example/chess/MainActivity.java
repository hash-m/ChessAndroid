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

/**
 * @author jasmine and risham
 * 
 * */
public class MainActivity extends Activity {
	Button play;
	Button replay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		try {
			readReplay();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		play = (Button) findViewById(R.id.main_play);
		replay = (Button) findViewById(R.id.main_replay);
		
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
				GameActivity.class); // resets objects too, (from, to)
							// with it, cant come back to it
				startActivity(i);// starting the activity
			}
		});
		
		replay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
				ReplaysActivity.class); // resets objects too, (from, to)
							// with it, cant come back to it
				startActivity(i);// starting the activity
			}
		});
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
