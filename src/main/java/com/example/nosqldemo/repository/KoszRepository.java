package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Kosz;


public interface KoszRepository extends CrudRepository<Kosz, ObjectId> {
	
	<S extends Kosz> S save(S entity); 
	
	Kosz findById(ObjectId id);
	
	List<Kosz> findByLiczbaObiektow(int liczbaObiektow);
	
	List<Kosz> findAll();
	
	@Query(value = "{ 'NazwaKosza' : ?0, 'liczbaObiektow' : ?1 }" )
	List<Kosz> znajdzKosz(String NazwaKosza, int liczbaObiektow);
	
}