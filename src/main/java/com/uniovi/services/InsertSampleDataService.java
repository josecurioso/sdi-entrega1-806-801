package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service // quitamos esta etiqueta para eliminar el servicio
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

		Set user4Offers = new HashSet<Offer>() {
			{
				add(new Offer("Hitchikers guide to the galaxy", "Douglas Adams book", 20.0, user4));
				add(new Offer("Clean Code", "Robert C. Martin book", 40.0, user4));
			}
		};
		user4.setOffers(user4Offers);

		Set user5Offers = new HashSet<Offer>() {
			{
				add(new Offer("Tesla Model  S", "electric SUV", 90000.0, user5));
				add(new Offer("Solid chat application", "fully decentralized as timbl likes it", 123.0, user5));
			}
		};
		user5.setOffers(user5Offers);

		Set user6Offers = new HashSet<Offer>() {
			{
				add(new Offer("Starship", "Rocket stage capable of propulsive landing on Mars", 10.0, user6));
				add(new Offer("Super Heavy", "Booster suitable for the Starship second stage", 30.0, user6));
			}
		};
		user6.setOffers(user6Offers);

		usersService.addUser(user2);
		usersService.addUser(user1);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		
	}
}