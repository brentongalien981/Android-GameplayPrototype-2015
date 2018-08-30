package com.example.prototype1;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.screenforselectingplayer.ActivityForSelectingPlayer;

public class MainMenu extends Activity implements OnItemClickListener {
	
	private ListView listViewOfMainMenu;
	private ArrayAdapter<String> listAdapter;
	private String[] menus;
	private ArrayList<String> listOfMainMenus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_menu);

		// Find the ListView resource.
		listViewOfMainMenu = (ListView) findViewById(R.id.listView_mainMenu);
		listViewOfMainMenu.setHorizontalScrollBarEnabled(true);

		// Create and populate a List of planet names.
		menus = new String[] { "online game", "local game", "profile", "options"};

		listOfMainMenus = new ArrayList<String>();
		listOfMainMenus.addAll(Arrays.asList(menus));

		// Create ArrayAdapter using the planet list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.layout_for_individual_main_menu_item, menus); 
		

		// Set the ArrayAdapter as the ListView's adapter.
		listViewOfMainMenu.setAdapter(listAdapter);
		listViewOfMainMenu.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
		String temp = listOfMainMenus.get(position);
		Intent intent;
		
		if (temp.equals("local game")) {
			intent = new Intent(this, ActivityForSelectingPlayer.class);
			startActivity(intent);
		}
		else {
			Toast.makeText(this, "that class is not yet created", Toast.LENGTH_LONG).show();
		}
		
		// startActivity();
		
	}
}
