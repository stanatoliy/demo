package com.example.demo;

import java.io.Serializable;
import java.util.List;

public class NoteItem implements Serializable{
	private static final long serialVersionUID = -2102727572302137280L;
	private String title;
	private String text;
	private List<String> hashTags;
	private long creationTime;
	private int id = 0;
	public NoteItem(String title, String text, List<String> hashtags) {
		this.title = title;
		this.text = text;
		this.hashTags = hashtags;
		this.creationTime = System.currentTimeMillis();
	}
	public int getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public String getText() {
		return this.text;
	}
	public List<String> getHashTags() {
		return this.hashTags;
	}
	public long getCreationTime() {
		return this.creationTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setHashTags(List<String> hashTags) {
		this.hashTags = hashTags;
	}
	public boolean hasHashTag(String hashTag) {
		if(hashTags != null) {
			for (String item : hashTags) {
				if(item.toLowerCase().equals(hashTag.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
}
