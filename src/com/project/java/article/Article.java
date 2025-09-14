package com.project.java.article;

public class Article {
	private String title;
	private String content;
	private int id;
	private String now;
	
	public String getTitle() {
	    return title;
	}

	public String getContent() {
	    return content;
	}

	public int getId() {
	    return id;
	}

	public String getNow() {
	    return now;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Article(String title, String content, int id, String now) {
		this.title = title;
		this.content = content;
		this.id = id;
		this.now = now;
	}
}