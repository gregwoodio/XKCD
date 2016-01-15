package com.example.xkcd;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class BitmapTask extends AsyncTask<String, Void, Bitmap> {

	@Override
	protected Bitmap doInBackground(String... src) {
		try {
			URL url = new URL(src[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream input = conn.getInputStream();
			Bitmap bmp = BitmapFactory.decodeStream(input);
			return bmp;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		
		return null;
	}

}
