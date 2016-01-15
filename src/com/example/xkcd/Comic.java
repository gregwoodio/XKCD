package com.example.xkcd;

public class Comic {
	private String title, link, description, date, imgUrl, altText;

	public Comic() {
		
	}
	
	public Comic(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		String str[] = description.split("\"");
		this.setUrl(str[1]);
	}
	
	public void setAltText() {
		String[] str = description.split("\"");
		if (str.length >= 4) {
			this.altText = str[3];
		}
	}

	public String getAltText() {
		return altText;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return imgUrl;
	}

	public void setUrl(String url) {
		this.imgUrl = url;
	}
	
	
}
