package org.java.project;

import org.java.project.pojo.Categoria;
import org.java.project.pojo.Photo;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaPhotoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private	PhotoService photoService;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaPhotoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria("estiva");
		Categoria c2 = new Categoria("invernale");
		Categoria c3 = new Categoria("primaverile");
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		categoriaService.save(c3);
		
		Photo p1 = new Photo("ciccio1", "ho fame1", "photo1", true, c1, c2);
		Photo p2 = new Photo("ciccio2", "ho fame2", "photo2", false, c3);
		Photo p3 = new Photo("ciccio3", "ho fame3", "photo3", true);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
	}
}
