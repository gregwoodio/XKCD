package com.example.xkcd;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<Comic>> {
	
	ArrayList<Comic> comics;
	String titles[];
	
	@Override
	protected ArrayList<Comic> doInBackground(String... urls) {

		comics = new ArrayList<Comic>(0);
		try {	
			URL rssUrl = new URL("https://xkcd.com/rss.xml");	
			
			//Parse the XML file
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XkcdHandler handler = new XkcdHandler();
			XMLReader xmlreader = parser.getXMLReader();
			xmlreader.setContentHandler(handler);
			InputSource inputSource = new InputSource(rssUrl.openStream());
			
			//parser.parse(inputSource, handler);
			xmlreader.parse(inputSource);
			comics = handler.getComics();
			return comics;
		

		} catch (MalformedURLException e) {
			comics.add(new Comic("MalformedURLException"));
		} catch (ParserConfigurationException e) {
			comics.add(new Comic("ParserConfigurationException"));
		} catch (SAXException e) {
			comics.add(new Comic("SAX Exception"));
		} catch (IOException e) {
			comics.add(new Comic("IOException"));
		}
		
		return comics;
	}
}
