package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Pilka;

public interface PilkaRepository extends CrudRepository<Pilka, ObjectId>{
	
	<S extends Pilka> S save(S entity);
	
	Pilka findById(ObjectId id);
	
	List<Pilka> findByRozmiar(int rozmiar);
	
	List<Pilka> findAll();
	
	@Query(value = "{ 'rozmiar' : ?0 }" )
	List<Pilka> znajdzPilka(int rozmiar);
	

}

