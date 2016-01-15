package com.example.xkcd;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XkcdHandler extends DefaultHandler {

	boolean item = false;
	boolean title = false;
	boolean link = false;
	boolean description = false;
	boolean pubDate = false;
	Comic comic;
	private StringBuilder chars = new StringBuilder();
	private StringBuilder att = new StringBuilder();
	ArrayList<Comic> comics = new ArrayList<Comic>();
	
	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) throws SAXException {
		
		chars.setLength(0);
		
		if (qName.equalsIgnoreCase("item")) {
			item = true;
			comic = new Comic();
		}
		if (qName.equalsIgnoreCase("title") && item) {
			title = true;
		}
		if (qName.equalsIgnoreCase("link") && item) {
			link = true;
		}
		if (qName.equalsIgnoreCase("description") && item) {
			description = true;
		}
		if (qName.equalsIgnoreCase("pubDate") && item) {
			pubDate = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName) 
		throws SAXException {
		if (title && item) {
			comic.setTitle(chars.toString());
			//System.out.println("Title: " + chars.toString());
			title = false;
		}
		if (link && item) {
			comic.setLink(chars.toString());
			//System.out.println("Link: " + chars.toString());
			link = false;
		}
		if (description && item) {
			comic.setDescription(chars.toString());
			//System.out.println("Description: " + chars.toString());
			description = false;
		}
		if (pubDate && item) {
			comic.setDate(chars.toString());
			//System.out.println("Date: " + chars.toString() + "\n");
			pubDate = false;
			comics.add(comic);
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		chars.append(ch, start, length);
	}
	
	public ArrayList<Comic> getComics() {
		return comics;
	}
}
