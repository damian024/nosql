package com.example.nosqldemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.service.PilkaManager;
import com.example.nosqldemo.service.KoszManager;
import com.example.nosqldemo.domain.Pilka;
import com.example.nosqldemo.domain.Kosz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class KoszTest {
	
	@Autowired
	PilkaManager managerpilki;
	@Autowired
	KoszManager managerKosz;
	
	//pilka
	private final String nazwa1 = "Meteora";
	private final String nazwa2 = "Nike brazil";
	
	private final int rozmiar1 = 5;
	private final int rozmiar2 = 7;
	
	
	private int rozmiarUnique = 9;
	
	private final int rozmiarUnique2 =2;
	private final int kryterium = 1;
	
	//Kosz
	private final String nazwaU1 = "Do nogi";
	private final String nazwaU2 = "Do kosza";
	
	private final int liczbaObiektow1 = 2;
	private final int liczbaObiektow2 = 4; 
	private final int liczbaObiektowUnique = 6;
	
	private final String NazwaKoszaUnique = "Do rugby";
	//private final int liczbaObiektowUnique2 = 666;
	
	
	@Test
	public void checkAdding(){

		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
		managerpilki.addpilka(pilka1);
		List<Pilka> pilkiTab = new ArrayList<Pilka>();
		pilkiTab.add(pilka1);
		
		Kosz kosz = new Kosz();
		kosz.setNazwaKosza(nazwaU1);
		kosz.setLiczbaObiektow(liczbaObiektow1);
		kosz.setpilki(pilkiTab);
		
		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowprzedDodaniem = listaKoszow.size();
		//System.out.println(liczbaKoszowprzedDodaniem);
		
		managerKosz.addNewKosz(kosz);
		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowpoDodaniu = listaKoszow2.size();
		//System.out.println(liczbaKoszowpoDodaniu);
		
		ObjectId id = kosz.getId();
		Kosz uk = managerKosz.getKoszById(id);
		
		assertEquals(uk.getNazwaKosza(), kosz.getNazwaKosza());
		assertEquals(uk.getLiczbaObiektow(), kosz.getLiczbaObiektow());
		assertEquals(uk.getpilki().size(), kosz.getpilki().size());
		
		assertEquals(uk.getpilki().get(0).getNazwa(), kosz.getpilki().get(0).getNazwa());
		assertEquals(uk.getpilki().get(0).getrozmiar(), kosz.getpilki().get(0).getrozmiar());
		
		assertEquals(liczbaKoszowprzedDodaniem+1, liczbaKoszowpoDodaniu);
		
		managerKosz.deleteAllKosze();
	}
	
	
	@Test
	public void deleteKoszCheck() {
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa1);
		pilka1.setrozmiar(rozmiar1);
		managerpilki.addpilka(pilka1);
		
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa1);
		pilka2.setrozmiar(rozmiar1);
		managerpilki.addpilka(pilka2);
		
		List<Pilka> pilkiTab = new ArrayList<Pilka>();
		pilkiTab.add(pilka1);
		pilkiTab.add(pilka2);
		
		Kosz kosz = new Kosz();
		kosz.setNazwaKosza(nazwaU1);
		kosz.setLiczbaObiektow(liczbaObiektow1);
		kosz.setpilki(pilkiTab);
		managerKosz.addNewKosz(kosz);
		
		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowprzedUsunieciem = listaKoszow.size();
		
		ObjectId idKoszu = kosz.getId();
		managerKosz.deleteKosz(kosz);
		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowpoUsunieciu = listaKoszow2.size();
		
		assertEquals(liczbaKoszowprzedUsunieciem, liczbaKoszowpoUsunieciu+1);
		assertEquals(null, managerKosz.getKoszById(idKoszu));
		
		assertEquals(null, managerpilki.getPilkaById(pilka1.getId()));
		assertEquals(null, managerpilki.getPilkaById(pilka2.getId()));
		
		managerKosz.deleteAllKosze();
	}
	
	
	@Test
	public void deleteAllKoszyCheck() {
		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowPrzedDodaniem = listaKoszow.size();
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
	
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
	
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka1);
		
		List<Pilka> pilkiTab2 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka2);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektow1);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		Kosz kosz2 = new Kosz();
		kosz2.setNazwaKosza(nazwaU1);
		kosz2.setLiczbaObiektow(liczbaObiektow1);
		kosz2.setpilki(pilkiTab2);
		managerKosz.addNewKosz(kosz2);
	
		assertNotNull(listaKoszow);
		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowPoDodaniu = listaKoszow2.size();
	
		assertEquals(liczbaKoszowPoDodaniu, liczbaKoszowPrzedDodaniem+2);
		
		managerKosz.deleteAllKosze();
		
		List<Kosz> listaKoszow3 = managerKosz.getAllKosze();
		int liczbaKoszowpoUsunieciu = listaKoszow3.size();
		assertEquals(0, liczbaKoszowpoUsunieciu);
	}
	
	
	@Test
	public void findKoszCheck() {
		
		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowprzedDodaniem = listaKoszow.size();
		
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiar1);
		managerpilki.addpilka(pilka);
		
		List<Pilka> pilkiTab = new ArrayList<Pilka>();
		pilkiTab.add(pilka);
		
		Kosz kosz = new Kosz();
		kosz.setNazwaKosza(nazwaU1);
		kosz.setLiczbaObiektow(liczbaObiektow1);
		kosz.setpilki(pilkiTab);
		managerKosz.addNewKosz(kosz);
		
		assertNotNull(managerKosz.getKoszById(kosz.getId()));
		assertNotNull(managerpilki.getPilkaById(pilka.getId()));
		
		managerKosz.deleteKosz(kosz);
		assertNull(managerpilki.getPilkaById(pilka.getId()));
		assertNull(managerKosz.getKoszById(kosz.getId()));
		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowPoUsunieciu = listaKoszow2.size();
		
		assertEquals(liczbaKoszowprzedDodaniem, liczbaKoszowPoUsunieciu);
		
		managerKosz.deleteAllKosze();
	}
	
	
	@Test
	public void findAllKoszyCheck() {
	
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);
	
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
	
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
	
		List<Pilka> pilkiTab = new ArrayList<Pilka>();
		pilkiTab.add(pilka1);
		pilkiTab.add(pilka2);
	
		Kosz kosz = new Kosz();
		kosz.setNazwaKosza(nazwaU2);
		kosz.setLiczbaObiektow(liczbaObiektow2);
		kosz.setpilki(pilkiTab);

		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowprzedDodaniem = listaKoszow.size();
	
		managerKosz.addNewKosz(kosz);
	
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowpoDodaniu = listaKoszow2.size();
		//assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu-2);
	
		assertNotNull(listaKoszow);
	
		//int liczbaPlanet = listaPilek.size();
	
		assertEquals(liczbaKoszowpoDodaniu, liczbaKoszowprzedDodaniem+1);
		
		managerKosz.deleteAllKosze();
	}
	
	@Test
	public void editKoszCheck() {
		
		List<Kosz> listaKoszow1 = managerKosz.getAllKosze();
		int liczbaKoszowprzedDodaniem = listaKoszow1.size();
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa2);
		pilka1.setrozmiar(rozmiar2);

		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
				
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka1);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektow1);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		List<Pilka> pilkiTab2 = new ArrayList<Pilka>();
		pilkiTab2.add(pilka2);
		
		Kosz kosz2 = new Kosz();
		kosz2.setNazwaKosza(nazwaU2);
		kosz2.setLiczbaObiektow(liczbaObiektow2);
		kosz2.setpilki(pilkiTab2);
		managerKosz.addNewKosz(kosz2);

		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowpoDodaniu = listaKoszow2.size();
		assertEquals(liczbaKoszowprzedDodaniem+2, liczbaKoszowpoDodaniu);
		
		
		kosz2.setNazwaKosza(nazwa1);
		kosz2.setLiczbaObiektow(liczbaObiektow1);
		
		managerKosz.updateKosz(kosz2);
		
		ObjectId idKoszu = kosz2.getId();
		
		Kosz uk = managerKosz.getKoszById(idKoszu);
		
		assertEquals(nazwa1, uk.getNazwaKosza());
		assertEquals(liczbaObiektow1, uk.getLiczbaObiektow());
		
		managerKosz.deleteAllKosze();
		
	}
	
	
	@Test
	public void findKoszByLiczbaObiektow() {
		
		List<Kosz> listaKoszow1 = managerKosz.getAllKosze();
		int liczbaKoszowprzedDodaniem = listaKoszow1.size();
		
		Pilka pilka = new Pilka();
		pilka.setNazwa(nazwa1);
		pilka.setrozmiar(rozmiarUnique);	
		managerpilki.addpilka(pilka);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektowUnique);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		
		assertNotNull(managerpilki.getPilkaById(pilka.getId()));
		assertNotNull(managerKosz.getKoszById(kosz1.getId()));
		
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowpoDodaniu = listaKoszow2.size();
		
		assertEquals(liczbaKoszowpoDodaniu, liczbaKoszowprzedDodaniem+1);
		
		assertNotNull(managerKosz.getKoszeByLiczbaObiektow(kosz1.getLiczbaObiektow()));
		List<Kosz> uk = managerKosz.getKoszeByLiczbaObiektow(kosz1.getLiczbaObiektow());
		Kosz uk2 = uk.get(0);
		assertEquals(liczbaObiektowUnique, uk2.getLiczbaObiektow());
		
		managerKosz.deleteAllKosze();
	}
	
	@Test
	public void checkPobranieWszystkichObiektowZKoszu() {
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa1);
		pilka1.setrozmiar(rozmiar1);
		
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
				
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka1);
		pilkiTab1.add(pilka2);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektow1);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		List<Pilka> listaPilekWKoszu = managerKosz.getpilkiInKosz(kosz1);
		assertNotNull(listaPilekWKoszu);
		
		assertEquals(listaPilekWKoszu.size(), 2);
		
		Pilka pl1 = listaPilekWKoszu.get(0);
		Pilka pl2 = listaPilekWKoszu.get(1);
		
		assertEquals(pl1.getNazwa(), nazwa1);
		assertEquals(pl1.getrozmiar(), rozmiar1);
		
		assertEquals(pl2.getNazwa(), nazwa2);
		assertEquals(pl2.getrozmiar(), rozmiar2);
		
		managerKosz.deleteAllKosze();
	}

	
	@Test
	public void getKoszByNazwaLiczbaObiektowCheck() {
		List<Kosz> listaKoszow = managerKosz.getAllKosze();
		int liczbaKoszowPrzedDodaniem = listaKoszow.size();
		
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa1);
		pilka1.setrozmiar(rozmiar1);
	
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiar2);
	
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka1);
		
		List<Pilka> pilkiTab2 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka2);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektow1);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		Kosz kosz2 = new Kosz();
		kosz2.setNazwaKosza(NazwaKoszaUnique);
		kosz2.setLiczbaObiektow(liczbaObiektowUnique);
		kosz2.setpilki(pilkiTab2);
		managerKosz.addNewKosz(kosz2);
	
		List<Kosz> listaKoszow2 = managerKosz.getAllKosze();
		int liczbaKoszowPoDodaniu = listaKoszow2.size();
	
		assertEquals(liczbaKoszowPoDodaniu, liczbaKoszowPrzedDodaniem+2);
		
		assertNotNull(managerKosz.getKoszByNazwaLiczbaObiektow(NazwaKoszaUnique, liczbaObiektowUnique));
		List<Kosz> pl1 = managerKosz.getKoszByNazwaLiczbaObiektow(NazwaKoszaUnique, liczbaObiektowUnique);
		int length1 = pl1.size();
		assertEquals(1, length1);
		
		Kosz kosz3 = new Kosz();
		kosz3.setNazwaKosza(NazwaKoszaUnique);
		kosz3.setLiczbaObiektow(liczbaObiektowUnique);
		kosz3.setpilki(pilkiTab2);
		managerKosz.addNewKosz(kosz3);
		
		assertNotNull(managerKosz.getKoszByNazwaLiczbaObiektow(NazwaKoszaUnique, liczbaObiektowUnique));
		List<Kosz> pl2 = managerKosz.getKoszByNazwaLiczbaObiektow(NazwaKoszaUnique, liczbaObiektowUnique);
		int length2 = pl2.size();
		assertEquals(2, length2);
		
		List<Kosz> pl3 = managerKosz.getKoszByNazwaLiczbaObiektow(nazwaU1, liczbaObiektowUnique);
		int length3 = pl3.size();
		assertEquals(0, length3);
		
		managerKosz.deleteAllKosze();
	}
	
	@Test
	public void deletePilkiWKoszuByRozmiarCheck() {
		Pilka pilka1 = new Pilka();
		pilka1.setNazwa(nazwa1);
		pilka1.setrozmiar(rozmiar1);
		
		Pilka pilka2 = new Pilka();
		pilka2.setNazwa(nazwa2);
		pilka2.setrozmiar(rozmiarUnique2);
		
		Pilka pilka3 = new Pilka();
		pilka3.setNazwa(nazwa1);
		pilka3.setrozmiar(rozmiarUnique2);
				
		managerpilki.addpilka(pilka1);
		managerpilki.addpilka(pilka2);
		managerpilki.addpilka(pilka3);
		
		List<Pilka> pilkiTab1 = new ArrayList<Pilka>();
		pilkiTab1.add(pilka1);
		pilkiTab1.add(pilka2);
		pilkiTab1.add(pilka3);
		
		Kosz kosz1 = new Kosz();
		kosz1.setNazwaKosza(nazwaU1);
		kosz1.setLiczbaObiektow(liczbaObiektow1);
		kosz1.setpilki(pilkiTab1);
		managerKosz.addNewKosz(kosz1);
		
		List<Pilka> listaPilekKoszu = managerKosz.getpilkiInKosz(kosz1);
		assertNotNull(listaPilekKoszu);
		
		managerKosz.deletePilkiWKoszuByRozmiar(kosz1, kryterium);
		
		assertEquals(null,managerpilki.getPilkaById(pilka1.getId()));
		assertEquals(null, managerpilki.getPilkaById(pilka2.getId()));
		assertEquals(null, managerpilki.getPilkaById(pilka3.getId()));
		
	}
	
	
}
