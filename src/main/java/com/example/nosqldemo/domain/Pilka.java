package com.example.nosqldemo.domain;

import org.bson.types.ObjectId;

public class Pilka {
	
	private ObjectId id;		
	private String nazwa;
	private int rozmiar;
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public int getrozmiar() {
		return rozmiar;
	}
	
	public void setrozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}
	
}