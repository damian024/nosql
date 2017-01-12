package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Pilka;
import com.example.nosqldemo.repository.PilkaRepository;

@Component
public class PilkaManager {

	@Autowired
	private  PilkaRepository pilkaRepository;
	
	public void addpilka(Pilka pilka){
		pilkaRepository.save(pilka);
	}
	
	public void deletepilka(Pilka pilka){
		pilkaRepository.delete(pilka);
	}
	
	public void deleteAllpilki(){
		pilkaRepository.deleteAll();
	}
	
	public void updatepilka(Pilka pilka){
		pilkaRepository.save(pilka);
	}
	
	public List<Pilka> getAllpilki(){
		return pilkaRepository.findAll();
	}
	
	public List<Pilka> getPilkiByRozmiar(int rozmiar){
		return pilkaRepository.findByRozmiar(rozmiar);
	}
	
	public Pilka getPilkaById(ObjectId id){
		return pilkaRepository.findById(id);
	}
	
	
	
}
