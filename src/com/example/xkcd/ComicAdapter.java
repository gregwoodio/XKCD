package com.example.xkcd;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ComicAdapter extends ArrayAdapter<Comic> {

	private ArrayList<Comic> comics;
	
	public ComicAdapter(Context context, int resource, ArrayList<Comic> objects) {
		super(context, resource, objects);
		comics = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		//check if view is null.
		//A null view still needs to be inflated (rendered).
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
			v = inflater.inflate(R.layout.list_layout, null);
		}
		
		Comic c = comics.get(position);
		
		if (c != null) {
		
			TextView txtTitle = (TextView)v.findViewById(R.id.title);
			TextView txtDate = (TextView)v.findViewById(R.id.date);
			
			if (c.getTitle() != null) {
				txtTitle.setText(c.getTitle());
			}
			if (c.getDate() != null) {
				txtDate.setText(c.getDate());
			}
		}
		
		return v;
	}
	

}
