package com.example.nosqldemo.domain;

import java.util.List;
import org.bson.types.ObjectId;

public class Kosz {
	
	private ObjectId id;	
	private String NazwaKosza;
    private Integer liczbaObiektow;
    private List<Pilka> pilki;

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getNazwaKosza() {
		return NazwaKosza;
	}
	public void setNazwaKosza(String NazwaKosza) {
		this.NazwaKosza = NazwaKosza;
	}
	
	public int getLiczbaObiektow() {
		return liczbaObiektow;
	}
	public void setLiczbaObiektow(int liczbaObiektow) {
		this.liczbaObiektow = liczbaObiektow;
	}
	
	public List<Pilka> getpilki() {
		return pilki;
	}
	public void setpilki(List<Pilka> pilki) {
		this.pilki = pilki;
	}

}










