package com.example.xkcd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	Comic comic;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_comic);

		// get Comic information from intent
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		comic = new Comic();
		comic.setTitle(bundle.getString("title"));
		comic.setDate(bundle.getString("date"));
		comic.setDescription(bundle.getString("description"));
		comic.setLink(bundle.getString("link"));
		comic.setUrl(bundle.getString("url"));

		TextView txtTitle = (TextView) findViewById(R.id.comic_title);
		TextView txtDate = (TextView) findViewById(R.id.comic_date);
		ImageView imgComic = (ImageView) findViewById(R.id.comic_img);
		TextView txtDescription = (TextView) findViewById(R.id.comic_description);
		TextView txtLink = (TextView) findViewById(R.id.comic_link);

		if (comic.getTitle() != null) {
			txtTitle.setText(comic.getTitle());
		}
		if (comic.getDate() != null) {
			txtDate.setText(comic.getDate());
		}
		if (comic.getUrl() != null) {
			try {
				// show a loading dialgo while waiting for the image to download
				dialog = ProgressDialog.show(this, "Loading",
						"Retrieving comic...");
				Bitmap bmp = new BitmapTask().execute(comic.getUrl()).get();
				imgComic.setImageBitmap(bmp);
				dialog.dismiss();
			} catch (Exception e) {

			}
		}
		if (comic.getDescription() != null) {
			// this code is crap. Just trying to get the alt="" text from the
			// RSS feed's description
			String[] str = comic.getDescription().split("\"");
			str[3] = str[3].replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
			txtDescription.setText(str[3]);
		}
		if (comic.getLink() != null) {
			txtLink.setText(comic.getLink());
		}

	}

	// go to the XKCD website for that day's comic
	public void onClickLink(View view) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(comic.getLink()));
		startActivity(intent);
	}

}