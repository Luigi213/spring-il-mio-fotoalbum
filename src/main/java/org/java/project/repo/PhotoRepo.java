package org.java.project.repo;

import java.util.List;

import org.java.project.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer>{
	public List<Photo> findByTitoloContaining(String titolo);
}
