package com.example.xkcd;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MenuActivity extends ListActivity {

	ArrayList<Comic> comics;
	String titles[];
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ListView myList = (ListView)findViewById(android.R.id.list);
		
		try {			
			
			//Show a loading dialog while waiting for comics
			dialog = ProgressDialog.show(this, "Loading", "Getting new comics...");
			comics = new RetrieveFeedTask().execute("https://xkcd.com/rss.xml").get();
			dialog.dismiss();
			
			ComicAdapter adapter = new ComicAdapter(this, R.layout.list_layout, comics);
			myList.setAdapter(adapter);
		    
			myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView, View childView, int position, long id) {
					Comic c = comics.get(position);
					Intent i = new Intent(MenuActivity.this, DisplayActivity.class);
					i.putExtra("title", c.getTitle());
					i.putExtra("url", c.getUrl());
					i.putExtra("date", c.getDate());
					i.putExtra("description", c.getDescription());
					i.putExtra("link", c.getLink());
					startActivity(i);
				}
			
			});
			
		} catch (ExecutionException e) {
			
		} catch (InterruptedException e) {
			
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			//Refresh the RSS feed, find new comics.
			ListView myList = (ListView)findViewById(android.R.id.list);
			try {			
				//Show a loading dialog while waiting for comics
				dialog = ProgressDialog.show(this, "Loading", "Getting new comics...");
				comics = new RetrieveFeedTask().execute("https://xkcd.com/rss.xml").get();
				dialog.dismiss();
				
				ComicAdapter adapter = new ComicAdapter(this, R.layout.list_layout, comics);
				myList.setAdapter(adapter);
			} catch (ExecutionException e) {	
			} catch (InterruptedException e) {
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
