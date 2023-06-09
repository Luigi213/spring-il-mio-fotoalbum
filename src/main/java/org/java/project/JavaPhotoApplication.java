package org.java.project;

import org.java.project.auth.pojo.Role;
import org.java.project.auth.pojo.User;
import org.java.project.auth.serv.RoleService;
import org.java.project.auth.serv.UserService;
import org.java.project.pojo.Categoria;
import org.java.project.pojo.Photo;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JavaPhotoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private	PhotoService photoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaPhotoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role roleUser = new Role("AMMINISTRATORI");
		Role roleAdmin = new Role("SUPERADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser1 = new User("amministratori1", pws, roleUser);
		User userUser2 = new User("amministratori2", pws, roleUser);
		User userUser3 = new User("amministratori3", pws, roleUser);
		User userAdmin = new User("superadmin", pws, roleAdmin);
		
		userService.save(userUser1);
		userService.save(userUser2);
		userService.save(userUser3);
		userService.save(userAdmin);
		
		Categoria c1 = new Categoria("estiva");
		Categoria c2 = new Categoria("invernale");
		Categoria c3 = new Categoria("primaverile");
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		categoriaService.save(c3);
		
		Photo p1 = new Photo("ciccio1", "ho fame1", "photo1", true, userUser1, c1, c2);
		Photo p2 = new Photo("ciccio2", "ho fame2", "photo2", false, userUser1, c3);
		Photo p3 = new Photo("ciccio3", "ho fame3", "photo3", true, userUser3);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
	}
}
