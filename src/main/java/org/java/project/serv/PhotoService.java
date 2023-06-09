package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Photo;
import org.java.project.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	public Photo save(Photo photo) {
		
		return photoRepo.save(photo);
	}
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	public List<Photo> findByTitolo(String titolo){
		return photoRepo.findByTitoloContaining(titolo);
	}
	public void delete(Photo photo) {
		photoRepo.delete(photo);
	}
}
