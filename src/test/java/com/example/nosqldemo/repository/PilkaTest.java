package com.example.nosqldemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.service.PilkaManager;
import com.example.nosqldemo.domain.Pilka;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class PilkaTest {
	
	@Autowired
	PilkaManager managerpilki;
	
	private final String nazwa1 = "Do kosza";
	private final String nazwa2 = "Do nogi";
	
	private final int rozmiar1 = 5;
	private final int rozmiar2 = 6;
	
	private int rozmiarUnique = 3;
	
	private final int rozmiarUnique1 = 2;
	private final int rozmiarUnique2 = 8;
	
	@Test
	public void checkAdding(){
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiar1);
		
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
		//System.out.println(liczbaPilekprzedDodaniem);
		
		managerpilki.addpilka(pilka);
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
		
		assertEquals(liczbaPilekprzedDodaniem+1, liczbaPilekpoDodaniu);
		managerpilki.deleteAllpilki();
	}
	
	
	@Test
	public void deletepilkaCheck() {
		
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiar1);
		managerpilki.addpilka(pilka);
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedUsunieciem = listaPilek.size();
		
		managerpilki.deletepilka(pilka);
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoUsunieciu = listaPilek2.size();
		
		assertEquals(liczbaPilekprzedUsunieciem, liczbaPilekpoUsunieciu+1);
		
		
		assertEquals(null, managerpilki.getPilkaById(pilka.getId()));
		managerpilki.deleteAllpilki();
	}
	
	
	@Test
	public void deleteAllpilkiCheck() {
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
	
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
	
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
	
		assertNotNull(listaPilek);
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
	
		assertEquals(liczbaPilekpoDodaniu, liczbaPilekprzedDodaniem+2);
		
		managerpilki.deleteAllpilki();
		
		List<Pilka> listaPilek3 = managerpilki.getAllpilki();
		int liczbaPilekpoUsunieciu = listaPilek3.size();
		assertEquals(0, liczbaPilekpoUsunieciu);
	}
	
	
	@Test
	public void findpilkaByIdCheck() {
	
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
		
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiar1);
		
		managerpilki.addpilka(pilka);
		assertNotNull(managerpilki.getPilkaById(pilka.getId()));
		
		managerpilki.deletepilka(pilka);
		assertNull(managerpilki.getPilkaById(pilka.getId()));
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
		
		assertEquals(liczbaPilekprzedDodaniem, liczbaPilekpoDodaniu);
		managerpilki.deleteAllpilki();
	}
	
	@Test
	public void findAllpilkiCheck() {
	
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
	
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
	
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
	
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
	
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
		//assertEquals(liczbaPilekprzedDodaniem, liczbaPilekpoDodaniu-2);
	
		assertNotNull(listaPilek);
	
		//int liczbaPilek = listaPilek.size();
	
		assertEquals(liczbaPilekpoDodaniu, liczbaPilekprzedDodaniem+2);
		managerpilki.deleteAllpilki();
	}

	
	@Test
	public void editpilkaCheck() {
		
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
		
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
		
		Pilka pilka3 = new Pilka();
		pilka3.setNazwa(nazwa2);
		pilka3.setrozmiar(rozmiar2);
		
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		managerpilki.addpilka(pilka3);
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
		assertEquals(liczbaPilekprzedDodaniem+3, liczbaPilekpoDodaniu);
			
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar1);
		managerpilki.updatepilka(pilka2);
		
		ObjectId idpilki = pilka2.getId();
		//Long indeks = (long)0;
		
		Pilka pl = managerpilki.getPilkaById(idpilki);
		
		assertEquals(nazwa2, pl.getNazwa());
		assertEquals(rozmiar1, pl.getrozmiar());
		
		assertEquals(nazwa2, pilka1.getNazwa());
		assertEquals(rozmiar2, pilka1.getrozmiar());
		
		assertEquals(nazwa2, pilka3.getNazwa());
		assertEquals(rozmiar2, pilka3.getrozmiar());
		
		managerpilki.deleteAllpilki();
	}
	
	
	@Test
	public void findpilkaByrozmiarCheck() {
		
		List<Pilka> listaPilek = managerpilki.getAllpilki();
		int liczbaPilekprzedDodaniem = listaPilek.size();
		
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiarUnique);
		
		managerpilki.addpilka(pilka);
		assertNotNull(managerpilki.getPilkaById(pilka.getId()));
		
		List<Pilka> listaPilek2 = managerpilki.getAllpilki();
		int liczbaPilekpoDodaniu = listaPilek2.size();
		
		assertEquals(liczbaPilekpoDodaniu, liczbaPilekprzedDodaniem+1);
		
		assertNotNull(managerpilki.getPilkiByRozmiar(pilka.getrozmiar()));
		List<Pilka> pl = managerpilki.getPilkiByRozmiar(pilka.getrozmiar());
		Pilka pl2 = pl.get(0);
		assertEquals(rozmiarUnique, pl2.getrozmiar());
		
		managerpilki.deleteAllpilki();
	}
	
	
	
}
