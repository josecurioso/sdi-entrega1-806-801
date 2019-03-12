package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service//quitamos esta etiqueta para eliminar el servicio
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	@PostConstruct
	public void init() {
		User user1 = new User("admin@gmail.com", "admin", "istrador");
		user1.setPassword("admin");
		user1.setRole(rolesService.getRoles()[2]);
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[1]);
		User user4 = new User("marta@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("pela@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("ed@gmail.com", "Edward", "Núñez");
		 user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);

		
		Set user2Offers = new HashSet<Offer>() {
			{
				add(new Offer("Nintendo", "consola", 100.0, user2));
				add(new Offer("Portátil", "ordenador", 600.0, user2));
			}
		};
		user2.setOffers(user2Offers);
		

		Set user3Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oneplus 5T", "movil", 400.0, user3));
				add(new Offer("Cristiano Ronaldo", "sabe jugar al futbol", 100000000.0, user3));
			}
		};
		user3.setOffers(user3Offers);
		
//		Set user2Marks = new HashSet<Mark>() {
//			{
//				add(new Mark("Nota B1", 5.0, user2));
//				add(new Mark("Nota B2", 4.3, user2));
//				add(new Mark("Nota B3", 8.0, user2));
//				add(new Mark("Nota B4", 3.5, user2));
//			}
//		};
//		user2.setMarks(user2Marks);
//		Set user3Marks = new HashSet<Mark>() {
//			{
//				;
//				add(new Mark("Nota C1", 5.5, user3));
//				add(new Mark("Nota C2", 6.6, user3));
//				add(new Mark("Nota C3", 7.0, user3));
//			}
//		};
//		user3.setMarks(user3Marks);
//		Set user4Marks = new HashSet<Mark>() {
//			{
//				add(new Mark("Nota D1", 10.0, user4));
//				add(new Mark("Nota D2", 8.0, user4));
//				add(new Mark("Nota D3", 9.0, user4));
//			}
//		};
//		user4.setMarks(user4Marks);
		usersService.addUser(user2);
		usersService.addUser(user1);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}