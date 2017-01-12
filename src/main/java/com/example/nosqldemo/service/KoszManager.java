package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Pilka;
import com.example.nosqldemo.domain.Kosz;
import com.example.nosqldemo.repository.PilkaRepository;
import com.example.nosqldemo.repository.KoszRepository;

@Component
public class KoszManager {

	@Autowired
	private  KoszRepository koszRepository;
	
	@Autowired
	private  PilkaRepository pilkaRepository;
	
	public void addNewKosz(Kosz uklad){
		koszRepository.save(uklad);
	}
	
	public void deleteKosz(Kosz uklad){
		for (Pilka pilka : uklad.getpilki()) {
			pilkaRepository.delete(pilka);
		}
		koszRepository.delete(uklad);
	}
	
	public void deleteAllKosze(){
		koszRepository.deleteAll();
	}
	
	public void updateKosz(Kosz uklad){
		koszRepository.save(uklad);
	}
	
	public List<Kosz> getAllKosze(){
		return koszRepository.findAll();
	}
	
	public List<Kosz> getKoszeByLiczbaObiektow(int liczbaObiektow){
		return koszRepository.findByLiczbaObiektow(liczbaObiektow);
	}
	
	public List<Kosz> getKoszByNazwaLiczbaObiektow(String NazwaKosza, int liczbaObiektow){
		return koszRepository.znajdzKosz(NazwaKosza, liczbaObiektow);
	}
	
	public Kosz getKoszById(ObjectId id){
		return koszRepository.findById(id);
	}
	
	public List<Pilka> getpilkiInKosz(Kosz uklad){
		List<Pilka> pilki = new ArrayList<Pilka>(uklad.getpilki());
		return pilki;
	}
	
	public void deletePilkiWKoszuByRozmiar(Kosz uklad, int kryterium){
	
		for (Pilka pilka : uklad.getpilki()) {
			if(pilka.getrozmiar() > kryterium){
				pilkaRepository.delete(pilka);
			}
		}
	}
	
	
}
