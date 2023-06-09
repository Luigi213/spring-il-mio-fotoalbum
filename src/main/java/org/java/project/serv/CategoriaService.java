package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Categoria;
import org.java.project.repo.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepo categoriaRepo;
	
	public List<Categoria> findAll() {
		
		return categoriaRepo.findAll();
	}
	public Categoria save(Categoria categoria) {
		
		return categoriaRepo.save(categoria);
	}
	public Optional<Categoria> findById(int id) {
		
		return categoriaRepo.findById(id);
	}
	public void delete(Categoria categoria) {
		categoriaRepo.delete(categoria);
	}
}
