package org.java.project.repo;

import org.java.project.pojo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer>{

}
