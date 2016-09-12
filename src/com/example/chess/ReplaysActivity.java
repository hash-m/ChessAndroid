/**
 * a list of replays of games available
 */
package com.example.chess;

import model.ReplayData;
import model.totalData;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Jasmine Feng and Risham Chokshi
 *
 */

public class ReplaysActivity extends Activity {
	public static boolean replay = false;
	public static int selectedIndex = 0;
	ListView listView;
	Button back;
	Button dateSort;
	Button nameSort;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_replays);
		dateSort = (Button) findViewById(R.id.date_sort);
		nameSort = (Button) findViewById(R.id.name_sort);
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			// TODO serialize here???
			@Override
			public void onClick(View v) {
				replay = false;
				Intent i = new Intent(ReplaysActivity.this,MainActivity.class); // resets objects too, (from, to)
				finish();// whatever activity its on, you will get done
							// with it, cant come back to it
				startActivity(i);// starting the activity 
			}
		});
		
		listView = (ListView)findViewById(R.id.replay_list);
		
		String[] gameNames = createNamesArray();
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, gameNames);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				replay = true;
				System.out.println(position);
				selectedIndex = position; 
				
				String selectedGame = (String)listView.getItemAtPosition(selectedIndex);
				Intent i = new Intent(ReplaysActivity.this,GameActivity.class); // resets objects too, (from, to)
				//finish();
				startActivity(i);// starting the activity
				finish();
			}
			
		});
		
		
		dateSort.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				totalData.replayDateSort();
				Intent i = new Intent(ReplaysActivity.this,ReplaysActivity.class); // resets objects too, (from, to)
				finish();
				startActivity(i);
			}
			
		});
		
		nameSort.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				totalData.replayNameSort();
				Intent i = new Intent(ReplaysActivity.this,ReplaysActivity.class); // resets objects too, (from, to)
				finish();
				startActivity(i);
				
			}
			
		});

	}

	/**
	 * creates a string array of saved game names from the total data list of saved game data
	 * @return
	 */
	private String[] createNamesArray(){
		//TODO just test data
		/*
		for(int i=0; i<20; i++){
			ReplayData rd = new ReplayData("Game " + i);
			totalData.replays.add(rd);
		}*/
		String[] gameNames = new String[totalData.replays.size()];
		for(int i =0; i<totalData.replays.size(); i++){
			gameNames[i] = totalData.replays.get(i).getName() + " @ " + totalData.replays.get(i).getDate();
		}
		return gameNames;
	}
}