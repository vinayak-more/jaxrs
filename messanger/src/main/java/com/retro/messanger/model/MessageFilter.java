package com.retro.messanger.model;

import javax.ws.rs.QueryParam;

public class MessageFilter {

	@QueryParam("year")
	private int year;
	
	@QueryParam("author")
	private String author;
	
	@QueryParam("start")
	private int start;
	
	@QueryParam("size")
	private int size;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "MessageFilter [year=" + year + ", author=" + author
				+ ", start=" + start + ", size=" + size + "]";
	}
	
	
}
